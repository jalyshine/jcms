package cn.jaly.tip.controller;

import cn.jaly.content.entity.AttachIndex;
import cn.jaly.content.service.AttachIndexService;
import cn.jaly.tip.entity.Tip;
import cn.jaly.tip.entity.TipType;
import cn.jaly.tip.service.TipService;
import cn.jaly.tip.service.TipTypeService;
import cn.jaly.utils.annotations.Token;
import cn.jaly.utils.common.Constant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/tip/Tip/")
public class TipHandler {

    @Autowired
    private TipService tipService;

    @Autowired
    private TipTypeService tipTypeService;

    @Autowired
    private AttachIndexService attachIndexService;

    @RequestMapping("list")
    public String list(Map<String, Object> request, HttpSession session,
                       @RequestParam(value = "tid", required = false) Integer typeId,
                       @RequestParam(value = "kwd", required = false) String keyword,
                       @RequestParam(value = "odr", required = false) String order,
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
        List<Tip> tips = tipService.queryForList(siteId, typeId, keyword, order);
        PageInfo page = new PageInfo(tips);
        request.put("page", page);

        List<TipType> tipTypes = tipTypeService.getBySite(siteId);
        request.put("tipTypes", tipTypes);
        return "tip/tip_list";
    }

    @Token(save = true)
    @RequestMapping(value = "{input}", method = RequestMethod.GET)
    public String input(@PathVariable("input") String input,
                        Map<String, Object> request, HttpSession session,
                        @RequestParam(value = "id", required = false) Integer id) {
        if(("add".equals(input) && id == null) || ("edit".equals(input) && id != null)) {
            Tip tip = new Tip();
            Integer siteId;
            if (id != null) {
                tip = tipService.getById(id);
                siteId = tip.getSiteId();
            } else {
                siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
                tip.setSiteId(siteId);
            }
            List<TipType> tipTypes = tipTypeService.getBySite(siteId);
            request.put("tipTypes", tipTypes);
            request.put("tip", tip);
            return "tip/tip_input";
        }
        return null;
    }

    @Token(remove = true)
    @RequestMapping(value = "{save}", method = RequestMethod.POST)
    public String save(@PathVariable("save") String save, Tip tip, String editorAttachment) {
        Integer id = tip.getId();
        if(("add".equals(save) && id == null) || ("edit".equals(save) && id != null)) {
            tipService.save(tip);

            // 关联上传的图标
            List<String> filePaths = new ArrayList<>();
            filePaths.add(tip.getBanner());
            filePaths.add(tip.getIcon());
            String[] tokens = editorAttachment.split("\\,");
            filePaths.addAll(Arrays.asList(tokens));
            AttachIndex attachIndex = new AttachIndex();
            attachIndex.setModule("tip");
            attachIndex.setHost("tip-" + tip.getId());
            attachIndexService.save(filePaths, attachIndex);
            return "redirect:/tip/Tip/list";
        }
        return null;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") Integer id) {
        tipService.delete(id);
        return "redirect:/tip/Tip/list";
    }
}
