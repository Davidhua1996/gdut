package com.gdut.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.gdut.base.Role;
import com.gdut.base.UserStatus;

@SuppressWarnings("serial")
@Entity
@Table(name="T_USER")
public class User implements Serializable{
	
	@Id
	@GeneratedValue(generator = "system-uuid")  
    @GenericGenerator(name = "system-uuid", strategy = "uuid") 
	private String id;
	/**
	 * 用户名(学号)
	 */
	@Column(length=32,nullable = false)
	private String loginName;
	
	@Column(length=32)
	private String realName;
	
	@Column(length=32,nullable=false)
	private String password;

	@Column(length=50)
	private String address;
	
	@Column(length=32)
	private String phoneNum;
	
	@Column(length=32)
	private String email;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Role role;
	
	/**
	 * 注册时间
	 */
	@Column
	private Date RegDate;
	/**
	 * 注册时间
	 */
	@Column
	private Date loginDate;
	
	/**
	 * 用户状态
	 */
	@Column
	@Enumerated(EnumType.STRING)
	private UserStatus status;
	
	/**
	 * 激活码
	 */
	@Column
	private String activatedCode;
	/**
	 * 对应商家表
	 * @OneToOne
	 */
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="vendor_id")
	private Vendor vendor = new Vendor();
	/**
	 * 对应送水工
	 * @OneToOne
	 */
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="worker_id")
	private Worker worker = new Worker();
	/**
	 * 对应学生表
	 */
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="student_id")
	private Student student = new Student();
	/**
	 * 密保问题1
	 */
	@Column(length=50)
	private String questionOne;
	/**
	 * 密保答案1
	 */
	@Column(length=50)
	private String answerOne;
	/**
	 * 密保问题2
	 */
	@Column(length=50)
	private String questionTwo;
	/**
	 * 密保答案2
	 */
	@Column(length=50)
	private String answerTwo;
	
	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public String getActivatedCode() {
		return activatedCode;
	}

	public void setActivatedCode(String activatedCode) {
		this.activatedCode = activatedCode;
	}

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}

	public String getQuestionOne() {
		return questionOne;
	}

	public void setQuestionOne(String questionOne) {
		this.questionOne = questionOne;
	}

	public String getAnswerOne() {
		return answerOne;
	}

	public void setAnswerOne(String answerOne) {
		this.answerOne = answerOne;
	}

	public String getQuestionTwo() {
		return questionTwo;
	}

	public void setQuestionTwo(String questionTwo) {
		this.questionTwo = questionTwo;
	}

	public String getAnswerTwo() {
		return answerTwo;
	}

	public void setAnswerTwo(String answerTwo) {
		this.answerTwo = answerTwo;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Date getRegDate() {
		return RegDate;
	}

	public void setRegDate(Date regDate) {
		RegDate = regDate;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
}