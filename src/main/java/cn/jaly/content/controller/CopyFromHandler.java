package cn.jaly.content.controller;

import java.util.List;
import java.util.Map;

import cn.jaly.admin.entity.Site;
import cn.jaly.admin.service.SiteService;
import cn.jaly.content.entity.AttachIndex;
import cn.jaly.content.entity.CopyFrom;
import cn.jaly.content.service.AttachIndexService;
import cn.jaly.content.service.CopyFromService;
import cn.jaly.utils.annotations.Token;
import cn.jaly.utils.common.Constant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/content/CopyFrom/")
public class CopyFromHandler {

    @Autowired
    private CopyFromService copyFromService;

    @Autowired
    private AttachIndexService attachIndexService;

    @RequestMapping("list")
    public String list(Map<String, Object> request, HttpSession session,
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
        Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
        List<CopyFrom> copyFroms = copyFromService.getBySiteId(siteId, keyword);
        PageInfo page = new PageInfo(copyFroms);
        request.put("page", page);
        return "content/copy_from_list";
    }

    @Token(save = true)
    @RequestMapping(value = "{input}", method = RequestMethod.GET)
    public String input(@PathVariable("input") String input,
                        Map<String, Object> request, HttpSession session,
                        @RequestParam(value = "id", required = false) Integer id) {
        if(("add".equals(input) && id == null) || ("edit".equals(input) && id != null)) {
            CopyFrom copyFrom = new CopyFrom();
            if (id != null) {
                copyFrom = copyFromService.getById(id);
            } else {
                Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
                copyFrom.setSiteId(siteId);
            }
            request.put("copyFrom", copyFrom);
            return "content/copy_from_input";
        }
        return null;
    }

    @Token(remove = true)
    @RequestMapping(value = "{save}", method = RequestMethod.POST)
    public String save(@PathVariable("save") String save, CopyFrom copyFrom) {
        Integer id = copyFrom.getId();
        if(("add".equals(save) && id == null) || ("edit".equals(save) && id != null)) {
            copyFromService.save(copyFrom);

            // 关联上传的图标
            String icon = copyFrom.getThumb();
            AttachIndex attachIndex = new AttachIndex();
            attachIndex.setModule("content");
            attachIndex.setHost("copy_from-" + copyFrom.getId());
            attachIndexService.save(icon, attachIndex);
            return "redirect:/content/CopyFrom/list";
        }
        return null;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") Integer id) {
        copyFromService.delete(id);
        return "redirect:/content/CopyFrom/list";
    }
}
