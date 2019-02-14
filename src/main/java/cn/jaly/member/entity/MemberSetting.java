package cn.jaly.member.entity;

public class MemberSetting{
    private Integer siteId;

    private Boolean allowReg;

    private Boolean selectModel;

    private Boolean needEmail;

    private Boolean needCode;

    private Boolean needPhone;

    private Boolean needAdmin;

    private Boolean integralModel;

    private Float integralPrice;

    private Integer initPoint;

    private Float initMoney;

    private Boolean showAgree;

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public Boolean getAllowReg() {
        return allowReg;
    }

    public void setAllowReg(Boolean allowReg) {
        this.allowReg = allowReg;
    }

    public Boolean getSelectModel() {
        return selectModel;
    }

    public void setSelectModel(Boolean selectModel) {
        this.selectModel = selectModel;
    }

    public Boolean getNeedEmail() {
        return needEmail;
    }

    public void setNeedEmail(Boolean needEmail) {
        this.needEmail = needEmail;
    }

    public Boolean getNeedCode() {
        return needCode;
    }

    public void setNeedCode(Boolean needCode) {
        this.needCode = needCode;
    }

    public Boolean getNeedPhone() {
        return needPhone;
    }

    public void setNeedPhone(Boolean needPhone) {
        this.needPhone = needPhone;
    }

    public Boolean getNeedAdmin() {
        return needAdmin;
    }

    public void setNeedAdmin(Boolean needAdmin) {
        this.needAdmin = needAdmin;
    }

    public Boolean getIntegralModel() {
        return integralModel;
    }

    public void setIntegralModel(Boolean integralModel) {
        this.integralModel = integralModel;
    }

    public Float getIntegralPrice() {
        return integralPrice;
    }

    public void setIntegralPrice(Float integralPrice) {
        this.integralPrice = integralPrice;
    }

    public Integer getInitPoint() {
        return initPoint;
    }

    public void setInitPoint(Integer initPoint) {
        this.initPoint = initPoint;
    }

    public Float getInitMoney() {
        return initMoney;
    }

    public void setInitMoney(Float initMoney) {
        this.initMoney = initMoney;
    }

    public Boolean getShowAgree() {
        return showAgree;
    }

    public void setShowAgree(Boolean showAgree) {
        this.showAgree = showAgree;
    }
}