package cn.jaly.admin.controller;

import java.util.List;
import java.util.Map;

import cn.jaly.admin.entity.AdminRole;
import cn.jaly.admin.service.AdminRoleService;
import cn.jaly.utils.annotations.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/AdminRole/")
public class AdminRoleHandler {

	@Autowired
	private AdminRoleService adminRoleService;
	
	@RequestMapping("list")
	public String list(Map<String, Object> request){
		List<AdminRole> adminRoles = adminRoleService.getAll();
		request.put("adminRoles", adminRoles);
		return "admin/admin_role_list";
	}

	@ResponseBody
	@RequestMapping(value = "check", method = RequestMethod.POST)
	public String check(@RequestParam("name") String name) {
		Integer rid = adminRoleService.getIdByName(name);
		return rid == null ? "0" : String.valueOf(rid);
	}

	@Token(save = true)
	@RequestMapping(value = "{input}", method = RequestMethod.GET)
	public String input(@PathVariable("input") String input, Map<String, Object> request,
						@RequestParam(value = "id", required = false) Integer id){
		if(("add".equals(input) && id == null) || ("edit".equals(input) && id != null)) {
			AdminRole adminRole = new AdminRole();
			if (id != null) {
				adminRole = adminRoleService.getById(id);
			}
			request.put("adminRole", adminRole);
			return "admin/admin_role_input";
		}
		return null;
	}

	@Token(remove = true)
	@RequestMapping(value = "{save}", method = RequestMethod.POST)
	public String save(@PathVariable("save") String save, AdminRole adminRole){
		Integer id = adminRole.getId();
		if(("add".equals(save) && id == null) || ("edit".equals(save) && id != null)) {
			adminRoleService.save(adminRole);
			return "redirect:/admin/AdminRole/list";
		}
		return null;
	}

	@RequestMapping(value="delete", method = RequestMethod.POST)
	public String delete(@RequestParam("id") Integer id){
		adminRoleService.delete(id); 
		return "redirect:/admin/AdminRole/list";
	}
}