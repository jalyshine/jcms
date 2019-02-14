package cn.jaly.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

@Controller
public class CommonHandler {

    @RequestMapping(value = "/test/{module}/{entity}/{action}", method = RequestMethod.GET)
    public String commonGet(Map<String, Object> request,
                          @PathVariable("module") String module,
                          @PathVariable("entity") String entity,
                          @PathVariable("action") String action){
        request.put("entity", entity);
        request.put("action", action);
        StringBuilder sb = new StringBuilder();
        sb.append(module).append("/").append(entity).append("_").append(action);
        return sb.toString();
    }

    @RequestMapping(value = "/test/{module}/{entity}/{action}", method = RequestMethod.POST)
    public String commonPost(Map<String, Object> request,
                          HttpServletRequest servletRequest,
                          @PathVariable("module") String module,
                          @PathVariable("entity") String entity,
                          @PathVariable("action") String action){

        Enumeration<String> attributeNames = servletRequest.getParameterNames();
        while (attributeNames.hasMoreElements()){
            String name = attributeNames.nextElement();
            Object value = servletRequest.getParameter(name);
            request.put(name, value);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(module).append("/").append(entity).append("_").append(action);
        return sb.toString();
    }

}
