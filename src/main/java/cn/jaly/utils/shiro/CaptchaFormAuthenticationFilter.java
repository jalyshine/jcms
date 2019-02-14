package cn.jaly.utils.shiro;

import cn.jaly.utils.common.Constant;
import cn.jaly.utils.common.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * shiro 整合 ajax
 */
public class CaptchaFormAuthenticationFilter extends FormAuthenticationFilter {

    private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    /*
     *	主要是针对登入成功的处理方法。
     *	对于请求头是AJAX的之间返回JSON字符串。
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject,
                                     ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        // 将登录成功的用户名放入session
        httpServletRequest.getSession().setAttribute(Constant.CURRENT_USER, token.getPrincipal().toString());

        try {
            if ("XMLHttpRequest".equalsIgnoreCase(httpServletRequest
                    .getHeader("X-Requested-With"))) {           // ajax请求
                httpServletResponse.setCharacterEncoding("UTF-8");
                PrintWriter out = httpServletResponse.getWriter();
                out.println(Constant.STATUS_SUCCESS);
                out.flush();
                out.close();
            } else {
                issueSuccessRedirect(request, response);
            }
        } catch (Exception e){
            logger.error(ExceptionUtils.formatExceptionInfo(e));
        }
        return false;
    }

    /**
     * 主要是处理登入失败的方法
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e,
                                     ServletRequest request, ServletResponse response) {
        if ("XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request)
                .getHeader("X-Requested-With"))) {                    // ajax请求
            try {
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                String message = e.getClass().getSimpleName();
                if ("IncorrectCredentialsException".equals(message)) {
                    out.println(Constant.STATUS_LOGIN_ERROR);
                } else if ("UnknownAccountException".equals(message)) {
                    out.println(Constant.STATUS_LOGIN_ERROR);
                } else if ("LockedAccountException".equals(message)) {
                    out.println(Constant.STATUS_USER_ISLOCK);
                } else {
                    out.println(Constant.STATUS_UNKNOW_ERROR);
                }
                out.flush();
                out.close();
            } catch (IOException ex) {
                logger.error(ExceptionUtils.formatExceptionInfo(e));
            }
            return false;
        } else {
            setFailureAttribute(request, e);
            return true;
        }
    }

    /**
     * 所有请求都会经过的方法。
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response){
        boolean flag = true;
        try {
            if(this.isLoginRequest(request, response)) {
                if(this.isLoginSubmission(request, response)) {
                    flag = this.executeLogin(request, response);
                }
            } else {
                this.saveRequestAndRedirectToLogin(request, response);
                flag = false;
            }
        } catch (Exception e){
            flag = false;
            logger.error(ExceptionUtils.formatExceptionInfo(e));
        }
        return flag;
    }
}
