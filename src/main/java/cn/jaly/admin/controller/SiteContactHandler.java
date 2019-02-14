package cn.jaly.admin.controller;

import cn.jaly.admin.entity.SiteContact;
import cn.jaly.admin.service.SiteContactService;
import cn.jaly.utils.annotations.Token;
import cn.jaly.utils.common.Constant;
import cn.jaly.utils.common.ResultBean;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/SiteContact/")
public class SiteContactHandler {

    @Autowired
    private SiteContactService siteContactService;

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
        List<SiteContact> siteContacts = siteContactService.queryForList(siteId, keyword, order);
        PageInfo page = new PageInfo(siteContacts);
        request.put("page", page);
        return "admin/site_contact_list";
    }

    @Token(save = true)
    @RequestMapping(value = "{input}", method = RequestMethod.GET)
    public String input(@PathVariable("input") String input,
                        Map<String, Object> request, HttpSession session,
                        @RequestParam(value = "id", required = false) Integer id) {
        if(("add".equals(input) && id == null) || ("edit".equals(input) && id != null)){
            SiteContact siteContact = new SiteContact();
            if (id == null) {
                Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
                siteContact.setSiteId(siteId);
            } else if("edit".equals(input)){
                siteContact = siteContactService.getById(id);
            }
            request.put("siteContact", siteContact);
            return "admin/site_contact_input";
        }
        return null;
    }

    @Token(remove = true)
    @RequestMapping(value = "{save}", method = RequestMethod.POST)
    public String save(@PathVariable("save") String save, SiteContact admin) {
        Integer id = admin.getId();
        if(("add".equals(save) && id == null) || ("edit".equals(save) && id != null)) {
            siteContactService.save(admin);
            return "redirect:/admin/SiteContact/list";
        }
        return null;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") Integer id) {
        siteContactService.delete(id);
        return "redirect:/admin/SiteContact/list";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ResponseBody
    @RequestMapping(value = "contact", method = RequestMethod.POST)
    public String contact(Integer siteId, String name, String phone, String email, String content){
        SiteContact siteContact = new SiteContact();
        siteContact.setSiteId(siteId);
        siteContact.setName(name);
        siteContact.setPhone(phone);
        siteContact.setEmail(email);
        siteContact.setContent(content);
        siteContactService.save(siteContact);
        return new ResultBean(0).toJson();
    }
}
