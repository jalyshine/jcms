package cn.jaly.utils.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

public class CustomizedToken extends UsernamePasswordToken {
 
	private String loginType;   // 登录类型

	public String getLoginType() {
		return loginType;
	}

	public CustomizedToken(final String username, final String password, String loginType) {
		super(username, password);
		this.loginType = loginType;
	}
}
