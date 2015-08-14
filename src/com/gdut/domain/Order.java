package com.gdut.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import com.gdut.base.DateTypes;
import com.gdut.base.OrderStatus;

@SuppressWarnings("serial")
@Entity
@Table(name="T_ORDER")
public class Order implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	/**
	 * 联系电话
	 */
	@Column(length=32,nullable=false)
	private String phoneNum;
	/**
	 * 送货地点
	 */
	@Column(length=50,nullable=false)
	private String address;
	/**
	 * 订单日期
	 */
	@Column(nullable=false)
	private Date orderDate;
	/**
	 * 付款方式
	 */
	@Column(length=50,nullable=false)
	private String payMeth;
	/**
	 * 付款凭证号
	 */
	@Column(length=32)
	private String voucher;
	/**
	 * 状态（0：创建；1：支付申请；2：支付完成；3：送货中；4：已收货；5：取消订单
	 */
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	/**
	 * 最后状态改变日期
	 */
	@Column
	private Date changeDate;
	/**
	 * 总价
	 */
	@Column
	private Double totalAmount;
	/**
	 * 送货时间
	 */
	@Column
	@Enumerated(EnumType.STRING)
	private DateTypes postDate;
	/**
	 * 付款时间
	 */
	@Column
	private Date payDate;
	/**
	 * 对应送水工Worker
	 * @ManyToOne
	 */
	@ManyToOne
	@JoinColumn(name="worker_id")
	private Worker worker;
	/**
	 * 对应学生表Student
	 * @ManyToOne
	 */
	@ManyToOne
	@JoinColumn(name="student_id")
	private Student student;
	/**
	 * 对用表单明细表orderList
	 * @OneToMany
	 */
	@OneToMany(mappedBy="order",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<OrderItems> orderLists=new ArrayList<OrderItems>();
	/**
	 * 对应商家表
	 * @ManyToOne 
	 */
	@ManyToOne
	@JoinColumn(name="vendor_id")
	private Vendor vendor;
	/**
	 * 对应区域表
	 */
	@Column(length=50)
	private String areaName;
	
	
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public Vendor getVendor() {
		return vendor;
	}
	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
	
	public DateTypes getPostDate() {
		return postDate;
	}
	public void setPostDate(DateTypes postDate) {
		this.postDate = postDate;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public void setOrderLists(List<OrderItems> orderLists) {
		this.orderLists = orderLists;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public Worker getWorker() {
		return worker;
	}
	public void setWorker(Worker worker) {
		this.worker = worker;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public List<OrderItems> getOrderLists() {
		return orderLists;
	}
	public void setOrderList(List<OrderItems> orderLists) {
		this.orderLists = orderLists;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getPayMeth() {
		return payMeth;
	}
	public void setPayMeth(String payMeth) {
		this.payMeth = payMeth;
	}
	public String getVoucher() {
		return voucher;
	}
	public void setVoucher(String voucher) {
		this.voucher = voucher;
	}
	public Date getChangeDate() {
		return changeDate;
	}
	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}
	
}
