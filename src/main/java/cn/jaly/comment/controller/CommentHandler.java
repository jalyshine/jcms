package cn.jaly.comment.controller;

import cn.jaly.comment.entity.Comment;
import cn.jaly.comment.service.CommentService;
import cn.jaly.utils.annotations.Token;
import cn.jaly.utils.common.Constant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/comment/Comment/")
public class CommentHandler {

    @Autowired
    private CommentService commentService;

    @RequestMapping("list")
    public String queryForList(Map<String, Object> request, HttpSession session,
                               @RequestParam(value = "tts", required = false) Byte status,
                               @RequestParam(value = "stm", required = false) String startTime,
                               @RequestParam(value = "edm", required = false) String endTime,
                               @RequestParam(value = "kwd", required = false) String kwd,
                               @RequestParam(value = "odr", required = false) String order,
                               @RequestParam(value = "ps", required = false) Integer ps,
                               @RequestParam(value = "pn", required = false) Integer pn) {
        if (ps == null) {
            ps = 20;
        }
        if (pn == null) {
            pn = 1;
        }
        Integer siteId = (Integer)session.getAttribute(Constant.CURRENT_SITE);
        PageHelper.startPage(pn, ps);
        List<Comment> comments = commentService.queryForList(siteId, startTime, endTime, kwd, status, order);
        PageInfo page = new PageInfo(comments);
        request.put("page", page);
        return "comment/comment_list";
    }

    @Token(remove = true)
    @RequestMapping(value = "{save}", method = RequestMethod.POST)
    public String save(@PathVariable("save") String save, Comment comment) {
        Integer id = comment.getId();
        if (("add".equals(save) && id == null) || ("edit".equals(save) && id != null)) {
            commentService.save(comment);
        }
        return null;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") Integer id, String odr) {
        commentService.delete(id);
        return "redirect:/comment/Comment/list?odr=" + odr;
    }

    @RequestMapping(value = "batch-{method}", method = RequestMethod.POST)
    public String batchOperate(@PathVariable("method") String method, Integer[] ids, String odr) {
        boolean flag = false;
        if (ids != null && ids.length > 0) {
            switch (method) {
                case "delete":
                    commentService.delete(ids);
                    flag = true;
                    break;
                default:
                    break;
            }
        }
        return flag ? "redirect:/comment/Comment/list?odr=" + odr : null;
    }

}
