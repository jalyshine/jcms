package cn.jaly.comment.entity;

public class CommentSetting {
    private Integer siteId;

    private Boolean allowGuest;

    private Boolean needVerify;

    private Boolean needCode;

    private Byte addPoint;

    private Byte delPoint;

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public Boolean getAllowGuest() {
        return allowGuest;
    }

    public void setAllowGuest(Boolean allowGuest) {
        this.allowGuest = allowGuest;
    }

    public Boolean getNeedVerify() {
        return needVerify;
    }

    public void setNeedVerify(Boolean needVerify) {
        this.needVerify = needVerify;
    }

    public Boolean getNeedCode() {
        return needCode;
    }

    public void setNeedCode(Boolean needCode) {
        this.needCode = needCode;
    }

    public Byte getAddPoint() {
        return addPoint;
    }

    public void setAddPoint(Byte addPoint) {
        this.addPoint = addPoint;
    }

    public Byte getDelPoint() {
        return delPoint;
    }

    public void setDelPoint(Byte delPoint) {
        this.delPoint = delPoint;
    }
}