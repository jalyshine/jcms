package cn.jaly.member.entity;

import cn.jaly.utils.common.DateTimeUtils;

import java.io.Serializable;
import java.util.Date;

public class MemberDetail implements Serializable {
    private Integer memberId;

    private String area;

    private Date birthday;

    private String birthdayStr;

    private String face;

    private String unit;

    private String nickName;

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
        this.birthdayStr = DateTimeUtils.formatSimple(birthday);
    }

    public String getBirthdayStr() {
        return birthdayStr;
    }

    public void setBirthdayStr(String birthdayStr) {
        this.birthdayStr = birthdayStr;
        this.birthday = DateTimeUtils.parseSimple(birthdayStr);
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face == null ? null : face.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    @Override
    public String toString() {
        return "MemberDetail{" +
                "memberId=" + memberId +
                ", area='" + area + '\'' +
                ", birthday=" + birthday +
                ", birthdayStr='" + birthdayStr + '\'' +
                ", face='" + face + '\'' +
                ", unit='" + unit + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}