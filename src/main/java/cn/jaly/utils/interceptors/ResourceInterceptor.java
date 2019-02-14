package cn.jaly.utils.interceptors;

import cn.jaly.utils.common.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 静态资源拦截器
 * 除allowExtensions定义的扩展名外，
 * 其余静态资源一律不允许通过地址栏输入地址访问
 */
public class ResourceInterceptor extends HandlerInterceptorAdapter {

    private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    private String allowExtensions;

    public String getAllowExtensions() {
        return allowExtensions;
    }

    public void setAllowExtensions(String allowExtensions) {
        this.allowExtensions = allowExtensions;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        if(requestURI.contains(".")){
            int index = requestURI.lastIndexOf(".");
            String ext = requestURI.substring(index + 1);
            if(!allowExtensions.contains(ext.toLowerCase() + ",")){
                try {
                    request.getRequestDispatcher("/WEB-INF/views/404.jsp").forward(request, response);
                } catch (ServletException | IOException e) {
                    logger.error(ExceptionUtils.formatExceptionInfo(e));
                }
            }
        }
        return super.preHandle(request, response, handler);
    }
}
