package com.gdut.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.gdut.base.BaseAction;
import com.gdut.base.UserStatus;
import com.gdut.domain.PageBean;
import com.gdut.domain.Student;
import com.gdut.domain.User;
import com.gdut.dto.StudentDto;
import com.gdut.exception.MyException;
import com.opensymphony.xwork2.ActionContext;
@SuppressWarnings("serial")
@Controller("student")
@Scope("prototype")
public class StudentAction extends BaseAction<User> {
	//====================学生信息修改================================
	/**
	 * 跳转到修改用户信息
	 */
	public String prerparedForUpate(){
		User user = (User)session.get("user");
		model=user;
		return "studentUpdate";
	}
	/**
	 * 修改用户信息验证
	 * @throws MyException
	 */
	public void validateUpdate() throws MyException{
		inputURL=inputProperties.getProperty("studentUpdate");
		if(null==model.getId()||"".equals(model.getId().trim())){
			throw new MyException("非法访问");
		}
		if(null==model.getPhoneNum()||"".equals(model.getPhoneNum())){
			this.addFieldError("phoneNum", "电话号码不能为空");
		}
		if(null==model.getStudent()){
			this.addActionError("请完善学生信息");
		}
		if(null==model.getRealName()||"".equals(model.getRealName())){
			this.addFieldError("student.name","学生姓名不能为空");
		}
		if(null==model.getStudent().getMajor()||"".equals(model.getStudent().getMajor())){
			this.addFieldError("student.major", "专业不能为空");
		}
		if(null==model.getStudent().getClazz()||"".equals(model.getStudent().getClazz())){
			this.addFieldError("student.clazz", "班级不能为空");
		}
		if(null==model.getStudent().getAreaName()||"".equals(model.getStudent().getAreaName())){
			this.addFieldError("student.area", "区域不能为空");
		}
		if(null==model.getStudent().getDormitory()||"".equals(model.getStudent().getDormitory())){
			this.addFieldError("student.dormitory", "宿舍楼号不能为空");
		}
		if(null==model.getStudent().getRomNum()||"".equals(model.getStudent().getRomNum())){
			this.addFieldError("student.romNum", "宿舍房号不能为空");
		}
	}
	/**
	 * 修改学生信息
	 */
	public String update(){
		User user = userService.getById(model.getId());
		Student student = user.getStudent();
		user.setPhoneNum(model.getPhoneNum());
		student.setMajor(model.getStudent().getMajor());
		student.setClazz(model.getStudent().getClazz());
		student.setAreaName(model.getStudent().getAreaName());
		student.setDormitory(model.getStudent().getDormitory());
		student.setRomNum(model.getStudent().getRomNum());
		userService.update(user);
		session.put("user",user);
		// 跳转到添加购物车页面
		return "buy";
	}
	//===================管理员查询学生信息=============================
	/**
	 * 跳转到查询学生信息的页面
	 */
	public String toStudentInfo(){
		return "studentInfo";
	}
	/**
	 * 分页查询并塞选学生信息
	 */
	@SuppressWarnings("unchecked")
	public void findAll(){
		Map<String,Object> map = new HashMap<String,Object>();
		List<StudentDto> listDto = new ArrayList<StudentDto>();
		System.out.println("realName:"+model.getRealName()+"\nmajor:"+model.getStudent().getMajor()+
				"\nareaName:"+model.getStudent().getAreaName()+"\ndormitory:"+model.getStudent().getDormitory());
		PageBean pageBean=userService.findStuByDiv(pageNum, pageSize,
				model.getRealName(),model.getStudent().getMajor(),
				model.getStudent().getAreaName(),model.getStudent().getDormitory());
		List<User> list =(List<User>) pageBean.getRecordList();
		for(User user : list){
			StudentDto studentDto = new StudentDto(user);
			listDto.add(studentDto);
		}
		pageBean.setRecordList(listDto);
		map.put("pager", pageBean);
		JSONObject json = JSONObject.fromObject(map);
		String pager = json.toString();
		try{
			sendTypeMsgAjax(pager, null, "json");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//======================管理员删除、修改学生信息========================================
	/**
	 * 删除学生信息(ajax)
	 *  
	 */
	public void delStu(){
		User user =userService.getById(model.getId());
		if(null!=user){
			user.setStatus(UserStatus.S_DELETE);
			userService.update(user);
		}
	}
	/**
	 * 修改学生信息(ajax)
	 * @return
	 */
	public void updateStu(){
		User user = userService.getById(model.getId());
		Student student  = user.getStudent();
		student.setAreaName(model.getStudent().getAreaName());
		student.setDormitory(model.getStudent().getDormitory());
		student.setRomNum(model.getStudent().getRomNum());
		student.setClazz(model.getStudent().getClazz());
		student.setMajor(model.getStudent().getMajor());
		user.setRealName(model.getRealName());
		userService.update(user);
	}
	/**
	 * 添加学生
	 * @return
	 */
	public void addStu(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("erroMessage", "");
		if(null==model.getLoginName()){
			map.put("errorMessage","学号不能为空");
		}
		if(null==model.getRealName()){
			map.put("errorMessage","学生姓名不能为空");
		}
		if(null==model.getStudent().getClazz()){
			map.put("errorMessage","学生班级不能为空");
		}
		if(null==model.getStudent().getDormitory()||
				null==model.getStudent().getAreaName()||
				  null==model.getStudent().getRomNum()){
			map.put("errorMessage","学生宿舍信息不能为空");
		}
		model.setRegDate(new Date());
		model.setStatus(UserStatus.S_APPROVED);
		userService.save(model);
		JSONObject json = JSONObject.fromObject(map);
		String message = json.toString();
		try{
			sendTypeMsgAjax(message, null,"json");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
