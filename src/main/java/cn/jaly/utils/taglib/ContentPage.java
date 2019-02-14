package cn.jaly.utils.taglib;

import cn.jaly.utils.common.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class ContentPage extends BodyTagSupport {

    private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    private static final long serialVersionUID = 1L;

    @Override
    public void doInitBody() throws JspException {
        try {
            this.pageContext.getRequest().setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error(ExceptionUtils.formatExceptionInfo(e));
        }
        super.doInitBody();
    }

    @Override
    public int doAfterBody() throws JspException {
        return SKIP_BODY;
    }

    @Override
    public int doStartTag() throws JspException {
        return EVAL_BODY_BUFFERED;
    }

    @Override
    public int doEndTag() throws JspException {
        JspWriter out = pageContext.getOut();
        CustomResponse bufferedResponse =
                new CustomResponse((HttpServletResponse) this.pageContext.getResponse());
        try {
            this.pageContext.getServletContext().getRequestDispatcher(
                    this.getMasterPagePath()).include(this.pageContext.getRequest(), bufferedResponse);
            out.write(bufferedResponse.getContent());
        } catch (ServletException | IOException e) {
            logger.error(ExceptionUtils.formatExceptionInfo(e));
        }
        return SKIP_PAGE;
    }

    private String masterPagePath;

    public String getMasterPagePath() {
        return masterPagePath;
    }

    public void setMasterPagePath(String masterPagePath) {
        this.masterPagePath = masterPagePath;
    }
}



