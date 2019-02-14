package cn.jaly.comment.controller;

import cn.jaly.comment.entity.CommentSetting;
import cn.jaly.comment.service.CommentSettingService;
import cn.jaly.utils.common.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/comment/CommentSetting/")
public class CommentSettingHandler {

    @Autowired
    private CommentSettingService commentSettingService;

    @RequestMapping(value = "manage", method = RequestMethod.GET)
    public String init(Map<String, Object> request, HttpSession session){
        Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
        CommentSetting commentSetting = commentSettingService.getBySiteId(siteId);
        if(commentSetting == null){
            commentSetting = new CommentSetting();
            commentSetting.setSiteId(siteId);
        }
        request.put("commentSetting", commentSetting);
        return "comment/comment_setting";
    }

    @ResponseBody
    @RequestMapping(value = "manage", method = RequestMethod.POST)
    public String save(CommentSetting commentSetting){
        commentSettingService.save(commentSetting);
        return "1";
    }
}
