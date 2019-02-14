package cn.jaly.admin.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SiteHit {
    private Integer id;

    private Integer hit;

    private Date time;

    private Integer siteId;

    private String timeStr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHit() {
        return hit;
    }

    public void setHit(Integer hit) {
        this.hit = hit;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
        SimpleDateFormat format = new SimpleDateFormat("MM-dd");
        this.timeStr = format.format(time);
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }
}