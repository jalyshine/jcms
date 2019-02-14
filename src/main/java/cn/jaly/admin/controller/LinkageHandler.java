package cn.jaly.admin.controller;

import cn.jaly.admin.entity.Linkage;
import cn.jaly.admin.service.LinkageService;
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
@RequestMapping("/admin/Linkage/")
public class LinkageHandler {

    @Autowired
    private LinkageService linkageService;

    @RequestMapping("list")
    public String list(Map<String, Object> request, HttpSession session,
                       @RequestParam(value = "pid", required = false) Integer pid,
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
        List<Linkage> linkages = linkageService.getByParentId(siteId, pid);
        PageInfo page = new PageInfo(linkages);
        request.put("page", page);

        Map<Integer, String> parents = linkageService.getParentsById(pid);
        request.put("parents", parents);
        return "admin/linkage_list";
    }

    @Token(save = true)
    @RequestMapping(value = "{input}", method = RequestMethod.GET)
    public String input(@PathVariable("input") String input,
                        Map<String, Object> request, HttpSession session,
                        @RequestParam(value = "pid", required = false) Integer parentId,
                        @RequestParam(value = "id", required = false) Integer id) {
        if(("add".equals(input) && id == null) || ("edit".equals(input) && id != null)) {
            List<Linkage> linkages = linkageService.getAll();
            request.put("linkages", linkages);
            Linkage linkage = new Linkage();
            if (id == null) {
                linkage.setParentId(parentId);
                Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
                linkage.setSiteId(siteId);
            } else {
                linkage = linkageService.getById(id);
            }
            request.put("linkage", linkage);
            return "admin/linkage_input";
        }
        return null;
    }

    @Token(remove = true)
    @RequestMapping(value = "{save}", method = RequestMethod.POST)
    public String save(@PathVariable("save") String save, Linkage linkage) {
        Integer id = linkage.getId();
        if(("add".equals(save) && id == null) || ("edit".equals(save) && id != null)) {
            linkageService.save(linkage);
            return getResult(linkage.getParentId());
        }
        return null;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") Integer id) {
        Integer parentId = linkageService.getById(id).getParentId();
        linkageService.delete(id);
        return getResult(parentId);
    }

    private String getResult(Integer parentId){
        String res = "redirect:/admin/Linkage/list";
        if (parentId != null) {
            res += "?pid=" + parentId;
        }
        return res;
    }
}
