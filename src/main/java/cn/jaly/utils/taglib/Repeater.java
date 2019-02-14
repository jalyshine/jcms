package cn.jaly.utils.taglib;

import cn.jaly.utils.common.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

public class Repeater extends SimpleTagSupport {

	private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
 
	private String var;
	private String dataSourceID;
	private String index;
	
	public void setVar(String var) {
		this.var = var;
	}

	public void setDataSourceID(String dataSourceID) {
		this.dataSourceID = dataSourceID;
	}
	
	public String getDataSourceID() {
		return dataSourceID;
	}
	
	public void setIndex(String index) {
		this.index = index;
	}

	@Override
	public void doTag() {
		List<LinkedHashMap<String, Object>> list =
				(List<LinkedHashMap<String, Object>>) getJspContext().getAttribute(dataSourceID);
		if(list != null){
			int i = 0;
			for (LinkedHashMap<String, Object> value : list) {
				getJspContext().setAttribute(var, value);
				if (index != null) {
					getJspContext().setAttribute(index, (Object) (i++));
				}
				try {
					getJspBody().invoke(null);
				} catch (JspException | IOException e) {
					logger.error(ExceptionUtils.formatExceptionInfo(e));
				}
			}
		}
	}

}
