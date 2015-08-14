package com.gdut.action;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.gdut.base.BaseAction;
import com.gdut.base.OrderStatus;
import com.gdut.base.Role;
import com.gdut.domain.Order;
import com.gdut.domain.OrderItems;
import com.gdut.domain.PageBean;
import com.gdut.domain.Product;
import com.gdut.domain.Student;
import com.gdut.domain.User;
import com.gdut.dto.OrderDto;
import com.gdut.dto.SalesSumary;
import com.gdut.exception.MyException;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
@Controller("order")
@Scope("prototype")
@SuppressWarnings("serial")
public class OrderAction extends BaseAction<Order> {
	
	/**
	 * 将商品加到购物车
	 */
	public void addOrderItems(){
		Order order=(Order)session.get("order");
		String id=request.getParameter("product_id");
		Product product=productService.getById(id);
		if(null==order){
			User user=(User)session.get("user");
			order=new Order();
			order.setStudent(user.getStudent());
			order.setPhoneNum(user.getPhoneNum());
			order.setAddress(user.getStudent().getDormitory()+"-"+user.getStudent().getRomNum());
			order.setAreaName(user.getStudent().getAreaName());
			order.setVendor(product.getVendor());
		}

		OrderItems orderItems=new OrderItems();
		orderItems.setBrand(product.getBrand());
		orderItems.setPrice(product.getPrice());
		orderItems.setQuantity(1);
		orderItems.setProduct(product);
		order=orderService.addOrderItems(order, orderItems);
		session.put("order", order);
	}
	/**
	 * 从购物车中删除
	 */
	public void delOrderItems(){
		Order order=(Order)session.get("order");
		String id=request.getParameter("product_id");
		order=orderService.delOrderItems(order, id);
		if(order.getOrderLists()==null){
			session.remove("order");
		}
	}
	/**
	 * 从购物车中查询
	 */
	public void getOrderItems(){
		Order order=(Order)session.get("order");
		List<OrderItems> list=orderService.getOrderItems(order);
		String json=changeListToStr(list);
		try {
			sendTypeMsgAjax(json, null,"json");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 生成订单
	 * @return
	 * @throws MyException 
	 */
	public String create() throws MyException{
		Order order=(Order)session.get("order");
		order.setOrderDate(new Date());
		order.setPostDate(model.getPostDate());
		order.setStatus(OrderStatus.S_CREATE);
		orderService.save(order);
		session.remove("order");
		return "pay";
	}
	/**
	 * 跳转到购买结算页面
	 * @return
	 * @throws MyException 
	 */
	public String buy() throws MyException{
		Order order=(Order)session.get("order");
		model=order;
		return "order";
	}
	/**
	 * 改变订单商品的数量
	 */
	public void updateQuantity(){
		String id=request.getParameter("product_id");
		Integer quantity=Integer.parseInt(request.getParameter("quantity"));
		Order order=(Order)session.get("order");
		order=orderService.updateQuantity(order, id, quantity);
		session.put("order",order);
		try {
			sendTypeMsgAjax(order.getTotalAmount()+"", null,"text");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 支付
	 */
	public String pay(){
		//TODO 做支付
		return null;
	}
	/**
	 * 删除
	 * @throws MyException 
	 */
	public String delete(){
		Integer id=model.getId();
		Order order = orderService.getById(id);
		if(order.getStatus().equals(OrderStatus.S_OVERDUE)
				||order.getStatus().equals(OrderStatus.S_SENT)){
			this.addActionError("删除订单失败");
		}else{
			orderService.delete(id);
		}
		return "meesage";
	}
	/**
	 * 统计查询
	 */
	public String salesReport(){
		String id=((User)session.get("user")).getVendor().getId();
		Integer year=Integer.parseInt(request.getParameter("year"));
		Integer month=Integer.parseInt(request.getParameter("month"));
		List<SalesSumary> list=orderService.salesReport(id, year, month);
		request.setAttribute("list",list);
		return "salesReport";
	}
	/**
	 * 商家 查询订单
	 * @return
	 */
	public String findForVendor(){
		String id=((User)session.get("user")).getVendor().getId();
		String areaName=request.getParameter("areaName");
		Integer year=Integer.parseInt(request.getParameter("year"));
		Integer month=Integer.parseInt(request.getParameter("month"));
		Integer day=Integer.parseInt(request.getParameter("day"));
		String conditions = "h.vendor.id=? and h.areaName=? and year(h.orderDate)=? and month(h.orderDate)=? and dayofmonth(h.orderDate)=?";
		PageBean pageBean=orderService.findByConditions(pageNum, pageSize, conditions,id,areaName, year, month,day);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findByConditons";
	}
	/**
	 * 学生查询订单
	 */
	public String findForStudent(){
		String id=((Student)session.get("student")).getId();
		Integer year=Integer.parseInt(request.getParameter("year"));
		Integer month=Integer.parseInt(request.getParameter("month"));
		StringBuilder conditions=new StringBuilder();
		List<Object> list=new ArrayList<Object>();
		conditions.append("h.student.id=? ");
		list.add(id);
		if(null!=year){
			conditions.append("and year(h.orderDate)=?");
			list.add(year);
		}
		if(null!=month){
			conditions.append("and month(h.orderDate)=?");
			list.add(month);
		}
		PageBean pageBean=orderService.findByConditions(pageNum, pageSize, conditions.toString(), list.toArray());
		ActionContext.getContext().getValueStack().push(pageBean);
		//转发到订单信息页面
		return "stuOrders";
	}
	/**
	 * 送水工查询订单
	 */
	public String findForWorker(){
		String areaName=((User)session.get("user")).getWorker().getAreaName();
		String conditions="h.areaName=? and h.status=S_PAYMENT";
		PageBean pageBean=orderService.findByConditions(pageNum, pageSize, conditions, areaName);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "workerOrders";
	}
	/**
	 * 跳转到管理员查询订单页面
	 */
	public String toFindForAdmin(){
		return "orderInfo";
	}
	/**
	 * 管理员查询订单
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void findForAdmin(){
		Map<String,Object> map = new HashMap<String,Object>();
		String areaName=request.getParameter("areaName");
		String dateStr = request.getParameter("dateStr");
		System.out.println(areaName);
		System.out.println(dateStr);
		Date date1 = null;
		Date date2 = null;
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(null!=dateStr&&!"".equals(dateStr.trim())){
			try {
				date1 = simple.parse(dateStr+" 00:00:00");
				date2 = simple.parse(dateStr+" 23:59:59");
			} catch (ParseException e1) {
				e1.printStackTrace();
				throw new RuntimeException();
			}
		}
		StringBuilder buffer = new StringBuilder();
		List<Object> alias = new ArrayList<Object>();
		if(null!=areaName&&!"".equals(areaName.trim())){
			buffer.append("h.areaName=?");
			alias.add(areaName);
		}
		if(null!=dateStr&&!"".equals(dateStr.trim())){
			if(null!=areaName&&!"".equals(areaName.trim())){
				buffer.append(" and h.orderDate between ? and ? ");
			}else{
				buffer.append(" h.orderDate between ? and ? ");
			}
			alias.add(date1);
			alias.add(date2);
		}
		String conditions = buffer.toString();
		PageBean pageBean=orderService.findByConditions(pageNum, pageSize, conditions,alias.toArray());
		List<Order> list =(List<Order>)pageBean.getRecordList();
		List<OrderDto> listDto = new ArrayList<OrderDto>();
		for(Order order:list){
			OrderDto orderDto = new OrderDto(order);
			listDto.add(orderDto);
		}
		pageBean.setRecordList(listDto);
		map.put("pager",pageBean);
		String json = JSONObject.fromObject(map).toString();
		try{
			sendTypeMsgAjax(json, null, "json");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 管理员撤销订单
	 */
	public void cancel(){
		Map<String,Object> map = new HashMap<String,Object>();
		Gson gson = new Gson();
		Order order = orderService.getById(Integer.valueOf(model.getId()));
		if(order.getStatus().equals(OrderStatus.S_OVERDUE)
				||order.getStatus().equals(OrderStatus.S_SENT)){
			map.put("errorCode","0003");
			map.put("errorMessage","撤销订单失败");
		}else{
			order.setStatus(OrderStatus.S_OVERDUE);
			orderService.update(order);
			map.put("errorCode","0000");
		}
		String json = gson.toJson(map);
		try{
			sendTypeMsgAjax(json, null, "json");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
