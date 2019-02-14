package cn.jaly.template.controller;

import cn.jaly.template.entity.TemplateAttribute;
import cn.jaly.template.service.TemplateAttributeService;
import cn.jaly.utils.annotations.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/template/TemplateAttribute/")
public class TemplateAttributeHandler {

    @Autowired
    private TemplateAttributeService templateAttributeService;

    @RequestMapping("list")
    public String list(Map<String, Object> request,
                       @RequestParam("name") String name) {
        List<TemplateAttribute> templateAttributes = templateAttributeService.getByAttrName(name);
        request.put("templateAttributes", templateAttributes);
        return "template/template_attribute_list";
    }

    @Token(save = true)
    @RequestMapping(value = "{input}", method = RequestMethod.GET)
    public String input(@PathVariable("input") String input, Map<String, Object> request,
                        @RequestParam(value = "name", required = false) String name,
                        @RequestParam(value = "id", required = false) Integer id) {
        if(("add".equals(input) && id == null) || ("edit".equals(input) && id != null)) {
            TemplateAttribute templateAttribute = new TemplateAttribute();
            if (id == null) {
                templateAttribute.setAttrName(name);
            } else {
                templateAttribute = templateAttributeService.getById(id);
            }
            request.put("templateAttribute", templateAttribute);
            return "template/template_attribute_input";
        }
        return null;
    }

    @Token(remove = true)
    @RequestMapping(value = "{save}", method = RequestMethod.POST)
    public String save(@PathVariable("save") String save, TemplateAttribute templateAttribute) {
        Integer id = templateAttribute.getId();
        if(("add".equals(save) && id == null) || ("edit".equals(save) && id != null)) {
            String name = templateAttribute.getAttrName();
            templateAttributeService.save(templateAttribute);
            return "redirect:/template/TemplateAttribute/list?name=" + name;
        }
        return null;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") Integer id, @RequestParam("name") String name) {
        templateAttributeService.delete(id);
        return "redirect:/template/TemplateAttribute/list?name=" + name;
    }
}
