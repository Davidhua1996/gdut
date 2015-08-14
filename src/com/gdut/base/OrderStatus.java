package com.gdut.base;

public enum OrderStatus{
	S_CREATE("未支付"), S_PAYMENT("已支付"), S_SENT("已送货"),S_OVERDUE("已过期");
	private String name;
	OrderStatus(String name){
		this.name=name;
	}
	public String  getName(){
		return name;
	}

}