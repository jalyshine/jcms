package cn.jaly.admin.controller;

import cn.jaly.admin.entity.EmailSetting;
import cn.jaly.admin.entity.ErrorLogSetting;
import cn.jaly.admin.entity.SecuritySetting;
import cn.jaly.admin.service.SettingService;
import cn.jaly.utils.common.ExceptionUtils;
import cn.jaly.utils.common.MailUtils;
import cn.jaly.utils.common.ResultBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;

@Controller
@RequestMapping("/admin/")
public class SettingHandler {

	private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

	@Autowired
	private SettingService settingService;

	@RequestMapping(value = "SecuritySetting/manage", method = RequestMethod.GET)
	public String showSecuritySetting(Map<String, Object> request){
		try {
			SecuritySetting securitySetting = settingService.querySecuritySetting();
			ErrorLogSetting errorLogSetting = settingService.queryLogSetting();
			securitySetting.setErrorLogSetting(errorLogSetting);
			request.put("securitySetting", securitySetting);
		} catch (DocumentException e) {
			logger.error(ExceptionUtils.formatExceptionInfo(e));
		}
		return "admin/security_setting";
	}

	@RequestMapping(value = "EmailSetting/manage", method = RequestMethod.GET)
	public String showEmailSetting(Map<String, Object> request){
		try {
			EmailSetting emailSetting = settingService.queryEmailSetting();
			request.put("emailSetting", emailSetting);
		} catch (DocumentException e) {
			logger.error(ExceptionUtils.formatExceptionInfo(e));
		}
		return "admin/email_setting";
	}

	@ResponseBody
	@RequestMapping(value = "SecuritySetting/manage", method = RequestMethod.POST)
	public String saveSecuritySetting(SecuritySetting securitySetting){
        ResultBean resultBean = new ResultBean(0);
		try {
			settingService.updateSecuritySetting(securitySetting);
			settingService.updateLogSetting(securitySetting.getErrorLogSetting());
		} catch (DocumentException | IOException e) {
			logger.error(ExceptionUtils.formatExceptionInfo(e));
			resultBean.setCode(1);
			resultBean.setMsg(e.getMessage());
		}
		return resultBean.toJson();
	}

	@ResponseBody
	@RequestMapping(value = "EmailSetting/manage", method = RequestMethod.POST)
	public String saveEmailSetting(EmailSetting emailSetting){
        ResultBean resultBean = new ResultBean(0);
		try {
			settingService.updateEmailSetting(emailSetting);
		} catch (DocumentException | IOException e) {
			logger.error(ExceptionUtils.formatExceptionInfo(e));
            resultBean.setCode(1);
            resultBean.setMsg(e.getMessage());
		}
        return resultBean.toJson();
	}

	@ResponseBody
	@RequestMapping(value = "EmailSetting/test", method = RequestMethod.POST)
	public String sendTestMail(String host, String from, Boolean needAuth,
							   String userName, String password, String content){
		MailUtils mailUtils = new MailUtils();
		mailUtils.setAddress(host, from, from);
		mailUtils.setContent(content, content);
        ResultBean resultBean = new ResultBean(0);
		try {
			mailUtils.send(userName, password, needAuth);
		} catch (GeneralSecurityException | MessagingException e) {
			logger.error(ExceptionUtils.formatExceptionInfo(e));
            resultBean.setCode(1);
            resultBean.setMsg(e.getMessage());
		}
        return resultBean.toJson();
	}

}
