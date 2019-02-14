package cn.jaly.template.controller;

import cn.jaly.content.entity.AttachIndex;
import cn.jaly.content.service.AttachIndexService;
import cn.jaly.template.entity.Template;
import cn.jaly.template.entity.TemplateAttribute;
import cn.jaly.template.service.TemplateAttributeService;
import cn.jaly.template.service.TemplateService;
import cn.jaly.utils.annotations.Token;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/template/Template")
public class TemplateHandler {

    @Autowired
    private TemplateService templateService;

    @Autowired
    private TemplateAttributeService templateAttributeService;

    @Autowired
    private AttachIndexService attachIndexService;

    @RequestMapping("list")
    public String list(Map<String, Object> request,
                       @RequestParam(value = "cid", required = false) Integer colorId,
                       @RequestParam(value = "tid", required = false) Integer typeId,
                       @RequestParam(value = "kwd", required = false) String keyword,
                       @RequestParam(value = "odr", required = false) String order,
                       @RequestParam(value = "ps", required = false) Integer ps,
                       @RequestParam(value = "pn", required = false) Integer pn) {

        if (ps == null) {
            ps = 10;
        }
        if (pn == null) {
            pn = 1;
        }
        PageHelper.startPage(pn, ps);
        List<Template> templates = templateService.queryForList(keyword, colorId, typeId, order);
        PageInfo page = new PageInfo(templates);
        request.put("page", page);
        List<TemplateAttribute> types = templateAttributeService.getByAttrName("type");
        List<TemplateAttribute> colors = templateAttributeService.getByAttrName("color");
        request.put("types", types);
        request.put("colors", colors);
        return "template/template_list";
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
        Integer id = templateService.getIdByTitle(title);
        return id == null ? "0" : id.toString();
    }

    @Token(save = true)
    @RequestMapping(value = "{input}", method = RequestMethod.GET)
    public String input(@PathVariable("input") String input, Map<String, Object> request,
                        @RequestParam(value = "id", required = false) Integer id) {
        if(("add".equals(input) && id == null) || ("edit".equals(input) && id != null)) {
            request.put("colors", templateAttributeService.getByAttrName("color"));
            request.put("types", templateAttributeService.getByAttrName("type"));

            Template template = new Template();
            if (id != null) {
                template = templateService.getById(id);
            }
            request.put("template", template);
            return "template/template_input";
        }
        return null;
    }

    @Token(remove = true)
    @RequestMapping(value = "{save}", method = RequestMethod.POST)
    public String save(@PathVariable("save") String save, Template template) {
        Integer id = template.getId();
        if(("add".equals(save) && id == null) || ("edit".equals(save) && id != null)) {
            templateService.save(template);

            // 关联上传的图片
            AttachIndex attachIndex = new AttachIndex();
            attachIndex.setModule("template");
            attachIndex.setHost("template-" + template.getId());
            attachIndexService.save(template.getThumb(), attachIndex);
            return "redirect:/template/Template/list";
        }
        return null;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") Integer id) {
        templateService.delete(id);
        return "redirect:/template/Template/list";
    }

}
