package cn.jaly.utils.taglib;

import cn.jaly.admin.dao.SqlCommandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 直接sql查询，当查询结果为单值时，结果直接存入以id为键值的页面上下文中
 */
public class SqlDataSource extends RequestContextAwareTag {

    private String id;
    private String cmd;
    private Integer from;
    private Integer count;

    @Autowired
    private SqlCommandMapper sqlCommandMapper;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    protected int doStartTagInternal() {
        sqlCommandMapper = this.getRequestContext().getWebApplicationContext().getBean(SqlCommandMapper.class);
        List<LinkedHashMap<String, Object>> result = sqlCommandMapper.selectCommandWithOrder(cmd);
        if(result != null){
            int size = result.size();
            if(size == 1){
                LinkedHashMap<String, Object> map = result.get(0);
                if(map.size() == 1){
                    pageContext.setAttribute(id, map.values().iterator().next());
                    return 0;
                }
            } else if(from != null && count != null && size >= (from + count)) {
                List<LinkedHashMap<String, Object>> temp = new ArrayList<>();
                for(int i=from; i<from+count; i++){
                    temp.add(result.get(i));
                }
                pageContext.setAttribute(id, temp);
                return 0;
            }
        }
        pageContext.setAttribute(id, result);
        return 0;
    }

}
