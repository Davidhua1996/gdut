package com.gdut.alipay;


public class Configuration {
	//合作身份者id
	private static String partner = "";
	//商户的私钥
	private static String key="";
	//收款支付宝账号 
	private String seller_email;
	//字符编码格式
	private String input_charset;
	//签名方式
	private String sign_type;
	//接口名称
	private String service;
	public String getPartner() {
		return partner;
	}
	public static void setPartner(String partner) {
		Configuration.partner = partner;
	}
	public static String getKey() {
		return key;
	}
	public static void setKey(String key) {
		Configuration.key = key;
	}
	public String getSeller_email() {
		return seller_email;
	}
	public void setSeller_email(String seller_email) {
		this.seller_email = seller_email;
	}
	public String getInput_charset() {
		return input_charset;
	}
	public void setInput_charset(String input_charset) {
		this.input_charset = input_charset;
	}
	public String getSign_type() {
		return sign_type;
	}
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	
}
