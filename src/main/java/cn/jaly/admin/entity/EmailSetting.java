package cn.jaly.admin.entity;

/**
 * 邮件设置
 */
public class EmailSetting {

	private String server;     // 邮件服务器
	private String sender;     // 发件人地址
	private Boolean needAuth;    // 是否开启AUTH LOGIN验证
	private String userName;   // 验证用户名
	private String password;   // 验证密码

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public Boolean getNeedAuth() {
		return needAuth;
	}

	public void setNeedAuth(Boolean auth) {
		needAuth = auth;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "EmailSetting{" +
				"server='" + server + '\'' +
				", sender='" + sender + '\'' +
				", needAuth=" + needAuth +
				", userName='" + userName + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
