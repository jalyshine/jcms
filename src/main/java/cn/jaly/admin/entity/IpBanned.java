package cn.jaly.admin.entity;

import cn.jaly.utils.common.DateTimeUtils;

import java.io.Serializable;
import java.util.Date;

public class IpBanned implements Serializable{
    private Integer id;

    private String ip;

    private Date expires;

    private String expireStr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Date getExpires() {
        return expires;
    }

    public void setExpires(Date expires) {
        this.expires = expires;
        this.expireStr = DateTimeUtils.formatFull(expires);
    }

    public String getExpireStr() {
        return expireStr;
    }

    public void setExpireStr(String expireStr) {
        this.expireStr = expireStr;
        this.expires = DateTimeUtils.parseFull(expireStr);
    }
}