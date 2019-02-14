package cn.jaly.content.controller;

import cn.jaly.content.entity.AttachSetting;
import cn.jaly.content.service.AttachSettingService;
import cn.jaly.content.service.AttachmentService;
import cn.jaly.utils.common.*;
import cn.jaly.utils.explorer.HttpUtils;
import cn.jaly.utils.explorer.UploadResult;
import cn.jaly.utils.explorer.UploadUtils;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class UploadHandler {

    private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private AttachSettingService attachSettingService;

    /**
     * 上传文件
     * @param request
     * @param session
     * @param files
     * @param type
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(HttpServletRequest request, HttpSession session,
                             @RequestParam("file") MultipartFile[] files,
                             @RequestParam(value = "type", required = false) String type) {
        String contextPath = request.getServletContext().getRealPath("/");
        Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
        AttachSetting attachSetting = attachSettingService.getBySiteId(siteId);

        UploadResult result = new UploadResult();
        try {
            if(type == null){
                result = UploadUtils.uploadFiles(files, contextPath, attachSetting);
            } else {
                result = UploadUtils.uploadImages(files, contextPath, type, attachSetting);
            }
            saveAttachmentRecord(result, request, session);
        } catch (IOException e) {
            result.setCode(1);
            logger.error(ExceptionUtils.formatExceptionInfo(e));
        }
        return UploadUtils.getLayUIResponse(result);
    }

    /**
     * 节点服务器上传文件后，回传上传结果
     * @param data
     * @param session
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/upload/attach", method = RequestMethod.POST)
    public String submitAttachment(String data, HttpSession session, HttpServletRequest request){
        Gson gson = new Gson();
        UploadResult result = gson.fromJson(data, UploadResult.class);
        saveAttachmentRecord(result, request, session);
        return new ResultBean(0).toJson();
    }

    private void saveAttachmentRecord(UploadResult result,
                                      HttpServletRequest request, HttpSession session){
        if(result.getCode() == 0){
            Integer adminId = (Integer) session.getAttribute(Constant.CURRENT_ADMIN);
            result.setUserId(adminId);
            result.setAdmin(true);
            Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
            result.setSiteId(siteId);
            result.setUploadIp(HttpUtils.getIP(request));
            // 开始上传
            attachmentService.save(result);
        }
    }
}
