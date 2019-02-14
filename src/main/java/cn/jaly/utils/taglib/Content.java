package cn.jaly.utils.taglib;

import cn.jaly.utils.common.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class Content extends BodyTagSupport {

    private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    private static final long serialVersionUID = 1L;

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
        String content = this.bodyContent.getString();
        try {
            this.pageContext.getRequest().setAttribute(this.getContentPlaceHolderId(), content);
            this.bodyContent.clear();
        } catch (IOException e) {
            logger.error(ExceptionUtils.formatExceptionInfo(e));
        }
        return EVAL_PAGE;
    }

    private String contentPlaceHolderId;

    public String getContentPlaceHolderId() {
        return contentPlaceHolderId;
    }

    public void setContentPlaceHolderId(String contentPlaceHolderId) {
        this.contentPlaceHolderId = contentPlaceHolderId;
    }
}



