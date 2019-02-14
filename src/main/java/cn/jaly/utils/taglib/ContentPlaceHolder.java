package cn.jaly.utils.taglib;

import cn.jaly.utils.common.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class ContentPlaceHolder extends TagSupport {

    private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    private static final long serialVersionUID = 1L;

    @Override
    public int doEndTag() throws JspException {
        JspWriter out = pageContext.getOut();
        Object obj = this.pageContext.getRequest().getAttribute(this.getId());
        try {
            if (obj != null)
                out.write(obj.toString());
        } catch (IOException e) {
            logger.error(ExceptionUtils.formatExceptionInfo(e));
        }
        return EVAL_PAGE;
    }
}



