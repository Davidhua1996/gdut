package com.gdut.dto;

import com.gdut.domain.User;

public class WorkerDto {
	/**
	 * 送水工id
	 */
	private String id;
	/**
	 * 送水工名称
	 */
	private String loginName;
	/**
	 * 真实姓名
	 */
	private String realName;
	/**
	 * 送水工地址
	 */
	private String address;
	/**
	 * 送水工电话
	 */
	private String phoneNum;
	/**
	 * 送水工邮箱
	 */
	private String email;
	/**
	 * 送水区域
	 */
	private String areaName;
	/**
	 * 送水楼
	 */
	private String buildingName;
	public WorkerDto(User user){
		this.address = user.getAddress();
		this.areaName = user.getWorker().getAreaName();
		this.email = user.getEmail();
		this.id = user.getId();
		this.loginName = user.getLoginName();
		this.phoneNum = user.getPhoneNum();
		this.realName = user.getRealName();
		this.buildingName = user.getWorker().getBuildingName();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	
	
}
