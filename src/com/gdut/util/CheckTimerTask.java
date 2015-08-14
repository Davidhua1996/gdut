package com.gdut.util;

import java.util.List;
import java.util.TimerTask;
import com.gdut.domain.User;
import com.gdut.service.UserService;
/**
 * 检查未审核的商家，向管理员发送邮件
 * @author David
 *
 */
public class CheckTimerTask extends TimerTask {
	private UserService userService;
	private EmailUtil emailUtil;
	public CheckTimerTask(UserService userService,EmailUtil emailUtil){
		this.userService=userService;
		this.emailUtil = emailUtil;
	}
	@Override
	public void run() {
		List<User> list=userService.getAuditedVendor();
		for(User user :list){
			SendEmail sendEmail = new SendEmail(emailUtil);
			sendEmail.setContent("有新的商家注册定水系统,请尽快审核!<br><a href='http://localhost:8080/gdut/userAction_activate.action?vendorId="+
					user.getId()+"&activatedCode="+user.getActivatedCode()+"'>现在去审核</a>");
			sendEmail.setTitle("注册商家审核");
			sendEmail.setAddress("dingshuixitong@163.com");
			sendEmail.start();
		}
	}
	
}
