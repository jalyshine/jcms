package cn.jaly.message.controller;

import cn.jaly.member.entity.MemberGroup;
import cn.jaly.member.service.MemberGroupService;
import cn.jaly.message.entity.MessageGroup;
import cn.jaly.message.service.MessageGroupService;
import cn.jaly.utils.annotations.Token;
import cn.jaly.utils.common.Constant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/message/MessageGroup/")
public class MessageGroupHandler {

    @Autowired
    private MessageGroupService messageGroupService;

    @Autowired
    private MemberGroupService memberGroupService;

    @RequestMapping("list")
    public String queryForList(Map<String, Object> request, HttpSession session,
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
        Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
        PageHelper.startPage(pn, ps);
        List<MessageGroup> messageGroups = messageGroupService.queryForList(siteId, keyword, order);
        PageInfo page = new PageInfo(messageGroups);
        request.put("page", page);

        return "message/message_group_list";
    }

    @Token(save = true)
    @RequestMapping(value = "{input}", method = RequestMethod.GET)
    public String input(@PathVariable("input") String input,
                        Map<String, Object> request, HttpSession session,
                        @RequestParam(value = "id", required = false) Integer id) {
        if(("add".equals(input) && id == null) || ("edit".equals(input) && id != null)) {
            MessageGroup messageGroup = new MessageGroup();
            Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
            if (id != null) {
                messageGroup = messageGroupService.getById(id);
            } else {
                messageGroup.setSiteId(siteId);
            }
            List<MemberGroup> memberGroups = memberGroupService.getBySiteId(siteId);
            request.put("memberGroups", memberGroups);
            request.put("messageGroup", messageGroup);
            return "message/message_group_input";
        }
        return null;
    }

    @Token(remove = true)
    @RequestMapping(value = "{save}", method = RequestMethod.POST)
    public String save(@PathVariable("save") String save,
                       MessageGroup messageGroup) {
        Integer id = messageGroup.getId();
        if(("add".equals(save) && id == null) || ("edit".equals(save) && id != null)) {
            Subject currentUser = SecurityUtils.getSubject();
            if (currentUser.isAuthenticated()) {
                String sender = currentUser.getPrincipals().getPrimaryPrincipal().toString();
                messageGroup.setSender(sender);
            }
            messageGroupService.save(messageGroup);
            return "redirect:/message/MessageGroup/list";
        }
        return null;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") Integer id) {
        messageGroupService.delete(id);
        return "redirect:/message/MessageGroup/list";
    }
}
