package cn.jaly.utils.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.jaly.admin.entity.*;
import cn.jaly.admin.service.*;
import cn.jaly.content.service.CategoryPrivacyService;
import cn.jaly.utils.common.Constant;
import cn.jaly.utils.common.MapUtils;
import cn.jaly.utils.common.BasicUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * 控制所有访问权限，只要被shiro拦截的，都在这里
 */
public class RolesAuthorizationFilter extends AuthorizationFilter {

//	@Autowired
//	private AdminService adminService;

	@Autowired
	private AdminRoleService adminRoleService;

	@Autowired
	private AdminRolePrivacyService adminRolePrivacyService;

//	@Autowired
//	private CategoryPrivacyService categoryPrivacyService;

	@Override
	protected boolean isAccessAllowed(ServletRequest req, ServletResponse resp, Object mappedValue){

		Subject subject = getSubject(req, resp);
		String[] rolesArray = (String[]) mappedValue;

		// 没有角色限制，直接访问
		if (rolesArray == null || rolesArray.length == 0) {
			return true;
		}

		// 有角色限制时，超级管理员直接通过
		AdminRole adminRole = adminRoleService.getById(1);
		if(subject.hasRole(adminRole.getName())){
			return true;
		}

		HttpServletRequest request = (HttpServletRequest) req;
		Map<String, String> uriMap = MapUtils.fromRequestURI(request.getRequestURI());
		String queryString = request.getQueryString();
		HttpSession session = request.getSession();
		Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
//		Integer adminId = (Integer) session.getAttribute(Constant.CURRENT_ADMIN);
//		Admin admin = adminService.getById(adminId);

		for (int i = 0; i < rolesArray.length; i++) {
			if (subject.hasRole(rolesArray[i])) {
				if(rolesArray[i].equals("Admin")){  // Admin为最低管理员权限
//					if(uriMap != null && "content".equals(uriMap.get("module"))){ // 栏目权限控制
//						String action = uriMap.get("action");
//						if(action.contains("batch-")){
//							action = action.substring(6);
//						}
//						String cidStr = request.getParameter("cid");
//						if(!BasicUtils.isInteger(cidStr)){
//							return false;
//						}
//						int cid = Integer.parseInt(cidStr);
//						return categoryPrivacyService.isCategoryPrivacyExist(admin.getAdminRoleId(), cid, action);
//					}
					return true;
				}
				// 高于admin的其他权限
				Integer roleId = adminRoleService.getIdByName(rolesArray[i]);
				if(adminRolePrivacyService.isPrivacyPass(uriMap, queryString, siteId, roleId)){
					return true;
				}
			}
		}
		return false;
	}
}