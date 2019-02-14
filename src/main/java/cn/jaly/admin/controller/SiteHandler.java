package cn.jaly.admin.controller;

import cn.jaly.admin.entity.Site;
import cn.jaly.admin.service.SiteService;
import cn.jaly.template.entity.Template;
import cn.jaly.template.service.TemplateService;
import cn.jaly.utils.annotations.Token;
import cn.jaly.utils.common.ExceptionUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/Site/")
public class SiteHandler {

    private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @Autowired
    private SiteService siteService;

    @Autowired
    private TemplateService templateService;

    @RequestMapping("list")
    public String list(Map<String, Object> request,
                       @RequestParam(value = "kwd", required = false) String keyword,
                       @RequestParam(value = "ps", required = false) Integer ps,
                       @RequestParam(value = "pn", required = false) Integer pn) {
        if (ps == null) {
            ps = 20;
        }
        if (pn == null) {
            pn = 1;
        }
        PageHelper.startPage(pn, ps);
        List<Site> sites = siteService.queryForList(keyword);
        PageInfo page = new PageInfo(sites);
        request.put("page", page);

        return "admin/site_list";
    }

    @ResponseBody
    @RequestMapping(value = "check", method = RequestMethod.POST)
    public String check(@RequestParam("name") String name) {
        Integer id = siteService.getIdByName(name);
        return id == null ? "0" : id.toString();
    }

    @Token(save = true)
    @RequestMapping(value = "{input}", method = RequestMethod.GET)
    public String input(@PathVariable("input") String input,
                        Map<String, Object> request, HttpServletRequest servletRequest,
                        @RequestParam(value = "id", required = false) Integer id) {
        if (("add".equals(input) && id == null) || ("edit".equals(input) && id != null)) {

            List<Template> templates = templateService.getAll();
            request.put("templates", templates);

            Site site = new Site();
            if (id != null) {
                site = siteService.getById(id);
            } else {
                site.setUserName("admin");
                site.setStartTime(new Date());
                Calendar calendar = Calendar.getInstance();
                calendar.set(2099, 12, 31, 23, 59, 59);
                site.setEndTime(calendar.getTime());
            }
            request.put("site", site);
            return "admin/site_input";
        }
        return null;
    }

    @Token(remove = true)
    @RequestMapping(value = "{save}", method = RequestMethod.POST)
    public String save(@PathVariable("save") String save, Site site) {
        Integer id = site.getId();
        if (("add".equals(save) && id == null) || ("edit".equals(save) && id != null)) {
            try {
                siteService.save(site);
            } catch (IOException e) {
                logger.error(ExceptionUtils.formatExceptionInfo(e));
            }
            return "redirect:/admin/Site/list";
        }
        return null;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") Integer id) {
        siteService.delete(id);
        return "redirect:/admin/Site/list";
    }
}