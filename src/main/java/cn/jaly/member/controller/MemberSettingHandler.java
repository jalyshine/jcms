package cn.jaly.member.controller;

import cn.jaly.member.entity.MemberSetting;
import cn.jaly.member.entity.MemberSettingWithBLOBs;
import cn.jaly.member.service.MemberSettingService;
import cn.jaly.utils.common.Constant;
import cn.jaly.utils.common.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/member/MemberSetting/")
public class MemberSettingHandler {

    @Autowired
    private MemberSettingService memberSettingService;

    @RequestMapping(value = "manage", method = RequestMethod.GET)
    public String init(Map<String, Object> request, HttpSession session){
        Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
        MemberSettingWithBLOBs memberSetting = memberSettingService.getBySiteId(siteId);
        if(memberSetting == null){
            memberSetting = new MemberSettingWithBLOBs();
            memberSetting.setSiteId(siteId);
        }
        request.put("memberSetting", memberSetting);
        return "member/member_setting";
    }

    @ResponseBody
    @RequestMapping(value = "manage", method = RequestMethod.POST)
    public String save(MemberSettingWithBLOBs memberSetting){
        memberSettingService.save(memberSetting);
        return new ResultBean(0).toJson();
    }

}
