package cn.jaly.tip.controller;

import cn.jaly.tip.entity.TipType;
import cn.jaly.tip.service.TipTypeService;
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
@RequestMapping("/tip/TipType/")
public class TipTypeHandler {

	@Autowired
	private TipTypeService tipTypeService;
	
	@RequestMapping("list")
	public String list(Map<String, Object> request, HttpSession session) {
		Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
		List<TipType> types = tipTypeService.getBySite(siteId);
		request.put("types", types);
		return "tip/tip_type_list";
	}

	@Token(save = true)
	@RequestMapping(value = "{input}", method = RequestMethod.GET)
	public String input(@PathVariable("input") String input,
						Map<String, Object> request, HttpSession session,
						@RequestParam(value = "id", required = false) Integer id){
		if(("add".equals(input) && id == null) || ("edit".equals(input) && id != null)) {
			TipType tipType = new TipType();
			if (id != null) {
				tipType = tipTypeService.getById(id);
			} else {
				Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
				tipType.setSiteId(siteId);
			}
			request.put("tipType", tipType);
			return "tip/tip_type_input";
		}
		return null;
	}

	@Token(remove = true)
	@RequestMapping(value = "{save}", method = RequestMethod.POST)
	public String save(@PathVariable("save") String save,
					   TipType tipType){
		Integer id = tipType.getId();
		if(("add".equals(save) && id == null) || ("edit".equals(save) && id != null)) {
			tipTypeService.save(tipType);
			return "redirect:/tip/TipType/list";
		}
		return null;
	}
	
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public String delete(@RequestParam("id") Integer id){
		tipTypeService.delete(id);
		return "redirect:/tip/TipType/list";
	}
}
