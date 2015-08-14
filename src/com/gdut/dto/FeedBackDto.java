package com.gdut.dto;

import java.util.Date;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.gdut.domain.FeedBack;

public class FeedBackDto {
	/**
	 * 反馈表id
	 */
	private String id;
	/**
	 * 反馈者姓名
	 */
	private String name;
	/**
	 * 反馈时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date dateTime;
	/**
	 * 反馈内容
	 */
	private String content;
	
	public FeedBackDto(FeedBack feedBack){
		this.id = feedBack.getId();
		if(null!=feedBack.getStudent()){
			this.name = feedBack.getStudent()
					.getUser().getRealName();
		}else{
			this.name = feedBack.getVendor().getCompanyName();
		}
		this.dateTime = feedBack.getDateTime();
		this.content = feedBack.getContent();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
