package cn.jaly.admin.entity;

import cn.jaly.utils.caches.ListOfJson;
import com.google.gson.Gson;

import java.util.List;

public class AdminMenu{
    private Integer adminId;

    private String menus;

    private List<Integer> menuIds;

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getMenus() {
        return menus;
    }

    public void setMenus(String menus) {
        this.menus = menus == null ? null : menus.trim();
        Gson gson = new Gson();
        this.menuIds = gson.fromJson(menus, new ListOfJson<Integer>(Integer.class));
    }

    public List<Integer> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<Integer> menuIds) {
        this.menuIds = menuIds;
        Gson gson = new Gson();
        this.menus = gson.toJson(menuIds);
    }
}