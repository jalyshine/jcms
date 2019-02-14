package cn.jaly.admin.service;

import cn.jaly.admin.dao.AdminMapper;
import cn.jaly.admin.dao.AdminMenuMapper;
import cn.jaly.admin.dao.AdminRolePrivacyMapper;
import cn.jaly.admin.entity.Admin;
import cn.jaly.admin.entity.AdminMenu;
import cn.jaly.admin.entity.BackMenu;
import cn.jaly.admin.entity.BackMenuExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AdminMenuService {

    @Autowired
    private AdminMenuMapper adminMenuMapper;

    /**
     * 初始化快捷菜单设置页面
     * 采用display字段表示菜单是否选中
     * @param adminId     管理员ID
     * @param backMenus   该管理员可以使用的菜单
     * @return
     */
    @Transactional(readOnly = true)
    public List<BackMenu> initBackMenuList(Integer adminId, List<BackMenu> backMenus) {
        // 初始状态均为未选中
        for(BackMenu backMenu : backMenus){
            backMenu.setDisplay(false);
        }
        // 查询数据库，该管理员是否已经有选中的快捷菜单
        AdminMenu adminMenu = adminMenuMapper.selectByPrimaryKey(adminId);
        if(adminMenu != null){
            List<Integer> menuIds = adminMenu.getMenuIds();
            for (BackMenu backMenu : backMenus) {
                for (Integer id : menuIds) {
                    if (id == backMenu.getId()) {
                        backMenu.setDisplay(true);
                    }
                }
            }
        }
        return backMenus;
    }

    @Transactional(readOnly = true)
    public List<BackMenu> getAdminMenuByAdminId(Integer adminId, List<BackMenu> backMenus) {
        AdminMenu adminMenu = adminMenuMapper.selectByPrimaryKey(adminId);
        if (adminMenu == null) {
            return null;
        }
        List<Integer> menuIds = adminMenu.getMenuIds();
        if (menuIds == null || menuIds.isEmpty()) {
            return null;
        }
        BackMenuExample example = new BackMenuExample();
        BackMenuExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(menuIds);

        List<BackMenu> menus = new ArrayList<>();
        for (BackMenu backMenu : backMenus) {
            for (Integer id : menuIds) {
                if (id == backMenu.getId()) {
                    menus.add(backMenu);
                    break;
                }
            }
        }
        return menus;
    }

    /**
     * 设置快捷菜单
     *
     * @param adminId
     * @param backMenuIds
     */
    @Transactional
    public void setAdminMenu(Integer adminId, Integer[] backMenuIds) {
        AdminMenu adminMenu = adminMenuMapper.selectByPrimaryKey(adminId);
        if (adminMenu == null) {
            adminMenu = new AdminMenu();
            adminMenu.setAdminId(adminId);
            adminMenu.setMenuIds(Arrays.asList(backMenuIds));
            adminMenuMapper.insert(adminMenu);
        } else {
            adminMenu.setMenuIds(Arrays.asList(backMenuIds));
            adminMenuMapper.updateByPrimaryKey(adminMenu);
        }
    }
}
