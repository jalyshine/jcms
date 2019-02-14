package cn.jaly.admin.controller;

import cn.jaly.admin.entity.IpBanned;
import cn.jaly.admin.service.IpBannedService;
import cn.jaly.utils.annotations.Token;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/IpBanned/")
public class IpBannedHandler {

	@Autowired
	private IpBannedService ipBannedService;

	@RequestMapping("list")
	public String list(Map<String, Object> request,
					   @RequestParam(value = "kwd", required = false) String keyword,
					   @RequestParam(value = "ps", required = false) Integer ps,
					   @RequestParam(value = "pn", required = false) Integer pn) {
		if (ps == null) {
			ps = 20;
		}
		if (pn == null) {
			pn = 1;
		}

		PageHelper.startPage(pn, ps);
		List<IpBanned> ipBanneds = ipBannedService.getAll(keyword);
		PageInfo page = new PageInfo(ipBanneds);
		request.put("page", page);
		return "admin/ip_banned_list";
	}

	@ResponseBody
	@RequestMapping(value = "check", method = RequestMethod.POST)
	public String check(@RequestParam("ip") String ip) {
		IpBanned ipBanned = ipBannedService.getByIp(ip);
		return ipBanned == null ? "0" : String.valueOf(ipBanned.getId());
	}

	@Token(save = true)
	@RequestMapping(value = "{input}", method = RequestMethod.GET)
	public String input(@PathVariable("input") String input, Map<String, Object> request,
						@RequestParam(value = "id", required = false) Integer id) {
		if(("add".equals(input) && id == null) || ("edit".equals(input) && id != null)) {
			IpBanned ib = new IpBanned();
			if (id == null) {
				ib.setExpires(new Date());
			} else {
				ib = ipBannedService.getById(id);
			}
			request.put("ipBanned", ib);
			return "admin/ip_banned_input";
		}
		return null;
	}

	@Token(remove = true)
	@RequestMapping(value = "{save}", method = RequestMethod.POST)
	public String save(@PathVariable("save") String save, IpBanned ipBanned) {
		Integer id = ipBanned.getId();
		if(("add".equals(save) && id == null) || ("edit".equals(save) && id != null)) {
			ipBannedService.save(ipBanned);
			return "redirect:/admin/IpBanned/list";
		}
		return null;
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String delete(@RequestParam("id") Integer id) {
		ipBannedService.delete(id);
		return "redirect:/admin/IpBanned/list";
	}
}
