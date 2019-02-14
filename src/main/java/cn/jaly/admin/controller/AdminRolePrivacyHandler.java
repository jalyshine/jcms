package cn.jaly.admin.controller;

import cn.jaly.admin.entity.AdminRole;
import cn.jaly.admin.entity.BackMenu;
import cn.jaly.admin.entity.Site;
import cn.jaly.admin.service.AdminRolePrivacyService;
import cn.jaly.admin.service.AdminRoleService;
import cn.jaly.admin.service.BackMenuService;
import cn.jaly.admin.service.SiteService;
import cn.jaly.utils.shiro.FilterChainDefinitionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/AdminRolePrivacy/")
public class AdminRolePrivacyHandler {

    @Autowired
    private AdminRolePrivacyService adminRolePrivacyService;

    @Autowired
    private BackMenuService backMenuService;

    @Autowired
    private AdminRoleService adminRoleService;

    @Autowired
    private FilterChainDefinitionsService filterChainDefinitionsService;

    @Autowired
    private SiteService siteService;

    @RequestMapping(value = "manage", method = RequestMethod.GET)
    public String privacy(Map<String, Object> request, @RequestParam("role")Integer roleId,
                          @RequestParam(value = "site", required = false) Integer siteId){
        if(siteId != null){
            List<BackMenu> backMenus = backMenuService.getAll();
            backMenus = adminRolePrivacyService.initBackMenuList(roleId, siteId, backMenus);
            request.put("menuList", backMenus);
        }
        AdminRole adminRole = adminRoleService.getById(roleId);
        request.put("roleName", adminRole.getName());
        List<Site> sites = siteService.queryForList(null);
        request.put("sites", sites);
        return "admin/admin_role_privacy";
    }

    @RequestMapping(value = "manage", method = RequestMethod.POST)
    public String save(Integer role, Integer site, Integer[] mids){
        adminRolePrivacyService.save(role, site, mids);
        // 重构权限
        filterChainDefinitionsService.updatePermission();
        return "redirect:/admin/AdminRole/list";
    }

}
