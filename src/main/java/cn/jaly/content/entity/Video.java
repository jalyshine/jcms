package cn.jaly.content.entity;

import cn.jaly.utils.common.DateTimeUtils;

import java.util.Date;

public class Video {
    private Integer id;

    private String title;

    private String author;

    private String authorUnit;

    private String thumb;

    private String description;

    private Boolean isLink;

    private String url;

    private String keywords;

    private Date publishTime;

    private String publishTimeStr;

    private Date updateTime;

    private Byte status;

    private Boolean sysAdd;

    private Integer categoryId;

    private VideoData videoData;

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getAuthorUnit() {
        return authorUnit;
    }

    public void setAuthorUnit(String authorUnit) {
        this.authorUnit = authorUnit == null ? null : authorUnit.trim();
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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Boolean getSysAdd() {
        return sysAdd;
    }

    public void setSysAdd(Boolean sysAdd) {
        this.sysAdd = sysAdd;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public VideoData getVideoData() {
        return videoData;
    }

    public void setVideoData(VideoData videoData) {
        this.videoData = videoData;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", authorUnit='" + authorUnit + '\'' +
                ", thumb='" + thumb + '\'' +
                ", description='" + description + '\'' +
                ", isLink=" + isLink +
                ", url='" + url + '\'' +
                ", keywords='" + keywords + '\'' +
                ", publishTime=" + publishTime +
                ", publishTimeStr='" + publishTimeStr + '\'' +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", sysAdd=" + sysAdd +
                ", categoryId=" + categoryId +
                ", videoData=" + videoData +
                '}';
    }
}