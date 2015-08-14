package com.gdut.service;

import java.util.List;

import com.gdut.base.DaoSupport;
import com.gdut.domain.PageBean;
import com.gdut.domain.Product;

public interface ProductService extends DaoSupport<Product> {
	public void findbyDetails( int pageNum, int pageSize,String brand,String name);
	public List<Product> findbyArea(String area);
	/**
	 * 
	 * 查找对应区域和栋商品的详细信息
	 * @param area
	 * @param building
	 * @return
	 */
	public List<Product> findbyAreaAndBuiding(String area,String building);
	public Product findbyBrand(String brand);
	/**
	 * 管理员查询所有商品
	 * @param pageNum
	 * @param pageSize
	 * @return 
	 */
	public PageBean findbyDiv(Integer pageNum, Integer pageSize);
	/**
	 * 根据商家查找商品
	 * @param id
	 * @return
	 */
	public List<Product> findbyVendor(String id);
}
