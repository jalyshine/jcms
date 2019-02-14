package cn.jaly.admin.controller;

import cn.jaly.admin.entity.Admin;
import cn.jaly.admin.entity.AdminRole;
import cn.jaly.admin.service.AdminRoleService;
import cn.jaly.admin.service.AdminService;
import cn.jaly.content.entity.AttachIndex;
import cn.jaly.content.service.AttachIndexService;
import cn.jaly.utils.annotations.Token;
import cn.jaly.utils.common.BasicUtils;
import cn.jaly.utils.common.ResultBean;
import cn.jaly.utils.shiro.AdminRealm;
import cn.jaly.utils.shiro.StockHashedCredentialsMatcher;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/Admin/")
public class AdminHandler {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminRoleService adminRoleService;

    @Autowired
    private AdminRealm adminRealm;

    @Autowired
    private AttachIndexService attachIndexService;

    @RequestMapping("list")
    public String list(Map<String, Object> request,
                       @RequestParam(value = "role", required = false) Integer roleId) {
        List<Admin> admins = adminService.getAllByAdminRoleId(roleId);
        request.put("admins", admins);
        return "admin/admin_list";
    }

    @ResponseBody
    @RequestMapping(value = "check", method = RequestMethod.POST)
    public String check(@RequestParam("name") String userName) {
        Integer id = adminService.getIdByUserName(userName);
        return id == null ? "0" : String.valueOf(id);
    }

    @Token(save = true)
    @RequestMapping(value = "{input}", method = RequestMethod.GET)
    public String input(@PathVariable("input") String input, Map<String, Object> request,
                        @RequestParam(value = "id", required = false) Integer id) {
        if(("add".equals(input) && id == null) || ("edit".equals(input) && id != null)) {
            List<AdminRole> adminRoles = adminRoleService.getAllEnabled();
            request.put("adminRoles", adminRoles);
            Admin admin = new Admin();
            if (id != null) {
                admin = adminService.getByIdWithRole(id);
            }
            request.put("admin", admin);
            return "admin/admin_input";
        }
        return null;
    }

    @Token(remove = true)
    @RequestMapping(value = "{save}", method = RequestMethod.POST)
    public String save(@PathVariable("save") String save, Admin admin) {
        Integer id = admin.getId();
        if(("add".equals(save) && id == null) || ("edit".equals(save) && id != null)) {
            if (admin.getId() == null) {
                generatePassword(admin, true);
            } else {
                String password = admin.getPassword();
                if ("".equals(password.trim())) {
                    admin.setPassword(null);
                } else {
                    generatePassword(admin, true);
                }
            }
            adminService.save(admin);
            return "redirect:/admin/Admin/list";
        }
        return null;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") Integer id) {
        adminService.delete(id);
        return "redirect:/admin/Admin/list";
    }

    /**
     * 显示管理员信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "info", method = RequestMethod.GET)
    public String adminInfo(Map<String, Object> request) {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            Object userName = currentUser.getPrincipal();
            Admin admin = adminService.getByUserName(userName.toString());
            request.put("admin", admin);
        }
        return "admin/admin_info";
    }

    /**
     * 更新个人信息
     * @param admin
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "info", method = RequestMethod.POST)
    public String updateInfo(Admin admin) {
        adminService.save(admin);
        // 关联上传的头像文件
        String face = admin.getFace();
        AttachIndex attachIndex = new AttachIndex();
        attachIndex.setModule("admin");
        attachIndex.setHost("admin-" + admin.getId());
        attachIndexService.save(face, attachIndex);
        return new ResultBean(0).toJson();
    }

    /**
     * 显示修改密码页面
     *
     * @param request
     * @return
     */
    @Token(save = true)
    @RequestMapping(value = "password", method = RequestMethod.GET)
    public String adminPassword(Map<String, Object> request) {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            Object userName = currentUser.getPrincipal();
            Admin admin = adminService.getByUserName(userName.toString());
            request.put("admin", admin);
        }
        return "admin/admin_password";
    }

    /**
     * 修改管理员密码
     *
     * @param admin
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "password", method = RequestMethod.POST)
    public String updatePassword(Admin admin, String oldPassword, String newPassword) {
        // 数据库中原始密码
        String preEncodePassword = adminService.getByIdWithRole(admin.getId()).getPassword();
        // 将后台输入的旧密码进行加密
        admin.setPassword(oldPassword);
        generatePassword(admin, false);
        String nowEncodePassword = admin.getPassword();

        ResultBean resultBean = new ResultBean(0);
        if(!preEncodePassword.equals(nowEncodePassword)){
            resultBean.setCode(1);
        } else {
            admin.setPassword(newPassword);
            generatePassword(admin, true);
            adminService.save(admin);
        }
        return resultBean.toJson();
    }


    /**
     * 根据用户输入，生成加密后的密码
     *
     * @param admin
     * @param newEncrypt 是否生成新的加密因子
     * @return
     */
    private void generatePassword(Admin admin, boolean newEncrypt) {
        StockHashedCredentialsMatcher
                matcher = (StockHashedCredentialsMatcher) adminRealm.getCredentialsMatcher();
        if (newEncrypt) {
            admin.setEncrypt(BasicUtils.getRadomEncrypt(6));
        }
        String psd = matcher.getHashProvidedCredentials(admin.getPassword(), admin.getEncrypt());
        admin.setPassword(psd);
    }

}
