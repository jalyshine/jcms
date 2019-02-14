package cn.jaly.admin.entity;

import java.io.Serializable;

public class AdminRolePrivacy implements Serializable{
    private Integer id;

    private Integer adminRoleId;

    private String module;

    private String entity;

    private String action;

    private String data;

    private Integer siteId;

    private AdminRole adminRole;

    public AdminRolePrivacy() {

    }

    public AdminRolePrivacy(BackMenu backMenu) {
        this.module = backMenu.getModule();
        this.entity = backMenu.getEntity();
        this.action = backMenu.getAction();
        this.data = backMenu.getData();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdminRoleId() {
        return adminRoleId;
    }

    public void setAdminRoleId(Integer adminRoleId) {
        this.adminRoleId = adminRoleId;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module == null ? null : module.trim();
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity == null ? null : entity.trim();
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action == null ? null : action.trim();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data == null ? null : data.trim();
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public AdminRole getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(AdminRole adminRole) {
        this.adminRole = adminRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdminRolePrivacy privacy = (AdminRolePrivacy) o;

        if (module != null ? !module.equals(privacy.module) : privacy.module != null) return false;
        if (entity != null ? !entity.equals(privacy.entity) : privacy.entity != null) return false;
        if (action != null ? !action.equals(privacy.action) : privacy.action != null) return false;
        return data != null ? data.equals(privacy.data) : privacy.data == null;
    }

    @Override
    public int hashCode() {
        int result = module != null ? module.hashCode() : 0;
        result = 31 * result + (entity != null ? entity.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    public boolean equalsBackMenu(BackMenu backMenu) {
        if (module != null ? !module.equals(backMenu.getModule()) : backMenu.getModule() != null) return false;
        if (entity != null ? !entity.equals(backMenu.getEntity()) : backMenu.getEntity() != null) return false;
        if (action != null ? !action.equals(backMenu.getAction()) : backMenu.getAction() != null) return false;
        return data != null ? data.equals(backMenu.getData()) : backMenu.getData() == null;
    }
}