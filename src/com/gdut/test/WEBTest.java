package com.gdut.test;

/**
 * 测试类
 */
//import java.util.Properties;
//import java.util.Timer;
//import java.util.TimerTask;
//
//import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import com.gdut.domain.Order;
//import com.gdut.service.OrderService;
import com.gdut.sms.SMSService;
import com.gdut.util.Encrypter;
//import com.gdut.util.EmailUtil;
//import com.gdut.util.FileUploadUtil;
//import com.gdut.util.OrderTimerTask;

public class WEBTest {
	private static ClassPathXmlApplicationContext context;
//	private static OrderService service;
//	private static FileUploadUtil fileUploadUtil;
//	private static Properties props;
//	private static EmailUtil email;
//	private static TimerTask order;
	private static SMSService sms;
	@BeforeClass
	public static void before(){
		context=new ClassPathXmlApplicationContext("applicationContext.xml");
//		order=(TimerTask)context.getBean("orderTimerTask");
//		service=(OrderService)context.getBean("orderService");
		sms = (SMSService)context.getBean("SMSService");
	}
//	@AfterClass
//	public static void after(){
//		email=null;
//		context.destroy();
//	}
	@org.junit.Test
	public void findItems() throws InterruptedException {
//		email.setVendorId("wwww");
//		email.setActivatedCode("adbcsgsd");
//		email.start();
//		Thread.sleep(1000000);
//		order=new OrderTimerTask();
		sms.sendMsg("13527956557", "xxx");
	}
	@org.junit.Test
	public void createAdmin(){
		String loginName = "admin";
		String password = "666666";
		password = Encrypter.encodePassword(loginName, password);
		System.out.println("loginName："+loginName+"\npassword:"+password);
	}
}
