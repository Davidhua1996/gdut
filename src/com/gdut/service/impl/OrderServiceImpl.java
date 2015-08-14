package com.gdut.service.impl;


import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gdut.base.DaoSupportImpl;
import com.gdut.base.OrderStatus;
import com.gdut.domain.Order;
import com.gdut.domain.OrderItems;
import com.gdut.domain.PageBean;
import com.gdut.dto.SalesSumary;
import com.gdut.service.OrderService;
import com.gdut.util.QueryHelper;
@Service("orderService")
public class OrderServiceImpl extends DaoSupportImpl<Order> implements OrderService{

	@Override
	public List<OrderItems> findItems(String id) {
		Order order=this.getById(id);
		List<OrderItems> list=order.getOrderLists();
		return list;
	}
	/**
	 * 生成报表对象
	 */
	@Override
	public List<SalesSumary> salesReport(String vendor_id,Integer year,Integer month) {
		String sql="select h.areaName,year(h.orderDate) yy,month(h.orderDate) mm,sum(i.quantity) quantity "+
					"from t_order h inner join t_order_item i "+
					"where h.id=i.order_id and h.status='S_PAYMENT' and year(h.orderDate)=? and month(h.orderDate)=? "+
					"group by h.areaName,yy,mm"; 
		return findBySql(sql, SalesSumary.class, year,month);
		
	}
	/**
	 * 多条件查订单(分页）
	 */
	@Override
	public PageBean findByConditions(int pageNum, int pageSize,String conditions, Object ... args){
		QueryHelper query=new QueryHelper(this.clazz,"h");
		if(null!=conditions&&!"".equals(conditions.trim())){
			query.addCondition(conditions, args);
		}
		query.addCondition("h.status!='S_OVERDUE'");
		return getPageBean(pageNum, pageSize, query);
	}
	/**
	 * 向购物车里添加
	 */
	@Override
	public Order addOrderItems(Order order, OrderItems orderitem) {
		//判断是否有商品重复
		boolean isHave=false;
		for(OrderItems item:order.getOrderLists()){
			if(item.getProduct().getId()==orderitem.getProduct().getId()){
				item.setQuantity(item.getQuantity()+1);
				item.setTotalPrice(item.getQuantity()*item.getPrice());
				isHave=true;
				break;
			}
		}
		if(!isHave){
			orderitem.setOrder(order);
			orderitem.setTotalPrice(orderitem.getQuantity()*orderitem.getPrice());
			order.getOrderLists().add(orderitem);
		}
		order.setTotalAmount(cluMoney(order));
		return order;
	}
	/**
	 * 计算总价
	 */
	@Override
	public Double cluMoney(Order order) {
		Double totalPrice=0.0;
		for(OrderItems item:order.getOrderLists()){
			totalPrice+=item.getQuantity()*item.getPrice();
		}
		return totalPrice;
	}
	/**
	 * 从购物车里删除
	 */
	public Order delOrderItems(Order order,String id){
		for(OrderItems item:order.getOrderLists()){
			if(item.getProduct().getId().toString()==id){
				order.getOrderLists().remove(item);
				break;
			}
		}
		order.setTotalAmount(cluMoney(order));
		return order;
	}
	/**
	 * 查询购物车里信息
	 */
	public List<OrderItems> getOrderItems(Order order){
		return order.getOrderLists();
	}
	/**
	 * 修改购物车商品数量
	 */
	public Order updateQuantity(Order order,String id,Integer quantity){ 
		for(OrderItems item:order.getOrderLists()){
			if(item.getProduct().getId().toString()==id){
				item.setQuantity(quantity);
				item.setTotalPrice(item.getQuantity()*item.getPrice());
				break;
			}
		}
		order.setTotalAmount(cluMoney(order));
		return order;
	}
	/**
	 * 订单过期
	 */
	@Transactional(readOnly=false)
	public void overDue(){
		Date date=new Date();
		long time=date.getTime()-1000*60*30;
		date=new Date(time);
		//更新订单
		SQLQuery query=getSession().createSQLQuery("update t_order h set h.status='S_OVERDUE' where h.status='S_CREATE' and h.orderDate<?");
		query.setParameter(0, date);
		query.executeUpdate();
	}
}
