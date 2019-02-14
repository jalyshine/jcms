package cn.jaly.admin.controller;

import cn.jaly.admin.entity.SiteHit;
import cn.jaly.admin.service.SiteHitService;
import cn.jaly.utils.common.DateTimeUtils;
import cn.jaly.utils.common.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SiteHitHandler {

    @Autowired
    private SiteHitService siteHitService;

    @ResponseBody
    @RequestMapping(value = "/hit", method = RequestMethod.POST)
    public String update(@RequestParam("site") Integer siteId,
                         @RequestParam("hits") Integer hits,
                         @RequestParam("time") String time){
        SiteHit siteHit = new SiteHit();
        siteHit.setSiteId(siteId);
        siteHit.setHit(hits);
        siteHit.setTime(DateTimeUtils.parseSimple(time));
        siteHitService.save(siteHit);
        return new ResultBean(0).toJson();
    }
}
