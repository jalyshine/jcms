package cn.jaly.member.entity;

public class MemberSettingWithBLOBs extends MemberSetting {
    private String regAgree;

    private String emailVerify;

    private String emailPassword;

    public String getRegAgree() {
        return regAgree;
    }

    public void setRegAgree(String regAgree) {
        this.regAgree = regAgree == null ? null : regAgree.trim();
    }

    public String getEmailVerify() {
        return emailVerify;
    }

    public void setEmailVerify(String emailVerify) {
        this.emailVerify = emailVerify == null ? null : emailVerify.trim();
    }

    public String getEmailPassword() {
        return emailPassword;
    }

    public void setEmailPassword(String emailPassword) {
        this.emailPassword = emailPassword == null ? null : emailPassword.trim();
    }
}