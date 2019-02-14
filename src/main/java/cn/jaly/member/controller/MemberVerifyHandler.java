package cn.jaly.member.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.jaly.member.entity.MemberVerify;
import cn.jaly.member.service.MemberVerifyService;
import cn.jaly.utils.annotations.Token;
import cn.jaly.utils.common.Constant;
import cn.jaly.utils.explorer.HttpUtils;
import cn.jaly.utils.common.BasicUtils;
import cn.jaly.utils.shiro.MemberRealm;
import cn.jaly.utils.shiro.StockHashedCredentialsMatcher;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/member/MemberVerify/")
public class MemberVerifyHandler {

    @Autowired
    private MemberVerifyService memberVerifyService;

    @Autowired
    private MemberRealm memberRealm;

    @RequestMapping("list")
    public String list(Map<String, Object> request, HttpSession session,
                       @RequestParam(value = "tts", required = false) String tts,
                       @RequestParam(value = "odr", required = false) String odr,
                       @RequestParam(value = "ps", required = false) Integer ps,
                       @RequestParam(value = "pn", required = false) Integer pn) {
        if (ps == null) {
            ps = 20;
        }
        if (pn == null) {
            pn = 1;
        }

        PageHelper.startPage(pn, ps);
        Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
        List<MemberVerify> memberVerifies = memberVerifyService.queryForList(siteId, tts, odr);
        PageInfo page = new PageInfo(memberVerifies);
        request.put("page", page);

        return "member/member_verify_list";
    }

    @Token(save = true)
    @RequestMapping(value = "{input}", method = RequestMethod.GET)
    public String input(@PathVariable("input") String input,
                        Map<String, Object> request, HttpSession session,
                        @RequestParam(value = "id", required = false) Integer id) {
        if(("add".equals(input) && id == null) || ("edit".equals(input) && id != null)) {
            MemberVerify memberVerify = new MemberVerify();
            if (id != null) {
                memberVerify = memberVerifyService.getById(id);
            } else {
                memberVerify.setStatus((byte) 0);
                Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
                memberVerify.setSiteId(siteId);
            }
            request.put("memberVerify", memberVerify);
            return "member/member_verify_input";
        }
        return null;
    }

    /**
     * 保存审核会员
     *
     * @param memberVerify
     * @param request
     * @return
     */
    @Token(remove = true)
    @RequestMapping(value = "{save}", method = RequestMethod.POST)
    public String save(@PathVariable("save") String save,
                       MemberVerify memberVerify, HttpServletRequest request) {
        Integer id = memberVerify.getId();
        if(("add".equals(save) && id == null) || ("edit".equals(save) && id != null)) {
            generatePassword(memberVerify, true);
            memberVerify.setRegIp(HttpUtils.getIP(request));
            memberVerifyService.save(memberVerify);
            return "redirect:/member/MemberVerify/list";
        }
        return null;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") Integer id){
        memberVerifyService.delete(id);
        return "redirect:/member/MemberVerify/list";
    }

    /**
     * 批量操作
     * @param ids
     * @return
     */
    @RequestMapping(value = "batch-{method}", method = RequestMethod.POST)
    public String batchOperate(@PathVariable("method") String method, Integer[] ids) {
        boolean flag = false;
        if (ids != null && ids.length > 0) {
            switch (method){
                case "delete":
                    memberVerifyService.delete(ids);
                    flag = true;
                    break;
                case "pass":
                    memberVerifyService.pass(ids);
                    flag = true;
                    break;
                case "refuse":
                    memberVerifyService.update(ids, (byte) Constant.MEMBER_VERIFY_REFUSE);
                    flag = true;
                    break;
                case "ignore":
                    memberVerifyService.update(ids, (byte) Constant.MEMBER_VERIFY_IGNORE);
                    flag = true;
                    break;
                default: break;
            }
        }
        return flag?"redirect:/member/MemberVerify/list":null;
    }

    /**
     * 根据用户输入，生成加密后的密码
     *
     * @param memberVerify
     * @param newEncrypt 是否生成新的加密因子
     * @return
     */
    private void generatePassword(MemberVerify memberVerify, boolean newEncrypt) {
        StockHashedCredentialsMatcher
                matcher = (StockHashedCredentialsMatcher) memberRealm.getCredentialsMatcher();
        if (newEncrypt) {
            memberVerify.setEncrypt(BasicUtils.getRadomEncrypt(6));
        }
        String psd = matcher.getHashProvidedCredentials(memberVerify.getPassword(), memberVerify.getEncrypt());
        memberVerify.setPassword(psd);
    }
}
