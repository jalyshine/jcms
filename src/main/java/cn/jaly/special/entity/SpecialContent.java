package cn.jaly.special.entity;

import cn.jaly.utils.common.DateTimeUtils;

import java.util.Date;

public class SpecialContent {
    private Integer id;

    private String title;

    private String thumb;

    private String description;

    private Boolean isLink;

    private String url;

    private String keywords;

    private Date publishTime;

    private String publishTimeStr;

    private Date updateTime;

    private Boolean isData;

    private String contentEntity;

    private Integer contentId;

    private Integer specialTypeId;

    private Integer specialId;

    private SpecialType specialType;

    private SpecialContentData specialContentData;

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

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb == null ? null : thumb.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Boolean getIsLink() {
        return isLink;
    }

    public void setIsLink(Boolean isLink) {
        this.isLink = isLink;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
        this.publishTimeStr = DateTimeUtils.formatFull(publishTime);
    }

    public String getPublishTimeStr() {
        return publishTimeStr;
    }

    public void setPublishTimeStr(String publishTimeStr) {
        this.publishTimeStr = publishTimeStr;
        this.publishTime = DateTimeUtils.parseFull(publishTimeStr);
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getIsData() {
        return isData;
    }

    public void setIsData(Boolean isData) {
        this.isData = isData;
    }

    public String getContentEntity() {
        return contentEntity;
    }

    public void setContentEntity(String contentEntity) {
        this.contentEntity = contentEntity == null ? null : contentEntity.trim();
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public Integer getSpecialTypeId() {
        return specialTypeId;
    }

    public void setSpecialTypeId(Integer specialTypeId) {
        this.specialTypeId = specialTypeId;
    }

    public Integer getSpecialId() {
        return specialId;
    }

    public void setSpecialId(Integer specialId) {
        this.specialId = specialId;
    }

    public SpecialType getSpecialType() {
        return specialType;
    }

    public void setSpecialType(SpecialType specialType) {
        this.specialType = specialType;
    }

    public SpecialContentData getSpecialContentData() {
        return specialContentData;
    }

    public void setSpecialContentData(SpecialContentData specialContentData) {
        this.specialContentData = specialContentData;
    }

    @Override
    public String toString() {
        return "SpecialContent{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", thumb='" + thumb + '\'' +
                ", description='" + description + '\'' +
                ", isLink=" + isLink +
                ", url='" + url + '\'' +
                ", keywords='" + keywords + '\'' +
                ", publishTime=" + publishTime +
                ", publishTimeStr='" + publishTimeStr + '\'' +
                ", updateTime=" + updateTime +
                ", isData=" + isData +
                ", contentEntity='" + contentEntity + '\'' +
                ", contentId=" + contentId +
                ", specialTypeId=" + specialTypeId +
                ", specialId=" + specialId +
                ", specialType=" + specialType +
                ", specialContentData=" + specialContentData +
                '}';
    }
}