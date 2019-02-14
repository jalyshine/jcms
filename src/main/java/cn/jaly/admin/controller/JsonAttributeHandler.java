package cn.jaly.admin.controller;

import cn.jaly.admin.service.JsonAttributeService;
import cn.jaly.utils.common.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/JsonAttribute/")
public class JsonAttributeHandler {

    @Autowired
    private JsonAttributeService jsonAttributeService;

    @ResponseBody
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public void save(HttpSession session, String tableName,
                       String fieldName, String[] names, String[] types){
        Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
        jsonAttributeService.batchInsert(siteId, tableName, fieldName, names, types);
    }

    @ResponseBody
    @RequestMapping("delete")
    public void delete(Integer id){
        jsonAttributeService.delete(id);
    }

}
