package cn.jaly.special.entity;

import java.util.Date;

public class Special {
    private Integer id;

    private String title;

    private String banner;

    private String thumb;

    private Boolean disabled;

    private Boolean elite;

    private String dirName;

    private String uiStyle;

    private String homeTemplate;

    private String listTemplate;

    private String showTemplate;

    private Integer listOrder;

    private String description;

    private String url;

    private Date updateTime;

    private Integer adminId;

    private Integer pictureId;

    private Integer voteId;

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

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner == null ? null : banner.trim();
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb == null ? null : thumb.trim();
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Boolean getElite() {
        return elite;
    }

    public void setElite(Boolean elite) {
        this.elite = elite;
    }

    public String getDirName() {
        return dirName;
    }

    public void setDirName(String dirName) {
        this.dirName = dirName == null ? null : dirName.trim();
    }

    public String getUiStyle() {
        return uiStyle;
    }

    public void setUiStyle(String uiStyle) {
        this.uiStyle = uiStyle == null ? null : uiStyle.trim();
    }

    public String getHomeTemplate() {
        return homeTemplate;
    }

    public void setHomeTemplate(String homeTemplate) {
        this.homeTemplate = homeTemplate == null ? null : homeTemplate.trim();
    }

    public String getListTemplate() {
        return listTemplate;
    }

    public void setListTemplate(String listTemplate) {
        this.listTemplate = listTemplate == null ? null : listTemplate.trim();
    }

    public String getShowTemplate() {
        return showTemplate;
    }

    public void setShowTemplate(String showTemplate) {
        this.showTemplate = showTemplate == null ? null : showTemplate.trim();
    }

    public Integer getListOrder() {
        return listOrder;
    }

    public void setListOrder(Integer listOrder) {
        this.listOrder = listOrder;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getPictureId() {
        return pictureId;
    }

    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }

    public Integer getVoteId() {
        return voteId;
    }

    public void setVoteId(Integer voteId) {
        this.voteId = voteId;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }
}