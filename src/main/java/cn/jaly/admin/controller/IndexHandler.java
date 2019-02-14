package cn.jaly.admin.controller;

import cn.jaly.admin.entity.Admin;
import cn.jaly.admin.entity.BackMenu;
import cn.jaly.admin.entity.Site;
import cn.jaly.admin.service.AdminRolePrivacyService;
import cn.jaly.admin.service.AdminService;
import cn.jaly.admin.service.BackMenuService;
import cn.jaly.admin.service.SiteService;
import cn.jaly.utils.common.Constant;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class IndexHandler {

    @Autowired
    private SiteService siteService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private BackMenuService backMenuService;

    @Autowired
    private AdminRolePrivacyService adminRolePrivacyService;

    /**
     * 后台首页
     * @param request
     * @param siteId
     * @return
     */
    @RequestMapping("/index")
    public String index(Map<String, Object> request, HttpSession session,
                        HttpServletRequest servletRequest,
                        @RequestParam(value = "site", required = false) Integer siteId){
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            Integer id = (Integer) session.getAttribute(Constant.CURRENT_ADMIN);
            Admin admin = adminService.getById(id);
            Integer roleId = admin.getAdminRoleId();
            if(siteId == null){
                if(roleId == 1){
                    siteId = 1;
                } else {
                    List<Integer> siteIds = adminRolePrivacyService.getOwnSiteIdsByRoleId(roleId);
                    if(siteIds == null || siteIds.isEmpty()){
                        return "404";
                    } else {
                        siteId = siteIds.get(0);
                    }
                }
            }
            List<BackMenu> navs = backMenuService.getIndexNavByPrivacy(roleId, siteId);
            if(navs == null || navs.isEmpty()){
                return "404";
            }
            request.put("navs", navs);
            request.put("admin", admin);
            // 将siteId加入session
            session.setAttribute(Constant.CURRENT_SITE, siteId);
            Site site = siteService.getById(siteId);
            request.put("uiStyle", site.getUiStyle());

            String contextPath = servletRequest.getContextPath() + "/";
            session.setAttribute("base", contextPath);
            return "index";
        } else {
            return "login";
        }
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

}
