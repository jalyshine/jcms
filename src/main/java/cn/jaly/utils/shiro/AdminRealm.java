package cn.jaly.utils.shiro;

import cn.jaly.admin.dao.AdminMapper;
import cn.jaly.admin.entity.Admin;
import cn.jaly.admin.entity.AdminExample;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AdminRealm extends AuthorizingRealm {

	@Autowired
	private AdminMapper adminMapper;

	private Admin admin;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		Object username = principals.getPrimaryPrincipal();

		AdminExample adminExample = new AdminExample();
		AdminExample.Criteria criteria = adminExample.createCriteria();
		criteria.andUserNameEqualTo(username.toString());
		admin = adminMapper.selectByExampleWithRole(adminExample).get(0);

		if(admin == null){
			return null;
		}

		Set<String> roles = new HashSet<>();
		//所有角色未禁用的管理员均有[Admin]角色
		if(!admin.getAdminRole().getDisabled()){
			roles.add("Admin");
			roles.add(admin.getAdminRole().getName());
		}

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);

		return info;
	}

	// token参数实际上就是Handler中currentUserSubject.login(token)传入的token，二者是同一个对象。
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {

		// 把AuthenticationToken转换为UsernamePasswordToken
		UsernamePasswordToken token2 = (UsernamePasswordToken) token;

		// 从UsernamePasswordToken中获取Username
		String username = token2.getUsername();

		// 从数据库中查询username所对应的用户信息
		AdminExample adminExample = new AdminExample();
		AdminExample.Criteria criteria = adminExample.createCriteria();
		criteria.andUserNameEqualTo(username.toString());
		List<Admin> admins = adminMapper.selectByExample(adminExample);
		if(!admins.isEmpty()){
			admin = admins.get(0);
		}

		// 根据表单提交的用户名和数据库查询的结果，比对后抛出对应的异常。
		// if ("monster".equals(userName)) {
		// throw new LockedAccountException("用户被锁定！");
		// }
		if (admin == null) {
			throw new UnknownAccountException("用户不存在！");
		}

		// 根据用户情况构建 AuthenticationInfo 对象并返回，通常使用的实现类为: SimpleAuthenticationInfo
		// 以下信息是从数据库中获取的
		// principal：认证的实体信息，可以是 userName, 也可以是数据表对应的用户的实体类对象。
		Object principal = username;
		// credentials：密码。
		Object credentials = admin.getPassword();
		// realmName：当前 realm 对象的 name，调用父类的 getName() 方法即可
		String realmName = this.getName();
		// 将用户名作为盐值
		ByteSource credentialsSalt = ByteSource.Util.bytes(admin.getEncrypt());

		// 作为 ShiroRealm 的 doGetAuthenticationInfo 方法的返回值
		return new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
	}

}
