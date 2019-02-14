package cn.jaly.utils.taglib;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SingleItem extends SimpleTagSupport {

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
        List<LinkedHashMap<String, Object>> list =
                (List<LinkedHashMap<String, Object>>) getJspContext().getAttribute(dataSourceID);
        if(list != null && !list.isEmpty()){
            getJspContext().setAttribute(var, list.get(0));
        }
    }
}
