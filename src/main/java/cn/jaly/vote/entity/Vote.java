package cn.jaly.vote.entity;

import cn.jaly.utils.caches.ListOfJson;
import cn.jaly.utils.common.DateTimeUtils;
import com.google.gson.Gson;

import java.util.Date;
import java.util.List;

public class Vote {
    private Integer id;

    private String title;

    private String description;

    private Integer creditPoint;

    private Date fromTime;

    private String fromTimeStr;

    private Date toTime;

    private String toTimeStr;

    private Short intervalDays;

    private Short maxVal;

    private Short minVal;

    private Boolean disabled;

    private Boolean isMultiple;

    private Boolean allowGuest;

    private Boolean allowView;

    private Date updateTime;

    private Integer siteId;

    private String items;

    private List<VoteOption> voteOptions;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getCreditPoint() {
        return creditPoint;
    }

    public void setCreditPoint(Integer creditPoint) {
        this.creditPoint = creditPoint;
    }

    public Date getFromTime() {
        return fromTime;
    }

    public void setFromTime(Date fromTime) {
        this.fromTime = fromTime;
        this.fromTimeStr = DateTimeUtils.formatFull(fromTime);
    }

    public String getFromTimeStr() {
        return fromTimeStr;
    }

    public void setFromTimeStr(String fromTimeStr) {
        this.fromTimeStr = fromTimeStr;
        this.fromTime = DateTimeUtils.parseFull(fromTimeStr);
    }

    public Date getToTime() {
        return toTime;
    }

    public void setToTime(Date toTime) {
        this.toTime = toTime;
        this.toTimeStr = DateTimeUtils.formatFull(toTime);
    }

    public String getToTimeStr() {
        return toTimeStr;
    }

    public void setToTimeStr(String toTimeStr) {
        this.toTimeStr = toTimeStr;
        this.toTime = DateTimeUtils.parseFull(toTimeStr);
    }

    public Short getIntervalDays() {
        return intervalDays;
    }

    public void setIntervalDays(Short intervalDays) {
        this.intervalDays = intervalDays;
    }

    public Short getMaxVal() {
        return maxVal;
    }

    public void setMaxVal(Short maxVal) {
        this.maxVal = maxVal;
    }

    public Short getMinVal() {
        return minVal;
    }

    public void setMinVal(Short minVal) {
        this.minVal = minVal;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Boolean getIsMultiple() {
        return isMultiple;
    }

    public void setIsMultiple(Boolean isMultiple) {
        this.isMultiple = isMultiple;
    }

    public Boolean getAllowGuest() {
        return allowGuest;
    }

    public void setAllowGuest(Boolean allowGuest) {
        this.allowGuest = allowGuest;
    }

    public Boolean getAllowView() {
        return allowView;
    }

    public void setAllowView(Boolean allowView) {
        this.allowView = allowView;
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

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items == null ? null : items.trim();
        Gson gson = new Gson();
        this.voteOptions = gson.fromJson(items, new ListOfJson<VoteOption>(VoteOption.class));
    }

    public List<VoteOption> getVoteOptions() {
        return voteOptions;
    }

    public void setVoteOptions(List<VoteOption> voteOptions) {
        this.voteOptions = voteOptions;
        Gson gson = new Gson();
        this.items = gson.toJson(voteOptions);
    }
}