package cn.jaly.content.controller;

import cn.jaly.admin.entity.Admin;
import cn.jaly.admin.service.AdminService;
import cn.jaly.content.entity.Category;
import cn.jaly.content.entity.ContentMenu;
import cn.jaly.content.service.CategoryService;
import cn.jaly.utils.common.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 显示内容列表
 */
@Controller
@RequestMapping("/content/Category/")
public class ContentListHandler {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AdminService adminService;

    /**
     * 内容列表的边栏
     * @param request
     * @param session
     * @return
     */
    @RequestMapping("nav")
    public String nav(Map<String, Object> request, HttpSession session) {
        Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
        Integer adminId = (Integer) session.getAttribute(Constant.CURRENT_ADMIN);
        Admin admin = adminService.getById(adminId);
        List<ContentMenu> contentNav = categoryService.getContentNav(siteId, admin.getAdminRoleId());
        request.put("contentMenu", contentNav);
        return "content/content_list";
    }

    /**
     * 内容列表的首页
     * @param request
     * @param session
     * @return
     */
    @RequestMapping("search")
    public String search(Map<String, Object> request, HttpSession session) {
        Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
        List<Category> categoryTree = categoryService.treeForListBySiteId(siteId);
        request.put("categoryTree", categoryTree);
        return "content/content_search";
    }

}
