package cn.jaly.member.controller;

import cn.jaly.content.entity.AttachIndex;
import cn.jaly.content.service.AttachIndexService;
import cn.jaly.member.entity.MemberGroup;
import cn.jaly.member.service.MemberGroupService;
import cn.jaly.utils.annotations.Token;
import cn.jaly.utils.common.Constant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/member/MemberGroup/")
public class MemberGroupHandler {

    @Autowired
    private MemberGroupService memberGroupService;

    @Autowired
    private AttachIndexService attachIndexService;

    @RequestMapping("list")
    public String list(Map<String, Object> request, HttpSession session,
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
        List<MemberGroup> memberGroups = memberGroupService.getBySiteId(siteId);
        PageInfo page = new PageInfo(memberGroups);
        request.put("page", page);
        return "member/member_group_list";
    }

    @ResponseBody
    @RequestMapping(value = "check", method = RequestMethod.POST)
    public String check(@RequestParam("name") String name) {
        Integer id = memberGroupService.getIdByName(name);
        return id == null ? "0" : id.toString();
    }

    @Token(save = true)
    @RequestMapping(value = "{input}", method = RequestMethod.GET)
    public String input(@PathVariable("input") String input,
                        Map<String, Object> request, HttpSession session,
                        @RequestParam(value = "id", required = false) Integer id) {
        if(("add".equals(input) && id == null) || ("edit".equals(input) && id != null)) {
            MemberGroup memberGroup = new MemberGroup();
            if (id != null) {
                memberGroup = memberGroupService.getById(id);
            } else {
                memberGroup.setAllowMessageNum(0);
                memberGroup.setAllowPostNum(0);
                memberGroup.setListOrder(0);
                memberGroup.setMaxPoint(0);
                memberGroup.setStarNum((byte) 0);
                memberGroup.setPriceYear(0.0);
                memberGroup.setPriceMonth(0.0);
                memberGroup.setPriceDay(0.0);
                Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
                memberGroup.setSiteId(siteId);
            }
            request.put("memberGroup", memberGroup);
            return "member/member_group_input";
        }
        return null;
    }

    @Token(remove = true)
    @RequestMapping(value = "{save}", method = RequestMethod.POST)
    public String save(@PathVariable("save") String save, MemberGroup memberGroup) {
        Integer id = memberGroup.getId();
        if(("add".equals(save) && id == null) || ("edit".equals(save) && id != null)) {
            memberGroupService.save(memberGroup);

            // 关联上传的图标
            String icon = memberGroup.getIcon();
            AttachIndex attachIndex = new AttachIndex();
            attachIndex.setModule("member");
            attachIndex.setHost("member_group-" + memberGroup.getId());
            attachIndexService.save(icon, attachIndex);
            return "redirect:/member/MemberGroup/list";
        }
        return null;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") Integer id) {
        memberGroupService.delete(id);
        return "redirect:/member/MemberGroup/list";
    }
}