package com.gdut.sms;
/**
 * 模板短信配置
 * @author David
 *
 */
public class Configuration {
	//用户id
	private String accountSid = "1eebd3be065b2ba7f3170f9e6cbbbe4a";
	//用户令牌
	private String authToken = "3af4bf9acb69452472e50d39325632eb";
	//版本
	private String version;
	//应用Id
	private String appId;
	//短信模板Id
	private String templatedId;
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getTemplatedId() {
		return templatedId;
	}
	public void setTemplatedId(String templatedId) {
		this.templatedId = templatedId;
	}
	public String getAccountSid() {
		return accountSid;
	}
	public void setAccountSid(String accountSid) {
		this.accountSid = accountSid;
	}
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	
	
}
