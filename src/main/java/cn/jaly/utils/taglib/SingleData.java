package cn.jaly.utils.taglib;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SingleData extends SimpleTagSupport {

    private String var;
    private String dataSourceID;

    public void setVar(String var) {
        this.var = var;
    }

    public String getVar() {
        return var;
    }

    public String getDataSourceID() {
        return dataSourceID;
    }

    public void setDataSourceID(String dataSourceID) {
        this.dataSourceID = dataSourceID;
    }

    @Override
    public void doTag(){
        getJspContext().setAttribute(var, getJspContext().getAttribute(dataSourceID));
    }
}
