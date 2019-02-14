package cn.jaly.utils.taglib;

import com.google.gson.Gson;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.util.Map;

public class JsonDataSource extends SimpleTagSupport {

	private String id;
	private String value;

	public void setId(String id) {
		this.id = id;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public void doTag() {
		if (value.startsWith("{") && value.endsWith("}")) {
			Gson gson = new Gson();
			Object map = gson.fromJson(value, Map.class);
			getJspContext().setAttribute(id, map);
		}
	}
}
