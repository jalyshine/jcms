package cn.jaly.message.controller;

import cn.jaly.message.entity.Message;
import cn.jaly.message.service.MessageService;
import cn.jaly.utils.annotations.Token;
import cn.jaly.utils.common.Constant;
import cn.jaly.utils.common.ResultBean;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/message/Message/")
public class MessageHandler {

	@Autowired
	private MessageService messageService;

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
		List<Message> messages = messageService.queryForList(siteId, keyword, order);
		PageInfo page = new PageInfo(messages);
		request.put("page", page);
		return "message/message_list";
	}

	@Token(save = true)
	@RequestMapping(value = "{input}", method = RequestMethod.GET)
	public String input(@PathVariable("input") String input,
                        Map<String, Object> request, HttpSession session,
						@RequestParam(value = "id", required = false) Integer id) {
		if(("add".equals(input) && id == null) || ("edit".equals(input) && id != null)) {
            Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
			Message message = new Message();
			if (id != null) {
				message = messageService.getById(id);
			} else {
			    message.setSiteId(siteId);
            }
			request.put("message", message);
			return "message/message_input";
		}
		return null;
	}

	@Token(remove = true)
	@RequestMapping(value = "{save}", method = RequestMethod.POST)
	public String save(@PathVariable("save") String save, Message message) {
		Integer id = message.getId();
		if(("add".equals(save) && id == null) || ("edit".equals(save) && id != null)) {
			Subject currentUser = SecurityUtils.getSubject();
			if (currentUser.isAuthenticated()) {
				String sender = currentUser.getPrincipals().getPrimaryPrincipal().toString();
				message.setSender(sender);
			}
			messageService.save(message);
			return "redirect:/message/Message/list";
		}
		return null;
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String delete(@RequestParam("id") Integer id) {
		messageService.delete(id);
		return "redirect:/message/Message/list";
	}
}
