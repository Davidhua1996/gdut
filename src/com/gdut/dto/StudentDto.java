package com.gdut.dto;

import com.gdut.domain.User;

public class StudentDto {
	/**
	 * 学生id
	 */
	public String id;
	/**
	 * 学号
	 */
	public String loginName;
	/**
	 * 学生名字
	 */
	public String name;
	/**
	 * 班别
	 */
	public String clazz;
	/**
	 * 专业
	 */
	public String major;
	/**
	 * 宿舍楼
	 */
	public String dormitory;
	/**
	 * 宿舍区域
	 */
	public String areaName;
	/**
	 * 宿舍号
	 */
	public String romNum;
	public StudentDto(User user){
		this.areaName = user.getStudent().getAreaName();
		this.clazz = user.getStudent().getClazz();
		this.dormitory = user.getStudent().getDormitory();
		this.id = user.getId();
		this.loginName = user.getLoginName();
		this.major = user.getStudent().getMajor();
		this.name = user.getRealName();
		this.romNum = user.getStudent().getRomNum();
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getDormitory() {
		return dormitory;
	}
	public void setDormitory(String dormitory) {
		this.dormitory = dormitory;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getRomNum() {
		return romNum;
	}
	public void setRomNum(String romNum) {
		this.romNum = romNum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
