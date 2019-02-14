package cn.jaly.content.controller;

import cn.jaly.content.entity.KeyLink;
import cn.jaly.content.service.KeyLinkService;
import cn.jaly.utils.annotations.Token;
import cn.jaly.utils.common.Constant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/content/KeyLink/")
public class KeyLinkHandler {
    
    @Autowired
    private KeyLinkService keyLinkService;

    @RequestMapping("list")
    public String list(Map<String, Object> request, HttpSession session,
                       @RequestParam(value = "kwd", required = false) String keyword,
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
        List<KeyLink> keyLinks = keyLinkService.getBySiteId(siteId, keyword);
        PageInfo page = new PageInfo(keyLinks);
        request.put("page", page);
        return "content/key_link_list";
    }

    @Token(save = true)
    @RequestMapping(value = "{input}", method = RequestMethod.GET)
    public String input(@PathVariable("input") String input,
                        Map<String, Object> request, HttpSession session,
                        @RequestParam(value = "id", required = false) Integer id){
        if(("add".equals(input) && id == null) || ("edit".equals(input) && id != null)) {
            KeyLink keyLink = new KeyLink();
            if (id != null) {
                keyLink = keyLinkService.getById(id);
            } else {
                Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
                keyLink.setSiteId(siteId);
            }
            request.put("keyLink", keyLink);
            return "content/key_link_input";
        }
        return null;
    }

    @Token(remove = true)
    @RequestMapping(value = "{save}", method = RequestMethod.POST)
    public String save(@PathVariable("save") String save, KeyLink keyLink){
        Integer id = keyLink.getId();
        if(("add".equals(save) && id == null) || ("edit".equals(save) && id != null)) {
            keyLinkService.save(keyLink);
            return "redirect:/content/KeyLink/list";
        }
        return null;
    }

    @RequestMapping(value="delete", method=RequestMethod.POST)
    public String delete(@RequestParam("id") Integer id){
        keyLinkService.delete(id);
        return "redirect:/content/KeyLink/list";
    }
}
