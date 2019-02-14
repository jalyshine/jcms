package cn.jaly.vote.controller;

import cn.jaly.content.entity.AttachIndex;
import cn.jaly.content.service.AttachIndexService;
import cn.jaly.vote.entity.Vote;
import cn.jaly.vote.entity.VoteOption;
import cn.jaly.vote.service.VoteService;
import cn.jaly.utils.annotations.Token;
import cn.jaly.utils.common.Constant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/vote/Vote/")
public class VoteHandler {

    @Autowired
    private VoteService voteService;

    @Autowired
    private AttachIndexService attachIndexService;

    @RequestMapping("list")
    public String queryForList(Map<String, Object> request, HttpSession session,
                               @RequestParam(value = "kwd", required = false) String keyword,
                               @RequestParam(value = "odr", required = false) String order,
                               @RequestParam(value = "ps", required = false) Integer ps,
                               @RequestParam(value = "pn", required = false) Integer pn) {
        if (ps == null) {
            ps = 20;
        }
        if (pn == null) {
            pn = 1;
        }
        PageHelper.startPage(pn, ps);
        Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
        List<Vote> votes = voteService.queryForList(siteId, keyword, order);
        PageInfo page = new PageInfo(votes);
        request.put("page", page);
        return "vote/vote_list";
    }

    @Token(save = true)
    @RequestMapping(value = "{input}", method = RequestMethod.GET)
    public String input(@PathVariable("input") String input,
                        Map<String, Object> request, HttpSession session,
                        @RequestParam(value = "id", required = false) Integer id) {
        if(("add".equals(input) && id == null) || ("edit".equals(input) && id != null)) {
            Vote vote = new Vote();
            Integer siteId = null;
            if (id == null) {
                Date fromTime = new Date();
                Date toTime = new Date(fromTime.getTime() + 7 * 24 * 60 * 60 * 1000);
                vote.setFromTime(fromTime);
                vote.setToTime(toTime);
                vote.setAllowGuest(true);
                vote.setCreditPoint(0);
                vote.setIntervalDays((short) 0);
                vote.setMaxVal((short) 1);
                vote.setMinVal((short) 1);

                siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
                vote.setSiteId(siteId);
            } else {
                vote = voteService.getById(id);
                siteId = vote.getSiteId();
            }
            request.put("vote", vote);
            return "vote/vote_input";
        }
        return null;
    }

    @Token(remove = true)
    @RequestMapping(value = "{save}", method = RequestMethod.POST)
    public String save(@PathVariable("save") String save,
                       Vote vote, String[] optIco, String[] optTxt) {
        Integer id = vote.getId();
        if(("add".equals(save) && id == null) || ("edit".equals(save) && id != null)) {
            if (optIco != null && optTxt != null) {
                List<VoteOption> voteOptions = new ArrayList<>();
                for (int i = 0; i < optIco.length; i++) {
                    VoteOption voteOption = new VoteOption(optTxt[i], optIco[i]);
                    voteOptions.add(voteOption);
                }
                vote.setVoteOptions(voteOptions);
            }
            voteService.save(vote);

            // 保存附件记录
            List<VoteOption> voteOptions = vote.getVoteOptions();
            List<String> icons = new ArrayList<>();
            for(VoteOption option : voteOptions){
                icons.add(option.getIcon());
            }
            AttachIndex attachIndex = new AttachIndex();
            attachIndex.setModule("vote");
            attachIndex.setHost("vote-" + vote.getId());
            attachIndexService.save(icons, attachIndex);
            return "redirect:/vote/Vote/list";
        }
        return null;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") Integer id) {
        voteService.delete(id);
        return "redirect:/vote/Vote/list";
    }
}