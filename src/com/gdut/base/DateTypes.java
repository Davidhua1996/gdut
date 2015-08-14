package com.gdut.base;

public enum DateTypes {
	MORNING("上午7:00-11:00"),NOON("中午12:00-13:00"),AFTERNOON("下午14:00-17:00");
	String name;
	DateTypes(String name){
		this.name=name;
	}
	public String getName() {
		return name;
	}
}
