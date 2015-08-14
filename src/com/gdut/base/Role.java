package com.gdut.base;

public enum Role{
	BUSINESS("商家"),ADMIN("管理员"),WORKER("送水工"),STUDENT("学生");
	private String name;
	 Role(String name){
		this.name=name;
	}
	public String  getName(){
		return name;
	}
}