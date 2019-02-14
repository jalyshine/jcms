package cn.jaly.content.controller;

import cn.jaly.content.entity.Model;
import cn.jaly.content.service.ModelService;
import cn.jaly.utils.annotations.Token;
import cn.jaly.utils.common.Constant;
import cn.jaly.utils.common.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/content/Model/")
public class ModelHandler {

    @Autowired
    private ModelService modelService;

    @RequestMapping("list")
    public String list(Map<String, Object> request, HttpSession session) {
        Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
        List<Model> models = modelService.getBySiteId(siteId);
        request.put("models", models);
        return "content/model_list";
    }

    @Token(save = true)
    @RequestMapping(value = "{input}", method = RequestMethod.GET)
    public String input(@PathVariable("input") String input,
                        Map<String, Object> request, HttpSession session,
                        HttpServletRequest servletRequest,
                        @RequestParam(value = "id", required = false) Integer id) {
        if(("add".equals(input) && id == null) || ("edit".equals(input) && id != null)) {
            Model model = new Model();
            if (id != null) {
                model = modelService.getById(id);
            } else {
                Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
                model.setSiteId(siteId);
            }
            request.put("model", model);
            return "content/model_input";
        }
        return null;
    }

    @Token(remove = true)
    @RequestMapping(value = "{save}", method = RequestMethod.POST)
    public String save(@PathVariable("save") String save, Model model) {
        Integer id = model.getId();
        if(("add".equals(save) && id == null) || ("edit".equals(save) && id != null)) {
            modelService.save(model);
            return "redirect:/content/Model/list";
        }
        return null;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") Integer id) {
        modelService.delete(id);
        return "redirect:/content/Model/list";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ResponseBody
    @RequestMapping(value = "hit", method = RequestMethod.POST)
    public String updateContentHit(String tableName, Integer id, Integer times){
        modelService.updateContentHits(tableName, id, times);
        return new ResultBean(0).toJson();
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ResponseBody
    @RequestMapping(value = "q_hit", method = RequestMethod.POST)
    public String queryContentHit(String tableName, Integer id){
        ResultBean resultBean = new ResultBean(0);
        Integer hits = modelService.getContentHits(tableName, id);
        resultBean.setData(hits);
        return resultBean.toJson();
    }
}
