package cn.jaly.utils.interceptors;

import cn.jaly.admin.entity.IpBanned;
import cn.jaly.admin.entity.Logs;
import cn.jaly.admin.entity.SecuritySetting;
import cn.jaly.admin.service.IpBannedService;
import cn.jaly.admin.service.LogService;
import cn.jaly.admin.service.SettingService;
import cn.jaly.utils.common.Constant;
import cn.jaly.utils.common.ExceptionUtils;
import cn.jaly.utils.explorer.HttpUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * 访问控制拦截器
 */
public class AccessInterceptor extends HandlerInterceptorAdapter {

    private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @Autowired
    private LogService logService;

    @Autowired
    private IpBannedService ipBannedService;

    @Autowired
    private SettingService settingService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean isAllow = domainInterceptor(request, response);

        if (isAllow) {
            isAllow = ipInterceptor(request, response);
        }

        if (isAllow) {
            logsInterceptor(request);
        }

        return super.preHandle(request, response, handler);
    }

    /**
     * 后台访问域名拦截
     *
     * @param request
     * @return
     */
    private boolean domainInterceptor(HttpServletRequest request, HttpServletResponse response)
            throws DocumentException {
        boolean flag = true;
        String backDns = settingService.querySecuritySetting().getBackDomain();
        if (backDns != null && !backDns.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            String protocol = request.getProtocol().toLowerCase();
            if(protocol.startsWith("http/")){
                sb.append("http://");
            } else if(protocol.startsWith("https/")){
                sb.append("https://");
            }
            sb.append(request.getServerName());
            int serverPort = request.getServerPort();
            if(serverPort != 80){
                sb.append(":").append(serverPort);
            }
            sb.append(request.getRequestURI());
            if(!sb.toString().contains(backDns)){
                try {
                    request.getRequestDispatcher("/WEB-INF/status/404.jsp").forward(request, response);
                } catch (IOException | ServletException e) {
                    flag = false;
                    logger.error(ExceptionUtils.formatExceptionInfo(e));
                }
            }
        }
        return flag;
    }

    /**
     * ip禁止拦截
     */
    private boolean ipInterceptor(HttpServletRequest request, HttpServletResponse response) {
        boolean flag = true;
        String ip = HttpUtils.getIP(request);
        IpBanned ipBanned = ipBannedService.getByIp(ip);
        if (ipBanned != null) {
            Date expires = ipBanned.getExpires();
            Date now = new Date();
            if (expires.getTime() > now.getTime()) {
                try {
                    request.setAttribute("type", "banned");
                    request.getRequestDispatcher("/WEB-INF/status/warning.jsp").forward(request, response);
                    flag = false;
                } catch (IOException | ServletException e) {
                    flag = false;
                    logger.error(ExceptionUtils.formatExceptionInfo(e));
                }
            }
        }
        return flag;
    }

    /**
     * 后台操作拦截
     *
     * @param request
     */
    private void logsInterceptor(HttpServletRequest request) throws DocumentException {
        Boolean allowOperLogger = settingService.querySecuritySetting().getAllowOperLogger();
        if (allowOperLogger != null && allowOperLogger && "POST".equals(request.getMethod())) {
            Logs logs = new Logs();
            logs.setUri(request.getRequestURI());
            logs.setData(request.getQueryString());
            logs.setTime(new Date());
            logs.setIp(HttpUtils.getIP(request));

            HttpSession session = request.getSession();
            Object siteIdObj = session.getAttribute(Constant.CURRENT_SITE);
            if (siteIdObj != null) {
                logs.setSiteId((Integer) siteIdObj);
            }

            Object adminIdObj = session.getAttribute(Constant.CURRENT_ADMIN);
            if (adminIdObj != null) {
                Integer adminId = (Integer) adminIdObj;
                logs.setAdminId(adminId);
            }
            logService.save(logs);
        }
    }

}
