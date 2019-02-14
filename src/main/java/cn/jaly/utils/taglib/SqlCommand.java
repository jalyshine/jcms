package cn.jaly.utils.taglib;

import cn.jaly.admin.dao.SqlCommandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

public class SqlCommand extends RequestContextAwareTag {

    private String cmd;
    private String method;

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Autowired
    private SqlCommandMapper sqlCommandMapper;

    @Override
    protected int doStartTagInternal() throws Exception {
        sqlCommandMapper = this.getRequestContext().getWebApplicationContext().getBean(SqlCommandMapper.class);
        switch (method) {
            case "insert":
                sqlCommandMapper.insertCommand(cmd);
                break;
            case "update":
                sqlCommandMapper.updateCommand(cmd);
                break;
            case "delete":
                sqlCommandMapper.deleteCommand(cmd);
                break;
        }
        return 0;
    }
}
