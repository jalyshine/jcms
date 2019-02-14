package cn.jaly.utils.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class TokenTag extends SimpleTagSupport{

	@Override
	public void doTag() throws JspException, IOException { 
		JspWriter out = getJspContext().getOut();
		String value = (String) ((PageContext)getJspContext()).getSession().getAttribute("token");
		out.write("<input type='hidden' name='token' value='" + value + "'>");
	}
}
