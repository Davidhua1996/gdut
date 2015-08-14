package com.gdut.service;

import java.util.List;

import com.gdut.base.DaoSupport;
import com.gdut.domain.Order;
import com.gdut.domain.OrderItems;
import com.gdut.domain.PageBean;
import com.gdut.dto.SalesSumary;


public interface OrderService extends DaoSupport<Order> {
	public  List<OrderItems> findItems(String id);
	public List<SalesSumary> salesReport(String vendor_id,Integer year,Integer month);
	/**
	 * 根据商家、年、月、区查订单(分页）
	 */
	public PageBean findByConditions(int pageNum, int pageSize,String condition,Object...args);
	/**
	 * 向购物车里添加
	 */ 
	public Order addOrderItems(Order order,OrderItems orderItems);
	/**
	 * 从购物车删除
	 */
	public Order delOrderItems(Order order,String id);
	/**
	 * 计算总价
	 */
	public Double cluMoney(Order order);
	/**
	 * 查询购物车里信息
	 */
	public List<OrderItems> getOrderItems(Order order);
	/**
	 * 修改购物车商品数量
	 */
	public Order updateQuantity(Order order,String id,Integer quantity);
	/**
	 * 订单过期
	 */
	public void overDue();
}
