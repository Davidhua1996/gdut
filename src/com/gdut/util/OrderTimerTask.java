package com.gdut.util;

import java.util.TimerTask;
import com.gdut.service.OrderService;
//订单过期计时器
public class OrderTimerTask extends TimerTask{
	private OrderService orderService;
	public OrderTimerTask(OrderService orderService){
		this.orderService=orderService;
	}
	
	@Override
	public void run() {
		orderService.overDue();
	}
	
}
