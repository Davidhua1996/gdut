package com.gdut.action;



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.gdut.base.BaseAction;
import com.gdut.base.Role;
import com.gdut.base.UserStatus;
import com.gdut.domain.PageBean;
import com.gdut.domain.User;
import com.gdut.dto.VendorDto;
import com.gdut.dto.WorkerDto;
import com.gdut.exception.MyException;
import com.gdut.util.Encrypter;
import com.gdut.util.SendEmail;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
/**
 * 用户基本操作
 * @author David
 *
 */
@Controller("user")
@Scope("prototype")
@SuppressWarnings("serial")
public class UserAction extends BaseAction<User>{
	//===================用户注册跳转=============================
	/** 
	 * 跳转到送水工注册
	 */
	public String workerRegisterPage(){
		return "workerRegister";
	}
	/**
	 * 跳转到商家注册页面
	 */
	public String vendorRegisterPage(){
		return "vendorRegister";
	}
	//====================注册验证=============================
	/**
	 * 公共验证方法
	 */
		private void registerValidation(){
			if(null==model.getLoginName()||"".equals(model.getLoginName().trim())){
				this.addFieldError("loginName","用户名不能为空");
			}
			if(null!=userService.findByName(model.getLoginName())){
				this.addFieldError("loginName", "该用户名已经被注册");
			}
			if(null==model.getPassword()||"".equals(model.getPassword().trim())){
				this.addFieldError("password","请输入密码");
			}
			if(null==model.getAddress()||"".equals(model.getAddress().trim())){
				this.addFieldError("address", "地址不能为空");
			}
			if(null==model.getPhoneNum()||"".equals(model.getPhoneNum().trim())){
				this.addFieldError("phoneNum", "电话号码不能为空");
			}
			
			if(null!=model.getQuestionOne()&&null==model.getAnswerOne()){
				this.addFieldError("AnswerOne", "问题答案不能为空");
			}
			if(null!=model.getQuestionTwo()&&null==model.getAnswerTwo()){
				this.addFieldError("AnswerTwo", "问题答案不能为空");
			}
		}
	/**
	 * 商家注册验证
	 */
	public void validateVendorRegister(){
		inputURL=inputProperties.getProperty("vendorRegister");
		registerValidation();
		if(null==model.getVendor()){
			this.addActionError("请填写商家信息");
		}
		if(null==model.getVendor().getCompanyName()||"".equals(model.getVendor().getCompanyName())){
			this.addFieldError("vendor.companyName", "请选择公司名称");
		}
		if(null==model.getVendor().getAreaName()||"".equals(model.getVendor().getAreaName())){
			this.addFieldError("vendor.areaName", "请选择区域信息");
		}
		if(null==model.getVendor().getBuildingName()||"".equals(model.getVendor().getBuildingName())){
			this.addFieldError("vendor.buildingName","请选择楼号");
		}
		if(null==picture){
			this.addFieldError("picture", "请上传卫生许可证");
		}
	}
	/**
	 * 学生注册验证
	 */
	public void validateStuRegister(){
		inputURL=inputProperties.getProperty("studentRegister");
		registerValidation();
		if(null==model.getRealName()||"".equals(model.getRealName())){
			this.addFieldError("student.name","请填写学生姓名");
		}
		if(null==model.getStudent()){
			this.addActionError("请填写学生信息");
		}
		if(null==model.getStudent().getClazz()||"".equals(model.getStudent().getClazz())){
			this.addFieldError("student.clazz","请填写班级名称");
		}
		if(null==model.getStudent().getDormitory()||"".equals(model.getStudent().getDormitory())){
			this.addFieldError("student.dormitory","请填写宿舍楼号");
		}
		if(null==model.getStudent().getAreaName()||"".equals(model.getStudent().getAreaName())){
			this.addFieldError("student.areaName","请填写宿舍区域");
		}
		if(null==model.getStudent().getMajor()||"".equals(model.getStudent().getMajor())){
			this.addFieldError("student.major","请填写专业信息");
		}
		if(null==model.getStudent().getRomNum()){
			this.addFieldError("student.major","请填写宿舍号");
		}
	}
	//================用户注册==============================
	/**
	 * 送水工注册(管理员)
	 */
	public void workerRegister(){
		Map<String,Object> map = new HashMap<String,Object>();
		Gson gson = new Gson();
		if(null == model.getRealName()&&"".equals(model.getRealName())){
			map.put("errorMessage","请输入真实姓名");
		}
		if(null == model.getLoginName()&&"".equals(model.getLoginName())){
			map.put("errorMessage","用户名不能为空");
		}
		if(null != userService.findByName(model.getLoginName())){
			map.put("errorMessage", "该用户名已经被注册");
		}
		if(null == model.getPassword()||"".equals(model.getPassword().trim())){
			map.put("errorMessage","请输入密码");
		}
		if(null != model.getWorker()){
			if(null == model.getWorker().getAreaName()||"".equals(model.getWorker().getAreaName())){
				map.put("errorMessage","请选择区域信息");
			}
			if(null == model.getWorker().getBuildingName()||"".equals(model.getWorker().getBuildingName())){
				map.put("errorMessage","请选择楼号");
			}
		}
		if(!map.isEmpty()){
			map.put("errorCode","0003");
			String json = gson.toJson(map);
			try{
				sendTypeMsgAjax(json, null,"json");
			}catch(Exception e){
				e.printStackTrace();
			}
			return;
		}
		model.setRegDate(new Date());
		model.setLoginDate(new Date());
		//设定为已审核状态
		model.setStatus(UserStatus.S_APPROVED);
		userService.save(model);
		map.put("errorCode","0000");
		String json = gson.toJson(map);
		try{
			sendTypeMsgAjax(json, null,"json");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 商家注册
	 */
	public String vendorRegister(){
		System.out.println(model.getLoginName());
		System.out.println("Pass");
		String path=ServletActionContext.getServletContext().getRealPath("/upload/vendor");
		String answerOne=model.getAnswerOne();
		String answerTwo=model.getAnswerTwo();
		String ext=pictureFileName.substring(pictureFileName.indexOf("."));
		String newName=UUID.randomUUID().toString().replace("-", "")+ext;
		File file=new File(path,newName);
		try{
			FileUtils.copyFile(picture,file);
		}catch(IOException e){
			e.printStackTrace();
		}
		picture.delete();
		//判断是否添加密保问题
		answerOne=null!=answerOne&&!"".equals(answerOne.trim())?Encrypter.encodeDES(answerOne):"";
		answerTwo=null!=answerTwo&&!"".equals(answerTwo.trim())?Encrypter.encodeDES(answerOne):"";
		model.setAnswerOne(answerOne);
		model.setAnswerOne(answerOne);
		model.setRegDate(new Date());
		//设定为未审核状态
		model.setStatus(UserStatus.S_AUDIT);
		model.getVendor().setPicUrl(newName);
		//随机生成激活码
		String activatedCode=UUID.randomUUID().toString().replace("-","");
		model.setActivatedCode(activatedCode);
		userService.save(model);
		User user=userService.findByNameAndPass(model.getLoginName(), model.getPassword());
		sendEmail = new SendEmail(emailUtil);
		sendEmail.setContent("有新的商家注册定水系统,请尽快审核!<br><a href='http://localhost:8080/gdut/userAction_activate.action?vendorId="+
							user.getId()+"&activatedCode="+activatedCode+"'>现在去审核</a>");
		sendEmail.setTitle("注册商家审核");
		sendEmail.setAddress("dingshuixitong@163.com");
		sendEmail.start();
		//返回注册成功页面
		return "registerSuccess";
	}
	/**
	 * 学生注册
	 */
	public String stuRegister(){
		String answerOne=model.getAnswerOne();
		String answerTwo=model.getAnswerTwo();
		//判断是否添加密保问题
		answerOne=null!=answerOne&&!"".equals(answerOne.trim())?Encrypter.encodeDES(answerOne):"";
		answerTwo=null!=answerTwo&&!"".equals(answerTwo.trim())?Encrypter.encodeDES(answerOne):"";
		model.setAnswerOne(answerOne);
		model.setAnswerOne(answerOne);
		model.setRegDate(new Date());
		//设定为已审核状态
		model.setStatus(UserStatus.S_APPROVED);
		model.setLoginDate(new Date());
		userService.save(model);
		//返回注册成功页面
		return "registerSuccess";
	}
	//============用户登录验证=======================
	/**
	 * 登录验证
	 * @throws MyException 
	 */
	public void validateLogin() throws MyException{
		String code=request.getParameter("code");
		Role role=model.getRole();
		//判断登录页面的input页面地址
		if(role==Role.WORKER){
			inputURL=inputProperties.getProperty("workerLogin");
		}
		if(role==Role.BUSINESS){
			inputURL=inputProperties.getProperty("vendorLogin");
		}
		if(role==Role.ADMIN){
			inputURL=inputProperties.getProperty("adminLogin");
		}
		if(null==role){
			throw new MyException("非法访问");
		}
		if(model.getRole()!=Role.ADMIN){
			if(null==code||"".equals(code.trim())){
				this.addFieldError("code", "验证码不能为空");
			}
		}
		if(null==model.getLoginName()||"".equals(model.getLoginName())){
			this.addFieldError("loginName","用户名不能为空");
		}
		if(null==model.getPassword()||"".equals(model.getPassword())){
			this.addFieldError("password","请输入密码");
		}
		if(model.getRole()!=Role.ADMIN){
			if(!(((String)session.get("indentifyCode")).equals(code))){
				this.addFieldError("code", "验证码不正确");
			}
		}
	}
	//============用户登录=======================
	/**
	 * 登录
	 * @return
	 * @throws MyException 
	 */
	public String login() {
		Role role=model.getRole();
		String loginName=model.getLoginName();
		String password=model.getPassword();
		System.out.println(loginName+""+password);
		User user=userService.findByNameAndPass(loginName, password);
		//判断用户是否存在，登录页面是否对应
		if(null!=user&&role==user.getRole()){
			if(user.getStatus()==UserStatus.S_BLACKLIST){
				this.addActionError("该用户已被列入黑名单");
				return INPUT;
			}
			if(user.getStatus()==UserStatus.S_AUDIT){
				//返回登陆界面
				this.addActionError("该用户未激活");
				return INPUT;
			}
			user.setLoginDate(new Date());
			session.put("user", user);
			if(role==Role.WORKER){
				return "worker";
			}else if(role==Role.BUSINESS){
				return "business";
			}else{
				return "auditedVendors";
			}
		}else {
			//返回登陆界面
			this.addActionError("用户或密码不存在");
			return INPUT;
		}
	}
	//==================退出登录========================
	public String withdraw(){
		User user=(User)session.get("user");
		Role role=user.getRole();
		session.remove("user");
		if(role==Role.ADMIN){
			return "adminLogin";
		}else if(role==Role.BUSINESS){
			return "vendorLogin";
		}else{
			return "workerLogin";
		}
		
	}
	//============验证码生成=======================
	/**
	 * 生成验证码
	 */
	public void indentifyCode(){
		int width=80;
		int height=30;
		BufferedImage image=new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
		Graphics g=image.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.white);
		g.fillRect(1, 1, width-2, height-2);
		Random random=new Random();
		String num="0123456789abcdefghijklmnopqrstuvwxyz";
		StringBuilder code=new StringBuilder();
		g.setFont(new Font("楷体",Font.BOLD, 21));
		for(int i=0;i<4;i++){
			Color c=new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
			g.setColor(c);
			int begin=random.nextInt(num.length()-2);
			g.drawString(num.substring(begin,begin+1),i*20,17);
			code.append(num.substring(begin,begin+1));
		}
		for(int i=0;i<7;i++){
			Color c=new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
			g.setColor(c);
			g.drawLine(random.nextInt(80), random.nextInt(40), random.nextInt(80), random.nextInt(40));
		}
		session.put("indentifyCode",code.toString());
		try {
			ImageIO.write(image, "jpeg", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
