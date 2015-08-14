package com.gdut.dto;

import com.gdut.domain.User;

public class VendorDto {
	/**
	 * 商家id
	 */
	private String id;
	/**
	 * 商家名称
	 */
	private String loginName;
	/**
	 * 真实姓名
	 */
	private String realName;
	/**
	 * 商家地址
	 */
	private String address;
	/**
	 * 商家电话
	 */
	private String phoneNum;
	/**
	 * 商家邮箱
	 */
	private String email;
	/**
	 * 卫生许可证
	 */
	private String picUrl;
	/**
	 * 供水区域
	 */
	private String areaName;
	/**
	 * 栋
	 */
	private String buildingName;
	/**
	 * 公司名称
	 */
	private String companyName;
	/**
	 * 商家卡号
	 */
	private String bankCard;
	
	public VendorDto(User user){
		this.address = user.getAddress();
		this.areaName =  user.getVendor().getAreaName();
		this.bankCard = user.getVendor().getBankCard();
		this.buildingName = user.getVendor().getBuildingName();
		this.companyName = user.getVendor().getCompanyName();
		this.email = user.getEmail();
		this.id = user.getId();
		this.loginName = user.getLoginName();
		this.phoneNum = user.getPhoneNum();
		this.picUrl = user.getVendor().getPicUrl();
		this.realName = user.getRealName();
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
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
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
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getBankCard() {
		return bankCard;
	}
	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}
	
	
}
