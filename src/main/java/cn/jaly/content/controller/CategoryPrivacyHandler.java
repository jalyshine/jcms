package cn.jaly.content.controller;

import cn.jaly.admin.entity.AdminRole;
import cn.jaly.admin.entity.Site;
import cn.jaly.admin.service.AdminRoleService;
import cn.jaly.admin.service.SiteService;
import cn.jaly.content.entity.Category;
import cn.jaly.content.service.CategoryPrivacyService;
import cn.jaly.content.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/content/CategoryPrivacy/")
public class CategoryPrivacyHandler {

    @Autowired
    private CategoryPrivacyService categoryPrivacyService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SiteService siteService;

    @Autowired
    private AdminRoleService adminRoleService;

    @RequestMapping(value = "manage", method = RequestMethod.GET)
    public String privacy(Map<String, Object> request, @RequestParam("role")Integer roleId,
                          @RequestParam(value = "site", required = false) Integer siteId){
        List<Site> sites = siteService.getByAdminPrivacy(roleId);
        request.put("sites", sites);

        if(siteId != null){
            List<Category> categories = categoryService.treeForListBySiteId(siteId);
            categories = categoryPrivacyService.initCategoryList(roleId, categories);
            request.put("categories", categories);
        }

        AdminRole adminRole = adminRoleService.getById(roleId);
        request.put("roleName", adminRole.getName());

        return "content/category_privacy";
    }

    @RequestMapping(value = "manage", method = RequestMethod.POST)
    public String save(Integer role, Integer[] cid, String[] action){
        categoryPrivacyService.save(role, cid, action);
        return "redirect:/admin/AdminRole/list";
    }
}
