package com.gdut.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.gdut.base.Role;
import com.gdut.base.UserStatus;

@SuppressWarnings("serial")
@Entity
@Table(name="T_STUDENT")
public class Student implements Serializable{

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	private String id;
	@Column(length=32)
	private String clazz;
	
	@Column(length=32,nullable=false)
	private String major;
	
	@Column(nullable=false)
	private String dormitory;
	
	@Column(nullable=false)
	private String areaName;
	
	@Column(nullable=false)
	private String romNum;
	
	@OneToOne(mappedBy="student")
	private User user;
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getRomNum() {
		return romNum;
	}

	public void setRomNum(String romNum) {
		this.romNum = romNum;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
