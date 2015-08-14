package com.gdut.listener;

import java.util.Timer;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


import com.gdut.service.OrderService;
import com.gdut.service.UserService;
import com.gdut.util.CheckTimerTask;
import com.gdut.util.EmailUtil;
import com.gdut.util.OrderTimerTask;

public class MyServletContextListener implements ServletContextListener{
	private WebApplicationContext context=null;
	private OrderService orderService=null;
	private UserService userService=null;
	private OrderTimerTask orderTimerTask=null;
	private CheckTimerTask checkTimerTask=null;
	private EmailUtil emailUtil=null;
	@Override
	public void contextDestroyed(ServletContextEvent se) {
		// TODO Auto-generated method stub
	}

	@Override
	public void contextInitialized(ServletContextEvent se) {
		context=WebApplicationContextUtils.getWebApplicationContext(se.getServletContext());
		orderService=(OrderService)context.getBean("orderService");
		userService=(UserService)context.getBean("userService");
		emailUtil=(EmailUtil)context.getBean("emailUtil");
		orderTimerTask=new OrderTimerTask(orderService);
		checkTimerTask=new CheckTimerTask(userService,emailUtil); 
//		new Timer(true).schedule(checkTimerTask, 0, 1000*60*60*24);
//		new Timer(true).schedule(orderTimerTask, 0, 1000*60);
	}
	
}
