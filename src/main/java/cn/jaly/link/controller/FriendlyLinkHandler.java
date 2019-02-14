package cn.jaly.link.controller;

import cn.jaly.content.entity.AttachIndex;
import cn.jaly.content.service.AttachIndexService;
import cn.jaly.link.entity.FriendlyLink;
import cn.jaly.link.entity.FriendlyLinkType;
import cn.jaly.link.service.FriendlyLinkService;
import cn.jaly.link.service.FriendlyLinkTypeService;
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
@RequestMapping("/link/FriendlyLink/")
public class FriendlyLinkHandler {

    @Autowired
    private FriendlyLinkService friendlyLinkService;

    @Autowired
    private FriendlyLinkTypeService friendlyLinkTypeService;

    @Autowired
    private AttachIndexService attachIndexService;

    @RequestMapping("list")
    public String list(Map<String, Object> request, HttpSession session,
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
        List<FriendlyLink> friendlyLinks = friendlyLinkService.queryForList(siteId, keyword, order);
        PageInfo page = new PageInfo(friendlyLinks);
        request.put("page", page);
        return "link/friendly_link_list";
    }

    @Token(save = true)
    @RequestMapping(value = "{input}", method = RequestMethod.GET)
    public String input(@PathVariable("input") String input,
                        Map<String, Object> request, HttpSession session,
                        @RequestParam(value = "id", required = false) Integer id) {
        if(("add".equals(input) && id == null) || ("edit".equals(input) && id != null)) {
            FriendlyLink friendlyLink = new FriendlyLink();
            Integer siteId;
            if (id != null) {
                friendlyLink = friendlyLinkService.getById(id);
                siteId = friendlyLink.getSiteId();
            } else {
                siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
                friendlyLink.setSiteId(siteId);
            }
            List<FriendlyLinkType> friendlyLinkTypes = friendlyLinkTypeService.getBySite(siteId);
            request.put("friendlyLinkTypes", friendlyLinkTypes);
            request.put("friendlyLink", friendlyLink);
            return "link/friendly_link_input";
        }
        return null;
    }

    @Token(remove = true)
    @RequestMapping(value = "{save}", method = RequestMethod.POST)
    public String save(@PathVariable("save") String save, FriendlyLink friendlyLink) {
        Integer id = friendlyLink.getId();
        if(("add".equals(save) && id == null) || ("edit".equals(save) && id != null)) {
            friendlyLinkService.save(friendlyLink);

            // 关联上传的图标
            String icon = friendlyLink.getLogo();
            AttachIndex attachIndex = new AttachIndex();
            attachIndex.setModule("link");
            attachIndex.setHost("friendly_link-" + friendlyLink.getId());
            attachIndexService.save(icon, attachIndex);
            return "redirect:/link/FriendlyLink/list";
        }
        return null;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") Integer id) {
        friendlyLinkService.delete(id);
        return "redirect:/link/FriendlyLink/list";
    }
}
