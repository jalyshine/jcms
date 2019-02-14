package cn.jaly.content.controller;

import cn.jaly.content.entity.WorkFlow;
import cn.jaly.content.service.WorkFlowService;
import cn.jaly.utils.annotations.Token;
import cn.jaly.utils.common.Constant;
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
@RequestMapping("/content/WorkFlow/")
public class WorkFlowHandler {

    @Autowired
    private WorkFlowService workFlowService;

    @RequestMapping("list")
    public String list(Map<String, Object> request, HttpSession session) {
        Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
        List<WorkFlow> workFlows = workFlowService.getBySiteId(siteId);
        request.put("workFlows", workFlows);
        return "content/workflow_list";
    }

    @Token(save = true)
    @RequestMapping(value = "{input}", method = RequestMethod.GET)
    public String input(@PathVariable("input") String input,
                        Map<String, Object> request, HttpSession session,
                        @RequestParam(value = "id", required = false) Integer id) {
        if(("add".equals(input) && id == null) || ("edit".equals(input) && id != null)) {
            WorkFlow workFlow = new WorkFlow();
            if (id != null) {
                workFlow = workFlowService.getById(id);
            } else {
                Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
                workFlow.setSiteId(siteId);
            }
            request.put("workFlow", workFlow);
            return "content/workflow_input";
        }
        return null;
    }

    @Token(remove = true)
    @RequestMapping(value = "{save}", method = RequestMethod.POST)
    public String save(@PathVariable("save") String save, WorkFlow workFlow) {
        Integer id = workFlow.getId();
        if(("add".equals(save) && id == null) || ("edit".equals(save) && id != null)) {
            workFlowService.save(workFlow);
            return "redirect:/content/WorkFlow/list";
        }
        return null;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") Integer id) {
        workFlowService.delete(id);
        return "redirect:/content/WorkFlow/list";
    }
}
