package cn.jaly.member.entity;

import java.io.Serializable;

public class MemberGroup implements Serializable {
    private Integer id;

    private String name;

    private Boolean isCore;

    private Byte starNum;

    private Integer maxPoint;

    private Integer memberCount;

    private Boolean allowSendMessage;

    private Integer allowMessageNum;

    private Boolean allowVisit;

    private Boolean allowPost;

    private Boolean allowPostVerify;

    private Integer allowPostNum;

    private Boolean allowSearch;

    private Boolean allowUpload;

    private Boolean allowUpgrade;

    private Double priceDay;

    private Double priceMonth;

    private Double priceYear;

    private String icon;

    private String userNameColor;

    private String description;

    private Integer listOrder;

    private Boolean disabled;

    private Integer siteId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Boolean getIsCore() {
        return isCore;
    }

    public void setIsCore(Boolean isCore) {
        this.isCore = isCore;
    }

    public Byte getStarNum() {
        return starNum;
    }

    public void setStarNum(Byte starNum) {
        this.starNum = starNum;
    }

    public Integer getMaxPoint() {
        return maxPoint;
    }

    public void setMaxPoint(Integer maxPoint) {
        this.maxPoint = maxPoint;
    }

    public Integer getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(Integer memberCount) {
        this.memberCount = memberCount;
    }

    public Boolean getAllowSendMessage() {
        return allowSendMessage;
    }

    public void setAllowSendMessage(Boolean allowSendMessage) {
        this.allowSendMessage = allowSendMessage;
    }

    public Integer getAllowMessageNum() {
        return allowMessageNum;
    }

    public void setAllowMessageNum(Integer allowMessageNum) {
        this.allowMessageNum = allowMessageNum;
    }

    public Boolean getAllowVisit() {
        return allowVisit;
    }

    public void setAllowVisit(Boolean allowVisit) {
        this.allowVisit = allowVisit;
    }

    public Boolean getAllowPost() {
        return allowPost;
    }

    public void setAllowPost(Boolean allowPost) {
        this.allowPost = allowPost;
    }

    public Boolean getAllowPostVerify() {
        return allowPostVerify;
    }

    public void setAllowPostVerify(Boolean allowPostVerify) {
        this.allowPostVerify = allowPostVerify;
    }

    public Integer getAllowPostNum() {
        return allowPostNum;
    }

    public void setAllowPostNum(Integer allowPostNum) {
        this.allowPostNum = allowPostNum;
    }

    public Boolean getAllowSearch() {
        return allowSearch;
    }

    public void setAllowSearch(Boolean allowSearch) {
        this.allowSearch = allowSearch;
    }

    public Boolean getAllowUpload() {
        return allowUpload;
    }

    public void setAllowUpload(Boolean allowUpload) {
        this.allowUpload = allowUpload;
    }

    public Boolean getAllowUpgrade() {
        return allowUpgrade;
    }

    public void setAllowUpgrade(Boolean allowUpgrade) {
        this.allowUpgrade = allowUpgrade;
    }

    public Double getPriceDay() {
        return priceDay;
    }

    public void setPriceDay(Double priceDay) {
        this.priceDay = priceDay;
    }

    public Double getPriceMonth() {
        return priceMonth;
    }

    public void setPriceMonth(Double priceMonth) {
        this.priceMonth = priceMonth;
    }

    public Double getPriceYear() {
        return priceYear;
    }

    public void setPriceYear(Double priceYear) {
        this.priceYear = priceYear;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getUserNameColor() {
        return userNameColor;
    }

    public void setUserNameColor(String userNameColor) {
        this.userNameColor = userNameColor == null ? null : userNameColor.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getListOrder() {
        return listOrder;
    }

    public void setListOrder(Integer listOrder) {
        this.listOrder = listOrder;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }
}