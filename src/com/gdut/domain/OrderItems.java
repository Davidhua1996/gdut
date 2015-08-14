package com.gdut.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@SuppressWarnings("serial")
@Entity
@Table(name="T_ORDER_ITEMS")
public class OrderItems implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	/**
	 * 品牌
	 */
	@Column(length=32,nullable=false)
	private String brand;
	/**
	 * 单价
	 */
	@Column(scale=2)
	private double price;
	/**
	 * 总价
	 */
	@Column(scale=2)
	private double totalPrice;
	/**
	 * 数量
	 */
	@Column
	private Integer quantity;
	/**
	 * 折扣
	 */
	@Column
	private Integer discount;
	/**
	 * 对应订单order
	 * @ManyToOne
	 */
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE})
	@JoinColumn(name="order_id")
	private Order order;
	/**
	 * 对应商品
	 * @ManyToOne
	 */
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
	

	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getDiscount() {
		return discount;
	}
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	
	
}
