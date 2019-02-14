package cn.jaly.member.controller;

import cn.jaly.member.entity.Member;
import cn.jaly.member.entity.MemberSettingWithBLOBs;
import cn.jaly.member.entity.MemberVerify;
import cn.jaly.member.service.MemberService;
import cn.jaly.member.service.MemberSettingService;
import cn.jaly.member.service.MemberVerifyService;
import cn.jaly.utils.annotations.Token;
import cn.jaly.utils.common.*;
import cn.jaly.utils.explorer.HttpUtils;
import cn.jaly.utils.shiro.CustomizedToken;
import cn.jaly.utils.shiro.MemberRealm;
import cn.jaly.utils.shiro.StockHashedCredentialsMatcher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/member/Member/")
public class MemberIndexHandler {

    private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberVerifyService memberVerifyService;

    @Autowired
    private MemberRealm memberRealm;

    @Autowired
    private MemberSettingService memberSettingService;

    @ResponseBody
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(HttpServletRequest request, HttpSession session, Member member) {
        generatePassword(member, true);
        String regIp = HttpUtils.getIP(request);
        Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
        MemberSettingWithBLOBs setting = memberSettingService.getBySiteId(siteId);
        if(setting.getNeedAdmin()){  // 需要通过审核
            MemberVerify memberVerify = new MemberVerify();
            memberVerify.setUserName(member.getUserName());
            memberVerify.setPassword(member.getPassword());
            memberVerify.setEncrypt(member.getEncrypt());
            memberVerify.setPhone(member.getPhone());
            memberVerify.setEmail(member.getEmail());
            memberVerify.setRegIp(regIp);
            memberVerifyService.save(memberVerify);
        } else {
            member.setRegIp(regIp);
            memberService.save(member);
        }
        return new ResultBean(0).toJson();
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String memberLogin(@RequestParam("account") String account,
                              @RequestParam("password") String password,
                              HttpSession session, Map<String, Object> request, HttpServletRequest servletRequest) {
        String username = account;
        if (PhoneFormatCheckUtils.isPhoneLegal(username)) {
            username = memberService.getUserNameByPhone(account);
        }
        Integer status = login(username, password, session, servletRequest);
        switch (status) {
            case Constant.STATUS_LOGIN_ERROR:
            case Constant.STATUS_USER_ISLOCK:
            case Constant.STATUS_UNKNOW_ERROR:
                request.put("error", status + "");
                return "member/login";
        }
        return "";
    }

    /**
     * 登录
     *
     * @param userName
     * @param password
     * @param session
     * @param servletRequest
     * @return
     */
    private int login(String userName, String password,
                      HttpSession session, HttpServletRequest servletRequest) {
        int res = Constant.STATUS_SUCCESS;
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            CustomizedToken customizedToken = new CustomizedToken(userName, password, "Member");
            try {
                currentUser.login(customizedToken);
                Member member = memberService.getByUserName(userName);
                session.setAttribute(Constant.CURRENT_USER, member.getId());
                member.setLastLoginIp(HttpUtils.getIP(servletRequest));
                member.setLastLoginTime(new Date());
                memberService.save(member);
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

    /**
     * 根据用户输入，生成加密后的密码
     *
     * @param member
     * @param newEncrypt 是否生成新的加密因子
     * @return
     */
    private void generatePassword(Member member, boolean newEncrypt) {
        StockHashedCredentialsMatcher
                matcher = (StockHashedCredentialsMatcher) memberRealm.getCredentialsMatcher();
        if (newEncrypt) {
            member.setEncrypt(BasicUtils.getRadomEncrypt(6));
        }
        String psd = matcher.getHashProvidedCredentials(member.getPassword(), member.getEncrypt());
        member.setPassword(psd);
    }

}
