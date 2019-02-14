package cn.jaly.special.controller;

import cn.jaly.special.service.SpecialTypeService;
import cn.jaly.utils.common.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/content/SpecialType/")
public class SpecialTypeHandler {

    @Autowired
    private SpecialTypeService specialTypeService;

    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") Integer id) {
        specialTypeService.delete(id);
        return new ResultBean(0).toJson();
    }
}
