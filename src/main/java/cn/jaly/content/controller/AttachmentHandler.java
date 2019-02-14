package cn.jaly.content.controller;

import cn.jaly.content.entity.Attachment;
import cn.jaly.content.service.AttachmentService;
import cn.jaly.utils.common.Constant;
import cn.jaly.utils.common.ExceptionUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/content/Attachment/")
public class AttachmentHandler {

    private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @Autowired
    private AttachmentService attachmentService;

    @RequestMapping("list")
    public String queryForList(Map<String, Object> request, HttpSession session,
                               @RequestParam(value = "stm", required = false) String startTime,
                               @RequestParam(value = "edm", required = false) String endTime,
                               @RequestParam(value = "kwd", required = false) String kwd,
                               @RequestParam(value = "type", required = false) String type,
                               @RequestParam(value = "odr", required = false) String order,
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
        List<Attachment> attachments = attachmentService.queryForList(siteId, kwd, startTime, endTime, type, order);
        PageInfo page = new PageInfo(attachments);
        request.put("page", page);
        return "content/attachment_list";
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") Integer id,
                         HttpServletRequest request, HttpSession session) {
        String contextPath = request.getServletContext().getRealPath("/");
        String filePath = attachmentService.getAttachmentFilePath(id);
        Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
        List<String> filePaths = new ArrayList<>();
        filePaths.add(filePath);
        try {
            attachmentService.deleteFile(siteId, contextPath, filePaths);
        } catch (IOException e) {
            logger.error(ExceptionUtils.formatExceptionInfo(e));
        }
        attachmentService.delete(id);
        return "redirect:/content/Attachment/list";
    }

    @RequestMapping(value = "batch-{method}", method = RequestMethod.POST)
    public String batchOperate(@PathVariable("method") String method,
                               HttpServletRequest request, HttpSession session, Integer[] ids) {
        String contextPath = request.getServletContext().getRealPath("/");
        List<String> filePaths = attachmentService.getAttachmentFilePath(ids);
        Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);

        boolean flag = false;
        if (ids != null && ids.length > 0) {
            switch (method) {
                case "delete":
                    try {
                        attachmentService.deleteFile(siteId, contextPath, filePaths);
                    } catch (IOException e) {
                        logger.error(ExceptionUtils.formatExceptionInfo(e));
                    }
                    attachmentService.delete(ids);
                    flag = true;
                    break;
                default:
                    break;
            }
        }
        return flag ? "redirect:/content/Attachment/list" : null;
    }

}
