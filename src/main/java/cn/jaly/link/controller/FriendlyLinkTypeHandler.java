package cn.jaly.link.controller;

import cn.jaly.link.entity.FriendlyLinkType;
import cn.jaly.link.service.FriendlyLinkTypeService;
import cn.jaly.utils.annotations.Token;
import cn.jaly.utils.common.Constant;
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
@RequestMapping("/link/FriendlyLinkType/")
public class FriendlyLinkTypeHandler {

	@Autowired
	private FriendlyLinkTypeService friendlyLinkTypeService;
	
	@RequestMapping("list")
	public String list(Map<String, Object> request, HttpSession session) {
		Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
		List<FriendlyLinkType> types = friendlyLinkTypeService.getBySite(siteId);
		request.put("types", types);
		return "link/friendly_link_type_list";
	}

	@Token(save = true)
	@RequestMapping(value = "{input}", method = RequestMethod.GET)
	public String input(@PathVariable("input") String input,
						Map<String, Object> request, HttpSession session,
						@RequestParam(value = "id", required = false) Integer id){
		if(("add".equals(input) && id == null) || ("edit".equals(input) && id != null)) {
			FriendlyLinkType friendlyLinkType = new FriendlyLinkType();
			if (id != null) {
				friendlyLinkType = friendlyLinkTypeService.getById(id);
			} else {
				Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
				friendlyLinkType.setSiteId(siteId);
			}
			request.put("friendlyLinkType", friendlyLinkType);
			return "link/friendly_link_type_input";
		}
		return null;
	}

	@Token(remove = true)
	@RequestMapping(value = "{save}", method = RequestMethod.POST)
	public String save(@PathVariable("save") String save,
					   FriendlyLinkType friendlyLinkType){
		Integer id = friendlyLinkType.getId();
		if(("add".equals(save) && id == null) || ("edit".equals(save) && id != null)) {
			friendlyLinkTypeService.save(friendlyLinkType);
			return "redirect:/link/FriendlyLinkType/list";
		}
		return null;
	}
	
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public String delete(@RequestParam("id") Integer id){
		friendlyLinkTypeService.delete(id);
		return "redirect:/link/FriendlyLinkType/list";
	}
}
