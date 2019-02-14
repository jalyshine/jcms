package cn.jaly.content.controller;

import cn.jaly.content.entity.AttachSetting;
import cn.jaly.content.service.AttachSettingService;
import cn.jaly.content.entity.AttachIndex;
import cn.jaly.content.service.AttachIndexService;
import cn.jaly.utils.common.Constant;
import cn.jaly.utils.common.ExceptionUtils;
import cn.jaly.utils.common.ResultBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/content/AttachSetting/")
public class AttachSettingHandler {

    private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @Autowired
    private AttachSettingService attachSettingService;

    @Autowired
    private AttachIndexService attachIndexService;

    @RequestMapping(value = "manage", method = RequestMethod.GET)
    public String manage(Map<String, Object> request, HttpSession session){
        Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
        AttachSetting attachSetting = attachSettingService.getBySiteId(siteId);
        if(attachSetting == null){
            attachSetting = new AttachSetting();
        }
        attachSetting.setSiteId(siteId);
        request.put("attachSetting", attachSetting);
        return "content/attach_setting";
    }

    @ResponseBody
    @RequestMapping(value = "manage", method = RequestMethod.POST)
    public String manage(AttachSetting attachSetting, HttpSession session){
        attachSettingService.save(attachSetting);
        Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
        try {
            attachSettingService.synchronizeSetting(attachSetting, siteId);
        } catch (IOException e) {
            logger.error(ExceptionUtils.formatExceptionInfo(e));
        }

        // 关联上传的图标
        String icon = attachSetting.getMark();
        AttachIndex attachIndex = new AttachIndex();
        attachIndex.setModule("admin");
        attachIndex.setHost("attach_setting-" + attachSetting.getSiteId());
        attachIndexService.save(icon, attachIndex);
        return new ResultBean(0).toJson();
    }

}
