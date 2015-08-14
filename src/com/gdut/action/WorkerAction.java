package com.gdut.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gdut.base.BaseAction;
import com.gdut.base.Role;
import com.gdut.domain.PageBean;
import com.gdut.domain.User;
import com.gdut.dto.WorkerDto;
import com.gdut.util.Encrypter;
@Controller("worker")
@Scope("prototype")
@SuppressWarnings("serial")
public class WorkerAction extends BaseAction<User>{
	/**
	 * 送水工找回密码
	 */
	public void WorkerGetBackPwd(){
		String loginName = model.getLoginName();
		String result = null;
		Random random = new Random();
		Map<String,Object> resp = new HashMap<String,Object>();
		StringBuilder newPwd = new StringBuilder();
		if(null==loginName){
			result = "请输入用户名";
		}else{
			User user = userService.findByName(model.getLoginName());
			if(null!=user&&user.getRole()==Role.WORKER){
				for(int i=0;i<8;i++){
					newPwd.append(random.nextInt(10));
				}
				user.setPassword(Encrypter.encodePassword(user.getLoginName(), newPwd.toString()));
				userService.update(user);
				result = smsService.sendMsg(user.getPhoneNum(), newPwd.toString());
			}else{
				result = "输入用户名有误";
			}
		}
		resp.put("resp", result);
		String json=changeListToStr(resp);
		try {
			sendTypeMsgAjax(json, null,"json");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 跳转到查询送水工信息页面
	 */
	public String toWorkerInfo(){
		return "workerInfo";
	}
	/**
	 * 查询送水工详细信息
	 */
	@SuppressWarnings("unchecked")
	public void workerInfo(){
		Map<String,Object> map = new HashMap<String,Object>();
		PageBean pageBean=userService.findByDiv(pageNum, pageSize, Role.WORKER);
		List<User> list =(List<User>) pageBean.getRecordList();
		List<WorkerDto> listDto = new ArrayList<WorkerDto>();
		for(User user:list){
			WorkerDto workerDto = new WorkerDto(user);
			listDto.add(workerDto);
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
	 * 管理员修改送水工信息+密码(ajax)
	 */
	public void workerUpdate(){
		User user=userService.getById(model.getId());
		if(null!=model.getPassword()&&!"".equals(model.getPassword().trim())){
			String newPwd = Encrypter.encodePassword(user.getLoginName(), model.getPassword());
			user.setPassword(newPwd);
			//向用户发送新密码
			smsService.sendMsg(user.getPhoneNum(),model.getPassword());
		}
		if(null!=model.getRealName()&&!"".equals(model.getRealName().trim())){
			user.setRealName(model.getRealName());
		}
		if(null!=model.getStatus()&&!"".equals(model.getStatus())){
			user.setStatus(model.getStatus());
		}
		if(null==model.getWorker()){
			userService.update(user);
			return;
		}
		if(null!=model.getWorker().getAreaName()&&!"".equals(model.getWorker().getAreaName().trim())){
			user.getWorker().setAreaName(model.getWorker().getAreaName());
		}
		if(null!=model.getWorker().getBuildingName()&&!"".equals(model.getWorker().getBuildingName())){
			user.getWorker().setBuildingName(model.getWorker().getBuildingName());
		}
		userService.update(user);
	}
}
