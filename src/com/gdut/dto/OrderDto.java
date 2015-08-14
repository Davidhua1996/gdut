package com.gdut.dto;

import java.util.Date;

import com.gdut.base.DateTypes;
import com.gdut.base.OrderStatus;
import com.gdut.domain.Order;

public class OrderDto {
	/**
	 * 订单id
	 */
	private Integer id;
	/**
	 * 联系电话
	 */
	private String phoneNum;
	/**
	 * 送货地点
	 */
	private String address;
	/**
	 * 付款方式
	 */
	private String payMethod;
	/**
	 * 总价
	 */
	private Double totalAmount;
	/**
	 * 付款凭证号
	 */
	private String voucher;
	/**
	 *支付状态
	 */
	private OrderStatus status;
	/**
	 * 送货时间
	 */
	private DateTypes postDate;
	/**
	 * 付款时间
	 */
	private Date payDate;
	/**
	 * 对应送水工id
	 */
	private String worker_id;
	/**
	 * 对应学生id
	 */
	private String student_id;
	/**
	 * 对应学生姓名
	 */
	private String student_name;
	/**
	 * 对应学生宿舍楼
	 */
	private String student_dormitory;
	/**
	 * 对应学生宿舍号
	 */
	private String student_romNum;
	/**
	 * 对应商家id
	 */
	private String vendor_id;
	/**
	 * 提供公司
	 */
	private String vendor_companyName;
	/**
	 * 对应区域名
	 */
	private String areaName;
	/**
	 * 商品品牌
	 */
	private String product_brand;
	/**
	 * 商品数量
	 */
	private Integer product_quantity;
	public OrderDto(Order order){
		this.address = order.getAddress();
		this.areaName = order.getAreaName();
		this.id = order.getId();
		this.payDate = order.getPayDate();
		this.payMethod = order.getPayMeth();
		this.phoneNum = order.getPhoneNum();
		this.postDate = order.getPostDate();
		this.status = order.getStatus();
		this.student_id = order.getStudent().getId();
		this.vendor_id = order.getVendor().getId();
		this.voucher = order.getVoucher();
		this.worker_id = order.getWorker().getId();
		this.student_name = order.getStudent().getUser().getRealName();
		this.student_dormitory = order.getStudent().getDormitory();
		this.student_romNum = order.getStudent().getRomNum();
		this.totalAmount = order.getTotalAmount();
		this.product_brand = order.getOrderLists().get(0).getProduct().getBrand();
		this.product_quantity = order.getOrderLists().get(0).getQuantity();
		this.vendor_companyName = order.getVendor().getCompanyName();
	}
	
	
	public Integer getProduct_quantity() {
		return product_quantity;
	}


	public void setProduct_quantity(Integer product_quantity) {
		this.product_quantity = product_quantity;
	}


	public String getProduct_brand() {
		return product_brand;
	}

	public void setProduct_brand(String product_brand) {
		this.product_brand = product_brand;
	}

	public String getVendor_companyName() {
		return vendor_companyName;
	}

	public void setVendor_companyName(String vendor_companyName) {
		this.vendor_companyName = vendor_companyName;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStudent_dormitory() {
		return student_dormitory;
	}

	public void setStudent_dormitory(String student_dormitory) {
		this.student_dormitory = student_dormitory;
	}

	public String getStudent_romNum() {
		return student_romNum;
	}

	public void setStudent_romNum(String student_romNum) {
		this.student_romNum = student_romNum;
	}

	public String getStudent_name() {
		return student_name;
	}

	public void setStudent_name(String student_name) {
		this.student_name = student_name;
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
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	public String getVoucher() {
		return voucher;
	}
	public void setVoucher(String voucher) {
		this.voucher = voucher;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
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
	public String getWorker_id() {
		return worker_id;
	}
	public void setWorker_id(String worker_id) {
		this.worker_id = worker_id;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getVendor_id() {
		return vendor_id;
	}
	public void setVendor_id(String vendor_id) {
		this.vendor_id = vendor_id;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	
}
