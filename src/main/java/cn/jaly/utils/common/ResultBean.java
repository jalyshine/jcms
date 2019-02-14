package cn.jaly.utils.common;

import com.google.gson.Gson;

/**
 * 全系统公用ajax回传接口
 * code：状态码 0成功，1失败，-1无权限
 * msg：信息
 * data：附加数据
 */
public class ResultBean {

    protected Integer code;
    protected String msg;
    protected String data;

    public ResultBean(Integer code, String msg, String data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultBean(){
        this(-1, "", "");
    }

    public ResultBean(Integer code){
        this(code, "", "");
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setData(Object data){
        this.data = new Gson().toJson(data);
    }

    public void setObjectData(Object object){
        Gson gson = new Gson();
        this.data = gson.toJson(object);
    }

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
