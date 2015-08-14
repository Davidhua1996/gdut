package com.gdut.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gdut.base.BaseAction;
import com.gdut.domain.FeedBack;
import com.gdut.domain.PageBean;
import com.gdut.domain.Student;
import com.gdut.domain.User;
import com.gdut.domain.Vendor;
import com.gdut.dto.FeedBackDto;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;

@Controller("feedBack")
@Scope("prototype")
@SuppressWarnings("serial")
public class FeedBackAction extends BaseAction<FeedBack> {
	/**
	 * 跳转到查询反馈信息页面
	 */
	public String toList(){
		return "feedBackList";
	}
	/**
	 * 查找学生的反馈信息
	 */
	@SuppressWarnings("unchecked")
	public void student(){
		Map<String,Object> map = new HashMap<String,Object>();
		Gson gson = new Gson();
		PageBean pageBean=feedBackService.findForStu(pageNum, pageSize);
		List<FeedBackDto> listDto = new ArrayList<FeedBackDto>();
		List<FeedBack> list = (List<FeedBack>)pageBean.getRecordList();
		for(FeedBack feedBack:list){
			listDto.add(new FeedBackDto(feedBack));
		}
		pageBean.setRecordList(listDto);
		map.put("pager",pageBean);
		String json = gson.toJson(map);
		try{
			sendTypeMsgAjax(json, null, "json");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 查找商家的反馈信息
	 */
	@SuppressWarnings("unchecked")
	public void vendor(){
		Map<String,Object> map = new HashMap<String,Object>();
		Gson gson = new Gson();
		PageBean pageBean=feedBackService.findForVendor(pageNum,pageSize);
		List<FeedBackDto> listDto = new ArrayList<FeedBackDto>();
		List<FeedBack> list = (List<FeedBack>)pageBean.getRecordList();
		for(FeedBack feedBack:list){
			listDto.add(new FeedBackDto(feedBack));
		}
		pageBean.setRecordList(listDto);
		map.put("pager",pageBean);
		String json = gson.toJson(map);
		try{
			sendTypeMsgAjax(json, null, "json");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 增加反馈
	 */
	public void addFeedBack(){
		User user =(User) session.get("user");
		Student student = (Student) session.get("student");
		Map<String,Object> resp = new HashMap<String,Object>();
		if(null != user){
			Vendor vendor = user.getVendor();
			model.setVendor(vendor);
		}else{
			model.setStudent(student);
		}
		feedBackService.save(model);
		resp.put("resp","添加反馈成功");
		String json=changeListToStr(resp);
		try {
			sendTypeMsgAjax(json, null,"json");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 删除反馈
	 */
	public void delete(){
		String id = model.getId();
		feedBackService.delete(id);
	}
}
