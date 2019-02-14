package cn.jaly.link.entity;

import java.util.Date;

public class FriendlyLink {
    private Integer id;

    private Byte linkFrom;

    private String name;

    private String url;

    private String logo;

    private String description;

    private String userName;

    private String phone;

    private Boolean elite;

    private Boolean passed;

    private Date updateTime;

    private Integer typeId;

    private Integer siteId;

    private FriendlyLinkType friendlyLinkType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getLinkFrom() {
        return linkFrom;
    }

    public void setLinkFrom(Byte linkFrom) {
        this.linkFrom = linkFrom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Boolean getElite() {
        return elite;
    }

    public void setElite(Boolean elite) {
        this.elite = elite;
    }

    public Boolean getPassed() {
        return passed;
    }

    public void setPassed(Boolean passed) {
        this.passed = passed;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public FriendlyLinkType getFriendlyLinkType() {
        return friendlyLinkType;
    }

    public void setFriendlyLinkType(FriendlyLinkType friendlyLinkType) {
        this.friendlyLinkType = friendlyLinkType;
    }
}