package cn.jaly.announce.controller;

import cn.jaly.announce.entity.Announce;
import cn.jaly.announce.service.AnnounceService;
import cn.jaly.utils.annotations.Token;
import cn.jaly.utils.common.Constant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/announce/Announce/")
public class AnnounceHandler {

    @Autowired
    private AnnounceService announceService;

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
        List<Announce> announces = announceService.queryForList(keyword, siteId, order);
        PageInfo page = new PageInfo(announces);
        request.put("page", page);
        return "announce/announce_list";
    }

    @Token(save = true)
    @RequestMapping(value = "{input}", method = RequestMethod.GET)
    public String input(@PathVariable("input") String input,
                        Map<String, Object> request, HttpSession session,
                        @RequestParam(value = "id", required = false) Integer id) {
        if(("add".equals(input) && id == null) || ("edit".equals(input) && id != null)){
            Announce announce = new Announce();
            if (id == null) {
                Date fromTime = new Date();
                Date toTime = new Date(fromTime.getTime() + 3600 * 24 * 14 * 1000);
                announce.setStartTime(fromTime);
                announce.setEndTime(toTime);
                Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
                announce.setSiteId(siteId);
            } else if("edit".equals(input)){
                announce = announceService.getById(id);
            }
            request.put("announce", announce);
            return "announce/announce_input";
        }
        return null;
    }

    @Token(remove = true)
    @RequestMapping(value = "{save}", method = RequestMethod.POST)
    public String save(@PathVariable("save") String save, Announce announce) {
        Integer id = announce.getId();
        if(("add".equals(save) && id == null) || ("edit".equals(save) && id != null)) {
            Subject currentUser = SecurityUtils.getSubject();
            if (currentUser.isAuthenticated()) {
                String sender = currentUser.getPrincipals().getPrimaryPrincipal().toString();
                announce.setUserName(sender);
            }
            announceService.save(announce);
            return "redirect:/announce/Announce/list";
        }
        return null;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") Integer id) {
        announceService.delete(id);
        return "redirect:/announce/Announce/list";
    }
}
