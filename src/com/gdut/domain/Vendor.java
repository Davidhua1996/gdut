package com.gdut.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@SuppressWarnings("serial")
@Entity
@Table(name="T_VENDOR")
public class Vendor implements Serializable{
	@Id
	@GeneratedValue(generator = "system-uuid")  
    @GenericGenerator(name = "system-uuid", strategy = "uuid") 
	private String id;
	/**
	 * 区域
	 */
	@Column(length=50,nullable=false)
	private String areaName;
	/**
	 * 栋
	 */
	@Column(length=50,nullable=false)
	private String buildingName;
	/**
	 * 图片地址
	 */
	@Column(length=50,nullable=false)
	private String picUrl;
	/**
	 * 公司名称
	 */
	@Column(length=32)
	private String companyName;
	/**
	 * 银行卡号
	 * @return
	 */
	@Column(length=50)
	private String bankCard;
	/**
	 * 对应用户表
	 */
	@OneToOne(mappedBy="vendor")
	private User user;
	public String getBankCard() {
		return bankCard;
	}
	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
