package com.gdut.dto;

import com.gdut.domain.Product;

public class ProductDto {
	/**
	 * 商品id
	 */
	private Integer id;
	/**
	 * 商品品牌
	 */
	private String brand;
	/**
	 * 商品描述
	 */
	private String description;
	/**
	 * 商品价格
	 */
	private Float price;
	/**
	 * 商品图片
	 */
	private String picUrl;
	/**
	 * 商品存货
	 */
	private Integer stock;
	/**
	 * 对应商家id
	 */
	private String vendor_id;
	/**
	 * 对应商家公司
	 */
	private String vendor_companyName;
	/**
	 * 对应商家供水区域
	 */
	private String vendor_areaName;
	
	public ProductDto(Product product){
		this.brand = product.getBrand();
		this.description = product.getDescription();
		this.id = product.getId();
		this.picUrl = product.getPicUrl();
		this.price = product.getPrice();
		this.stock = product.getStock();
		this.vendor_areaName = product.getVendor().getAreaName();
		this.vendor_companyName = product.getVendor().getCompanyName();
		this.vendor_id = product.getVendor().getId();
	}

	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public String getVendor_id() {
		return vendor_id;
	}
	public void setVendor_id(String vendor_id) {
		this.vendor_id = vendor_id;
	}
	public String getVendor_companyName() {
		return vendor_companyName;
	}
	public void setVendor_companyName(String vendor_companyName) {
		this.vendor_companyName = vendor_companyName;
	}
	public String getVendor_areaName() {
		return vendor_areaName;
	}
	public void setVendor_areaName(String vendor_areaName) {
		this.vendor_areaName = vendor_areaName;
	}
	
}
