package cn.jaly.admin.entity;

import java.io.Serializable;
import java.util.List;

public class AdminRole implements Serializable{
    private Integer id;

    private String name;

    private String description;

    private Short listOrder;

    private Boolean disabled;

    private List<AdminRolePrivacy> adminRolePrivacies;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Short getListOrder() {
        return listOrder;
    }

    public void setListOrder(Short listOrder) {
        this.listOrder = listOrder;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public List<AdminRolePrivacy> getAdminRolePrivacies() {
        return adminRolePrivacies;
    }

    public void setAdminRolePrivacies(List<AdminRolePrivacy> adminRolePrivacies) {
        this.adminRolePrivacies = adminRolePrivacies;
    }

    @Override
    public String toString() {
        return "AdminRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", listOrder=" + listOrder +
                ", disabled=" + disabled +
                ", adminRolePrivacies=" + adminRolePrivacies +
                '}';
    }
}