package cn.jaly.utils.taglib;

import javax.servlet.jsp.tagext.TagSupport;

public class ContentError extends TagSupport {

    private static final long serialVersionUID = 1L;

    private Boolean urlError;

    public void setUrlError(Boolean urlError) {
        this.urlError = urlError;
    }

    @Override
    public int doEndTag() {
        if(urlError){
            pageContext.getResponse().setContentLength(0);
        }
        return EVAL_PAGE;
    }
}



