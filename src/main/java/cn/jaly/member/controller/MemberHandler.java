package cn.jaly.member.controller;

import cn.jaly.member.entity.Member;
import cn.jaly.member.entity.MemberGroup;
import cn.jaly.member.service.MemberGroupService;
import cn.jaly.member.service.MemberService;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/member/Member/")
public class MemberHandler {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRealm memberRealm;

    @Autowired
    private MemberGroupService memberGroupService;

    @RequestMapping("list")
    public String list(Map<String, Object> request, HttpSession session,
                       @RequestParam(value = "st", required = false) String st,
                       @RequestParam(value = "et", required = false) String et,
                       @RequestParam(value = "tts", required = false) Byte tts,
                       @RequestParam(value = "grp", required = false) Integer grp,
                       @RequestParam(value = "kwd", required = false) String kwd,
                       @RequestParam(value = "odr", required = false) String odr,
                       @RequestParam(value = "ps", required = false) Integer ps,
                       @RequestParam(value = "pn", required = false) Integer pn){

        if (ps == null) {
            ps = 20;
        }
        if (pn == null) {
            pn = 1;
        }

        PageHelper.startPage(pn, ps);
        Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
        List<Member> members = memberService.queryForList(siteId, tts, grp, st, et, kwd, odr);
        PageInfo page = new PageInfo(members);
        request.put("page", page);
        List<MemberGroup> memberGroups = memberGroupService.getBySiteId(siteId);
        request.put("memberGroups", memberGroups);
        return "member/member_list";
    }

    /**
     * 检查是否重名
     * @param userName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "check", method = RequestMethod.POST)
    public String check(@RequestParam("name") String userName) {
        Integer id = memberService.getIdByUserName(userName);
        return id == null ? "0" : String.valueOf(id);
    }

    @Token(save = true)
    @RequestMapping(value = "{input}", method = RequestMethod.GET)
    public String input(@PathVariable("input") String input,
                        Map<String, Object> request, HttpSession session,
                        @RequestParam(value = "id", required = false) Integer id) {
        if(("add".equals(input) && id == null) || ("edit".equals(input) && id != null)) {
            Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
            List<MemberGroup> memberGroups = memberGroupService.getBySiteId(siteId);
            request.put("memberGroups", memberGroups);
            Member member = new Member();
            if (id != null) {
                member = memberService.getById(id);
            } else {
                member.setSiteId(siteId);
            }
            request.put("member", member);
            return "member/member_input";
        }
        return null;
    }

    @Token(remove = true)
    @RequestMapping(value = "{save}", method = RequestMethod.POST)
    public String save(@PathVariable("save") String save,
                       Member member, HttpServletRequest request) {
        Integer id = member.getId();
        if(("add".equals(save) && id == null) || ("edit".equals(save) && id != null)) {
            if (member.getId() != null) {
                member.setPassword(null);
            } else {
                generatePassword(member, true);
                member.setRegIp(HttpUtils.getIP(request));
            }
            memberService.save(member);
            return "redirect:/member/Member/list";
        }
        return null;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") Integer id) {
        memberService.delete(id);
        return "redirect:/member/Member/list";
    }

    /**
     * 批量操作
     * @param ids
     * @return
     */
    @RequestMapping(value = "batch-{method}", method = RequestMethod.POST)
    public String batchOperate(@PathVariable("method") String method, Integer[] ids) {
        boolean flag = false;
        if (ids != null) {
            switch (method){
                case "delete":
                    memberService.delete(ids);
                    flag = true;
                    break;
                case "lock":
                    memberService.lock(true, ids);
                    flag = true;
                    break;
                case "unlock":
                    memberService.lock(false, ids);
                    flag = true;
                    break;
            }
        }
        return flag?"redirect:/member/Member/list":null;
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