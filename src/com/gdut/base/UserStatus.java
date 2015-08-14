package com.gdut.base;

public enum UserStatus {
	S_AUDIT("未审核"),S_APPROVED("已审核"),S_BLACKLIST("黑名单"),S_DELETE("删除");
	private String name;
	UserStatus(String name){
		this.name=name; 
	}
	public String  getName(){
		return name;
	}
}
