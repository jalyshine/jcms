package cn.jaly.admin.controller;

import cn.jaly.admin.entity.*;
import cn.jaly.admin.service.*;
import cn.jaly.content.entity.Model;
import cn.jaly.content.service.ModelService;
import cn.jaly.utils.common.Constant;
import cn.jaly.utils.common.ExceptionUtils;
import cn.jaly.utils.common.ResultBean;
import cn.jaly.utils.explorer.HttpUtils;
import cn.jaly.utils.shiro.CustomizedToken;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/Admin/")
public class AdminAccountHandler {

    private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @Autowired
    private SiteService siteService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private BackMenuService backMenuService;

    @Autowired
    private AdminMenuService adminMenuService;

    @Autowired
    private ModelService modelService;

    @Autowired
    private LoginTimeService loginTimeService;

    @Autowired
    private SiteHitService siteHitService;

    @Autowired
    private SettingService settingService;

    /**
     * 锁定后台界面
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "lckUI", method = RequestMethod.POST)
    public String lockUI() {
        Subject currentUser = SecurityUtils.getSubject();
        if(currentUser.isAuthenticated()){
            currentUser.getSession().stop();
            currentUser.logout();
        }
        return new ResultBean(0).toJson();
    }

    /**
     * 解锁后台界面
     * @param userName
     * @param password
     * @param session
     * @param servletRequest
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "unlckUI", method = RequestMethod.POST)
    public String unlockUI(@RequestParam(value = "userName") String userName,
                           @RequestParam(value = "password") String password,
                           HttpSession session,
                           HttpServletRequest servletRequest) {
        ResultBean resultBean = new ResultBean(0);
        int status = login(userName, password, session, servletRequest);
        if(status != Constant.STATUS_SUCCESS){
            resultBean.setCode(1);
            resultBean.setData(status + "");
        }
        return resultBean.toJson();
    }

    /**
     * 管理员登录
     * @param userName
     * @param password
     * @param session
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String adminLogin(@RequestParam("userName") String userName,
                             @RequestParam("password") String password,
                             HttpSession session, HttpServletRequest request) {
        ResultBean resultBean = new ResultBean(0);
        // 检查是否处于登录恢复期
        // 0 获取最大登录次数
        Integer maxLoginTimes = null;
        try {
            maxLoginTimes = settingService.querySecuritySetting().getMaxLoginTimes();
        } catch (DocumentException e) {
            logger.error(ExceptionUtils.formatExceptionInfo(e));
        }
        // 1 先检查数据库，有没有近期失败登录的记录
        LoginTimes loginTimes = loginTimeService.getByUserName(userName, true);
        if(loginTimes != null && loginTimes.getTimes() >= maxLoginTimes){
            resultBean.setCode(1);
            long preLoginTime = loginTimes.getLoginTime().getTime();
            long now = System.currentTimeMillis();
            int remainTime = (int) (60 - ((now - preLoginTime) / (60 * 1000)));
            // 未到60分钟，不允许登录。每次登录，返回剩余分钟数。
            if(remainTime > 0){
                resultBean.setData(remainTime);
                return resultBean.toJson();
            }
            // 到60分钟，登录次数设为1.
            else {
                loginTimes.setTimes((byte) 0);
                loginTimeService.save(loginTimes);
            }
        }
        // 登录获取登录状态
        int status = login(userName, password, session, request);
        if(status == Constant.STATUS_SUCCESS){  // 登录成功
            // 消除所有登录失败记录
            loginTimeService.delete(userName, true);
            // 回传默认的siteId
            Admin admin = adminService.getByUserName(userName);
            Integer roleId = admin.getAdminRoleId();
            Integer siteId = 0;
            if(roleId == 1){
                siteId = 1;
            } else {
                List<Site> sites = siteService.getByAdminPrivacy(roleId);
                if(sites != null && !sites.isEmpty()){
                    siteId = sites.get(0).getId();
                }
            }
            resultBean.setData(siteId.toString());
        } else {                                 // 登录失败
            resultBean.setCode(1);
            Byte remainTimes = -1;
            // 1.1 如果没有：添加一次记录
            if(loginTimes == null){
                loginTimes = new LoginTimes();
                loginTimes.setIp(HttpUtils.getIP(request));
                loginTimes.setUserName(userName);
                loginTimes.setIsAdmin(true);
                loginTimes.setTimes((byte) 1);
                loginTimes.setLoginTime(new Date());
                loginTimeService.save(loginTimes);
                remainTimes = (byte) (maxLoginTimes - loginTimes.getTimes());
            }
            // 1.2 如果有，检查登录次数，与最大登录次数比较
            else {
                Byte preLoginTimes = loginTimes.getTimes();
                // 1.2.1 未达到最大登录次数。登录次数+1，更新记录
                if(preLoginTimes < maxLoginTimes){
                    loginTimes.setTimes((byte) (preLoginTimes + 1));
                    loginTimeService.save(loginTimes);
                    remainTimes = (byte) (maxLoginTimes - loginTimes.getTimes());
                }
            }
            if(remainTimes != -1){
                resultBean.setData((0-remainTimes));
            }
        }
        return resultBean.toJson();
    }

    /**
     * 显示控制面板
     *
     * @param request
     * @return
     */
    @RequestMapping("home")
    public String adminHome(Map<String, Object> request, HttpSession session) {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            Integer adminId = (Integer) session.getAttribute(Constant.CURRENT_ADMIN);
            Admin admin = adminService.getByIdWithRole(adminId);
            Integer adminRoleId = admin.getAdminRoleId();
            request.put("admin", admin);

            List<Site> sites;
            if(adminRoleId == 1){
                sites = siteService.queryForList(null);
            } else {
                sites = siteService.getByAdminPrivacy(adminRoleId);
            }
            request.put("sites", sites);

            List<BackMenu> backMenus = backMenuService.getAll();
            List<BackMenu> quickMenus = adminMenuService.getAdminMenuByAdminId(adminId, backMenus);
            request.put("quickMenus", quickMenus);

            Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
            List<Model> models = modelService.getBySiteId(siteId);
            request.put("models", models);

            Site site = siteService.getById(siteId);
            request.put("siteName", site.getName());
            request.put("startTime", site.getStartTimeStr());
            request.put("endTime", site.getEndTimeStr());

            List<SiteHit> hits = siteHitService.getNearestHits(siteId, 10);
            request.put("hits", new Gson().toJson(hits));
        }
        return "admin/admin_home";
    }

    /**
     * 登录
     * @param userName
     * @param password
     * @param session
     * @param servletRequest
     * @return
     */
    private int login(String userName, String password,
                      HttpSession session, HttpServletRequest servletRequest){
        int res = Constant.STATUS_SUCCESS;
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            CustomizedToken customizedToken = new CustomizedToken(userName, password, "Admin");
            String ip = HttpUtils.getIP(servletRequest);
            try {
                currentUser.login(customizedToken);
                Admin admin = adminService.getByUserName(userName);
                session.setAttribute(Constant.CURRENT_ADMIN, admin.getId());
                admin.setLastLoginIp(ip);
                admin.setLastLoginTime(new Date());
                adminService.save(admin);
            } catch (IncorrectCredentialsException e) {
                logger.error(ExceptionUtils.formatExceptionInfo(e));
                res = Constant.STATUS_LOGIN_ERROR;
            } catch (LockedAccountException e) {
                logger.error(ExceptionUtils.formatExceptionInfo(e));
                res = Constant.STATUS_USER_ISLOCK;
            } catch (UnknownAccountException e) {
                logger.error(ExceptionUtils.formatExceptionInfo(e));
                res = Constant.STATUS_UNKNOW_ERROR;
            } catch (AuthenticationException e) {
                logger.error(ExceptionUtils.formatExceptionInfo(e));
                res = Constant.STATUS_LOGIN_ERROR;
            }
        }
        return res;
    }

}
