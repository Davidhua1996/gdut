package com.gdut.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import com.gdut.domain.Vendor;
import com.gdut.dto.VendorDto;
import com.gdut.dto.WorkerDto;
import com.gdut.exception.MyException;
import com.gdut.util.Encrypter;
import com.gdut.util.SendEmail;
import com.opensymphony.xwork2.ActionContext;
/**
 * 商家信息操作
 * @author David
 *
 */
@Controller("vendor")
@Scope("prototype")
@SuppressWarnings("serial")
public class VendorAction extends BaseAction<User> {
	/**
	 * 修改信息前
	 */
	public String prepareforUpdate() {
		User user=(User)session.get("user");
		User newUser=userService.getById(user.getId());
		model=newUser;
		return "vendorUpdate";
	}
	/**
	 * 修改密码
	 * @return
	 */
	public String prepareforPassword(){
		User user=(User)session.get("user");
		User newUser=userService.getById(user.getId());
		model=newUser;
		return "vendorUpdatePassword";
	}
	/**
	 * 跳转到查询信息的页面
	 */
	public String toVendorInfo(){
		return "vendorInfo";
	}
	/**
	 * 查询详细信息
	 */
	@SuppressWarnings("unchecked")
	public void usersInfo(){
		Map<String,Object> map = new HashMap<String,Object>(); 
		PageBean pageBean=userService.findByDiv(pageNum, pageSize, Role.BUSINESS);
		List<User> list =(List<User>) pageBean.getRecordList();
		List<VendorDto> listDto = new ArrayList<VendorDto>();
		for(User user:list){
			VendorDto vendorDto = new VendorDto(user);
			listDto.add(vendorDto);
		}
		pageBean.setRecordList(listDto);
		map.put("pager",pageBean);
		JSONObject json= JSONObject.fromObject(map);
		String pager = json.toString();
		System.out.println(pager);
		try {
			sendTypeMsgAjax(pager, null, "json");
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	/**
	 * 模糊查询
	 */
	public String fuzzyQuery(){
		String name=model.getLoginName();
		PageBean pageBean=userService.fuzzyQuery(pageNum, pageSize, name,Role.BUSINESS);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "QueryResult";
	}
	/**
	 * 跳转到查询未审核商家页面
	 */
	public String toAuditedVendors(){
		return "auditedVendors";
	}
	/**
	 * 查询未审核商家
	 */
	@SuppressWarnings("unchecked")
	public void auditedVendors(){
		Map<String,Object> map = new HashMap<String,Object>();
		PageBean pageBean = userService.auditedVendors(pageNum, pageSize);
		List<User> list =(List<User>) pageBean.getRecordList();
		List<VendorDto> listDto = new ArrayList<VendorDto>();
		for(User user:list){
			VendorDto vendorDto = new VendorDto(user);
			listDto.add(vendorDto);
		}
		pageBean.setRecordList(listDto);
		map.put("pager",pageBean);
		JSONObject json= JSONObject.fromObject(map);
		String pager = json.toString();
		System.out.println(pager);
		try {
			sendTypeMsgAjax(pager, null, "json");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 跳转到查询黑名单的页面
	 */
	public String toBlackList(){
		return "blackList";
	}
	/**
	 * 查询黑名单
	 */
	@SuppressWarnings("unchecked")
	public void blackList(){
		Map<String,Object> map = new HashMap<String,Object>();
		PageBean pageBean = userService.blackList(pageNum, pageSize);
		List<User> list =(List<User>) pageBean.getRecordList();
		List<VendorDto> listDto = new ArrayList<VendorDto>();
		for(User user:list){
			VendorDto vendorDto = new VendorDto(user);
			listDto.add(vendorDto);
		}
		pageBean.setRecordList(listDto);
		map.put("pager",pageBean);
		JSONObject json= JSONObject.fromObject(map);
		String pager = json.toString();
		try {
			sendTypeMsgAjax(pager, null, "json");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 管理员修改商家信息(ajax)
	 */
	public void vendorUpdateAdmin(){
		User user=userService.getById(model.getId());
		//将商家拉入黑名单或者白名单或者删除
		System.out.println("OK");
		System.out.println(user.getId());
		System.out.println(model.getStatus());
		if(null!=model.getStatus()){
			user.setStatus(model.getStatus());
		}
		if(null!=model.getAddress()&&!"".equals(model.getAddress().trim())){
			user.setAddress(model.getAddress());
		}
		if(null==model.getVendor()){
			userService.update(user);
			return;
		}
		if(null!=model.getVendor().getCompanyName()&&!"".equals(model.getVendor().getCompanyName())){
			user.getVendor().setCompanyName(model.getVendor().getCompanyName());
		}
		if(null!=model.getVendor().getBankCard()&&!"".equals(model.getVendor().getBankCard())){
			user.getVendor().setBankCard(model.getVendor().getBankCard());
		}
		if(null!=model.getPhoneNum()&&!"".equals(model.getPassword())){
			user.setPhoneNum(model.getPhoneNum());
		}
		if(null!=model.getVendor().getAreaName()&&!"".equals(model.getVendor().getAreaName())){
			user.getVendor().setAreaName(model.getVendor().getAreaName());
		}
		if(null!=model.getVendor().getBuildingName()&&!"".equals(model.getVendor().getBuildingName())){
			user.getVendor().setBuildingName(model.getVendor().getBuildingName());
		}
		if(null!=picture){
			String path=ServletActionContext.getServletContext().getRealPath("/upload/vendor");
			File file=new File(path+user.getVendor().getPicUrl());
			file.delete();
			String ext=pictureFileName.substring(pictureFileName.indexOf("."));
			String newName=UUID.randomUUID().toString().replace("-", "")+ext;
			file=new File(path+newName);
			try {
				FileUtils.copyFile(picture,file);
				user.getVendor().setPicUrl(newName);
				picture.delete();
			} catch (IOException e) {
				e.printStackTrace();
			}
			userService.update(user);
			try {
				sendTypeMsgAjax(user.getVendor().getPicUrl(), null,"text");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		userService.update(user);
	}
	/**
	 * 修改商家信息的验证(商家)
	 */
	public void validateVendorUpdate(){
		inputURL=inputProperties.getProperty("vendorUpdate");
		if(null==model.getAddress()||"".equals(model.getAddress().trim())){
			this.addFieldError("address", "地址不能为空");
		}
		if(null==model.getVendor().getCompanyName()||"".equals(model.getVendor().getCompanyName().trim())){
			this.addFieldError("vendor.companyName", "公司名不能为空");
		}
		if(null==model.getVendor().getBankCard()||"".equals(model.getVendor().getBankCard().trim())){
			this.addFieldError("vendor.bankCard", "银行卡号不为空");
		}
		if(null==model.getPhoneNum()||"".equals(model.getPhoneNum().trim())){
			this.addFieldError("phoneNum", "电话号码不能为空");
		}
		if(null==model.getVendor().getAreaName()||"".equals(model.getVendor().getAreaName().trim())){
			this.addFieldError("vendor.areaName","区域不能为空");
		}
		if(null==model.getVendor().getBuildingName()||"".equals(model.getVendor().getBuildingName().trim())){
			this.addFieldError("vendor.buildingName","请输入楼号");
		}
	}
	/**
	 * 修改商家信息(商家)
	 */
	public String vendorUpadte(){
		String path=ServletActionContext.getServletContext().getRealPath("/upload/vendor");
		User user=userService.getById(model.getId());
		user.setAddress(model.getAddress());
		user.getVendor().setCompanyName(model.getVendor().getCompanyName());
		user.getVendor().setBankCard(model.getVendor().getBankCard());
		user.setPhoneNum(model.getPhoneNum());
		user.getVendor().setAreaName(model.getVendor().getAreaName());
		user.getVendor().setBuildingName(model.getVendor().getBuildingName());
		if(null!=picture){
			File file=new File(path+user.getVendor().getPicUrl());
			file.delete();
			String ext=pictureFileName.substring(pictureFileName.indexOf("."));
			String newName=UUID.randomUUID().toString().replace("-", "")+ext;
			file=new File(path+newName);
			try {
				FileUtils.copyFile(picture,file);
				user.getVendor().setPicUrl(newName);
				picture.delete();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		userService.save(user);
		return "vendor";
	}
	/**
	 * 商家修改密码的验证
	 * @throws MyException 
	 */
	public void validateUpdatePassword() throws MyException{
		inputURL=inputProperties.getProperty("vendorUpdatePassword");
		String oldPassword=(String)request.getParameter("oldPassword");
		oldPassword=Encrypter.encodePassword(model.getLoginName(), model.getPassword());
		String newPassword1=(String)request.getParameter("newPassword1");
		String newPassword2=(String)request.getParameter("newPassword2");
		
		if(null==oldPassword||"".equals(oldPassword.trim())){
			this.addFieldError("oldPassword","请输入原始密码");
		}
		if(oldPassword!=((User)session.get("user")).getPassword()){
			this.addFieldError("oldPassword","密码不一致");
		}
		if(null==newPassword1||"".equals(newPassword1.trim())){
			this.addFieldError("newPassword1","请输入新密码");
		}
		if(null==newPassword2||"".equals(newPassword2.trim())){
			this.addFieldError("newPassword2","请再一次输入新密码");
		}
		if(newPassword2!=newPassword1){
			this.addFieldError("newPassword2","输入密码不一致");
		}
	}
	/**
	 * 商家修改密码
	 * @throws MyException 
	 */
	public String updatePassword(){
		User user=(User)session.get("user");
		user.setPassword((String)request.getParameter("newPassword1")); 
		userService.update(user);
		session.put("user", user);
		//返回商家主页面
		return "vendorMain";
	}
	/**
	 * 商家审核
	 * @return
	 * @throws MyException 
	 */
	public String activate() throws MyException{
		String vendorId=request.getParameter("vendorId");
		String activatedCode=request.getParameter("activatedCode");
		if(null!=vendorId&null!=activatedCode){
			User user=userService.getById(vendorId);
			if(null!=user&user.getActivatedCode()==activatedCode){
				model=user;
				return "activate";
			}else{
				throw new MyException("验证信息有误");
			}
		}else{
			throw new MyException("非法访问");
		}
	}
	/**
	 * 商家审核通过
	 * @return
	 * @throws MyException 
	 */
	public String activatedPass() throws MyException{
		User user=userService.getById(model.getId());
		if(null!=user&&user.getActivatedCode().equals(model.getActivatedCode())){
			System.out.println("sendEmail");
			user.setStatus(UserStatus.S_APPROVED);
			user.setActivatedCode(null);
			userService.update(user);
			sendEmail = new SendEmail(emailUtil);
			sendEmail.setContent("成功通过定水商家审核");
			sendEmail.setTitle("定水审核通知");
			sendEmail.setAddress(user.getEmail());
			sendEmail.start();
			return "exmainSuccess";
		}else{
			throw new MyException("非法访问");
		}
	}
	/**
	 * 商家审核不通过
	 */
	public String activatedFail() throws MyException{
		User user=userService.getById(model.getId());
		String mesg=request.getParameter("message");
		System.out.println(mesg);
		if(null!=user&&user.getActivatedCode().equals(model.getActivatedCode())){
			sendEmail = new SendEmail(emailUtil);
			userService.delete(model.getId());
			sendEmail.setContent("用户注册审核失败，失败原因:<br>"+mesg);
			sendEmail.setTitle("商家用户审核失败");
			sendEmail.setAddress(user.getEmail());
			sendEmail.start();
		}else{
			throw new MyException("非法访问");
		}
		return "exmainSuccess";
	}
	/**
	 * 商家找回密码
	 */
	public void VendorGetBackPwd(){
		
	}
}
