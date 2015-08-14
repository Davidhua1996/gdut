package com.gdut.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
/**
 * 反馈表
 * @author David
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="T_FEEDBACK")
public class FeedBack implements Serializable {
	@Id
	@GeneratedValue(generator="system-id")
	@GenericGenerator(name="system-id",strategy="uuid")
	private String id;
	/**
	 * 对应商家表
	 */
	@ManyToOne
	@JoinColumn(name="vendor_id")
	private Vendor vendor;
	/**
	 * 对应学生表
	 */
	@ManyToOne
	@JoinColumn(name="student_id")
	private Student student;
	/**
	 * 反馈内容
	 */
	@Column
	private String content;
	/**
	 * 反馈时间
	 * @return
	 */
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Vendor getVendor() {
		return vendor;
	}
	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	
}
