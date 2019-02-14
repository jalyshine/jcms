package cn.jaly.admin.controller;

import cn.jaly.admin.entity.BackMenu;
import cn.jaly.admin.service.AdminMenuService;
import cn.jaly.admin.service.BackMenuService;
import cn.jaly.utils.common.Constant;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/AdminMenu/")
public class AdminMenuHandler {

    @Autowired
    private AdminMenuService adminMenuService;

    @Autowired
    private BackMenuService backMenuService;

    @RequestMapping(value = "manage", method = RequestMethod.GET)
    public String list(Map<String, Object> request, HttpSession session){
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            Integer adminId = (Integer) session.getAttribute(Constant.CURRENT_ADMIN);
            List<BackMenu> backMenus = backMenuService.getAllParents();
            List<BackMenu> menus = adminMenuService.initBackMenuList(adminId, backMenus);
            request.put("menus", menus);
        }
        return "admin/admin_menu";
    }

    @RequestMapping(value = "manage", method = RequestMethod.POST)
    public String setAdminMenu(HttpSession session, Integer[] mids){
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            Integer id = (Integer) session.getAttribute(Constant.CURRENT_ADMIN);
            adminMenuService.setAdminMenu(id, mids);
        }
        return "redirect:/admin/Admin/home";
    }

}
