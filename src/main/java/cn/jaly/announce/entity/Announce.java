package cn.jaly.announce.entity;

import cn.jaly.utils.common.DateTimeUtils;

import java.util.Date;

public class Announce {
    private Integer id;

    private String title;

    private String content;

    private Integer hits;

    private String userName;

    private Date startTime;

    private String startTimeStr;

    private Date endTime;

    private String endTimeStr;

    private Date updateTime;

    private Integer siteId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
        this.startTimeStr = DateTimeUtils.formatFull(startTime);
    }

    public String getStartTimeStr() {
        return startTimeStr;
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
        this.startTime = DateTimeUtils.parseFull(startTimeStr);
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
        this.endTimeStr = DateTimeUtils.formatFull(endTime);
    }

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
        this.endTime = DateTimeUtils.parseFull(endTimeStr);
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    @Override
    public String toString() {
        return "Announce{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", hits=" + hits +
                ", userName='" + userName + '\'' +
                ", startTime=" + startTime +
                ", startTimeStr='" + startTimeStr + '\'' +
                ", endTime=" + endTime +
                ", endTimeStr='" + endTimeStr + '\'' +
                ", updateTime=" + updateTime +
                ", siteId=" + siteId +
                '}';
    }
}