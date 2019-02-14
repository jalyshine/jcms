package cn.jaly.member.entity;

import cn.jaly.utils.common.DateTimeUtils;

import java.util.Date;

public class Member{
    private Integer id;

    private String userName;

    private String password;

    private String encrypt;

    private String email;

    private String phone;

    private Date regTime;

    private String regIp;

    private Date lastLoginTime;

    private String lastLoginIp;

    private Integer loginTimes;

    private Boolean hasMessage;

    private Boolean isLock;

    private Byte vipLevel;

    private Date vipOverTime;

    private String vipOverTimeStr;

    private Double amount;

    private Integer point;

    private Integer memberGroupId;

    private Integer siteId;

    private MemberDetail memberDetail;

    private MemberGroup memberGroup;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(String encrypt) {
        this.encrypt = encrypt == null ? null : encrypt.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public String getRegIp() {
        return regIp;
    }

    public void setRegIp(String regIp) {
        this.regIp = regIp == null ? null : regIp.trim();
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp == null ? null : lastLoginIp.trim();
    }

    public Integer getLoginTimes() {
        return loginTimes;
    }

    public void setLoginTimes(Integer loginTimes) {
        this.loginTimes = loginTimes;
    }

    public Boolean getHasMessage() {
        return hasMessage;
    }

    public void setHasMessage(Boolean hasMessage) {
        this.hasMessage = hasMessage;
    }

    public Boolean getIsLock() {
        return isLock;
    }

    public void setIsLock(Boolean isLock) {
        this.isLock = isLock;
    }

    public Byte getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(Byte vipLevel) {
        this.vipLevel = vipLevel;
    }

    public Date getVipOverTime() {
        return vipOverTime;
    }

    public void setVipOverTime(Date vipOverTime) {
        this.vipOverTime = vipOverTime;
        this.vipOverTimeStr = DateTimeUtils.formatFull(vipOverTime);
    }

    public String getVipOverTimeStr() {
        return vipOverTimeStr;
    }

    public void setVipOverTimeStr(String vipOverTimeStr) {
        this.vipOverTimeStr = vipOverTimeStr;
        this.vipOverTime = DateTimeUtils.parseFull(vipOverTimeStr);
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getMemberGroupId() {
        return memberGroupId;
    }

    public void setMemberGroupId(Integer memberGroupId) {
        this.memberGroupId = memberGroupId;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public MemberDetail getMemberDetail() {
        return memberDetail;
    }

    public void setMemberDetail(MemberDetail memberDetail) {
        this.memberDetail = memberDetail;
    }

    public MemberGroup getMemberGroup() {
        return memberGroup;
    }

    public void setMemberGroup(MemberGroup memberGroup) {
        this.memberGroup = memberGroup;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", memberDetail=" + memberDetail +
                ", memberGroup=" + memberGroup +
                '}';
    }
}