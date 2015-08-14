package com.gdut.dto;

import java.math.BigDecimal;

public class SalesSumary {
	private String areaName;
	private BigDecimal quantity;
	private Integer yy;
	private Integer mm;
	
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public Integer getYy() {
		return yy;
	}
	public void setYy(Integer yy) {
		this.yy = yy;
	}
	public Integer getMm() {
		return mm;
	}
	public void setMm(Integer mm) {
		this.mm = mm;
	}
	

}
