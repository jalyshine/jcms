package cn.jaly.special.controller;

import cn.jaly.content.entity.AttachIndex;
import cn.jaly.content.service.AttachIndexService;
import cn.jaly.special.entity.Special;
import cn.jaly.special.entity.SpecialType;
import cn.jaly.special.service.SpecialService;
import cn.jaly.special.service.SpecialTypeService;
import cn.jaly.template.service.TemplateService;
import cn.jaly.utils.annotations.Token;
import cn.jaly.utils.common.Constant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/special/Special/")
public class SpecialHandler {

    @Autowired
    private SpecialService specialService;

    @Autowired
    private SpecialTypeService specialTypeService;

    @Autowired
    private AttachIndexService attachIndexService;

    @Autowired
    private TemplateService templateService;

    @RequestMapping("list")
    public String list(Map<String, Object> request, HttpSession session,
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
        List<Special> specials = specialService.getBySite(siteId, order);
        PageInfo page = new PageInfo(specials);
        request.put("page", page);
        return "special/special_list";
    }

    /**
     * 检查标题是否重名
     *
     * @param title
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "check", method = RequestMethod.POST)
    public String check(@RequestParam("ttl") String title) {
        Integer id = specialService.getIdByTitle(title);
        return id == null ? "0" : id.toString();
    }

    @Token(save = true)
    @RequestMapping(value = "{input}", method = RequestMethod.GET)
    public String input(@PathVariable("input") String input, HttpServletRequest servletRequest,
                        Map<String, Object> request, HttpSession session,
                        @RequestParam(value = "id", required = false) Integer id) {
        if(("add".equals(input) && id == null) || ("edit".equals(input) && id != null)) {
            Special special = new Special();
            Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
            if (id != null) {
                special = specialService.getById(id);
                List<SpecialType> specialTypes = specialTypeService.getBySpecialId(id);
                request.put("specialTypes", specialTypes);
            } else {
                special.setSiteId(siteId);
            }
            request.put("special", special);

            String contextPath = servletRequest.getServletContext().getRealPath("/");
            List<String> homeTemps =
                    templateService.queryFileForList(contextPath, siteId, "special", "home_");
            request.put("template_home", homeTemps);
            List<String> listTemps =
                    templateService.queryFileForList(contextPath, siteId, "special", "list_");
            request.put("template_list", listTemps);
            List<String> showTemps =
                    templateService.queryFileForList(contextPath, siteId, "special", "show_");
            request.put("template_show", showTemps);

            return "special/special_input";
        }
        return null;
    }

    @Token(remove = true)
    @RequestMapping(value = "{save}", method = RequestMethod.POST)
    public String save(@PathVariable("save") String save, Special special, Integer[] sid,
                       Integer[] order, String[] name, String[] desc, String[] path) {
        Integer id = special.getId();
        if(("add".equals(save) && id == null) || ("edit".equals(save) && id != null)) {
            specialService.save(special, sid, order, name, desc, path);

            // 关联上传的缩略图和横幅
            List<String> paths = new ArrayList<>();
            paths.add(special.getThumb());
            paths.add(special.getBanner());
            AttachIndex attachIndex = new AttachIndex();
            attachIndex.setModule("special");
            attachIndex.setHost("special" + special.getId());
            attachIndexService.save(paths, attachIndex);
            return "redirect:/special/Special/list";
        }
        return null;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") Integer id) {
        specialService.delete(id);
        return "redirect:/special/Special/list";
    }
}
