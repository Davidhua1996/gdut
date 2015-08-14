package com.gdut.util;





public class EmailUtil {
	//邮箱帐户名
	private String account;
	//邮箱的密码
	private String password;
	//邮箱的服务器名
	private String host;
	//邮箱的端口
	private String port;
	//信件标题
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
}
