package cn.jaly.admin.controller;

import cn.jaly.admin.entity.SiteInfo;
import cn.jaly.admin.service.SiteInfoService;
import cn.jaly.content.entity.AttachIndex;
import cn.jaly.content.service.AttachIndexService;
import cn.jaly.utils.common.Constant;
import cn.jaly.utils.common.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/SiteInfo/")
public class SiteInfoHandler {

    @Autowired
    private SiteInfoService siteInfoService;

    @Autowired
    private AttachIndexService attachIndexService;

    @RequestMapping(value = "manage", method = RequestMethod.GET)
    public String manage(Map<String, Object> request, HttpSession session){
        Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
        SiteInfo siteInfo = siteInfoService.getBySiteId(siteId);
        if(siteInfo == null){
            siteInfo = new SiteInfo();
        }
        siteInfo.setSiteId(siteId);
        request.put("siteInfo", siteInfo);
        return "admin/site_info";
    }

    @ResponseBody
    @RequestMapping(value = "manage", method = RequestMethod.POST)
    public String manage(SiteInfo siteInfo){
        siteInfoService.save(siteInfo);

        // 关联上传的图标
        List<String> filePaths = new ArrayList<>();
        filePaths.add(siteInfo.getLogo());
        filePaths.add(siteInfo.getQrCode());
        filePaths.add(siteInfo.getImage());
        AttachIndex attachIndex = new AttachIndex();
        attachIndex.setModule("admin");
        attachIndex.setHost("site_info-" + siteInfo.getSiteId());
        attachIndexService.save(filePaths, attachIndex);
        return new ResultBean(0).toJson();
    }

}
