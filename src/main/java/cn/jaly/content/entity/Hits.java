package cn.jaly.content.entity;

import java.util.Date;

public class Hits {
    private String hitsId;

    private Integer total;

    private Integer yesterday;

    private Integer today;

    private Integer week;

    private Integer month;

    private Date updateTime;

    public String getHitsId() {
        return hitsId;
    }

    public void setHitsId(String hitsId) {
        this.hitsId = hitsId == null ? null : hitsId.trim();
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getYesterday() {
        return yesterday;
    }

    public void setYesterday(Integer yesterday) {
        this.yesterday = yesterday;
    }

    public Integer getToday() {
        return today;
    }

    public void setToday(Integer today) {
        this.today = today;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}