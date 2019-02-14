package cn.jaly.utils.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class ContentPageParam extends TagSupport {

    private static final long serialVersionUID = 1L;

    @Override
    public int doEndTag() throws JspException {
        this.pageContext.getRequest().setAttribute(name, value);
        return EVAL_PAGE;
    }

    private String name;
    private Object value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}



