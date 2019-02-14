package cn.jaly.admin.controller;

import cn.jaly.admin.entity.Logs;
import cn.jaly.admin.service.LogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/Logs/")
public class LogsHandler {

    @Autowired
    private LogService logService;

    @RequestMapping("list")
    public String list(Map<String, Object> request,
                       @RequestParam(value = "ps", required = false) Integer ps,
                       @RequestParam(value = "pn", required = false) Integer pn) {

        if (ps == null) {
            ps = 20;
        }
        if (pn == null) {
            pn = 1;
        }

        PageHelper.startPage(pn, ps);
        List<Logs> logs = logService.getAll();
        PageInfo page = new PageInfo(logs);
        request.put("page", page);

        return "admin/logs_list";
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@RequestParam("ids") Integer[] ids) {
        logService.delete(ids);
        return "redirect:/admin/Logs/list";
    }

}
