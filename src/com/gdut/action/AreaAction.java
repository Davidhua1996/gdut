package com.gdut.action;

import java.io.IOException;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gdut.base.BaseAction;
import com.gdut.domain.Area;

@Controller("area")
@Scope("prototype")
@SuppressWarnings("serial")
public class AreaAction extends BaseAction<Area>{
	/**
	 * 发送区域数据
	 */
	public void areaMessage(){
		List<Area> list=areaService.findAll();
		String json=changeListToStr(list);
		System.out.println(json);
		try {
			sendTypeMsgAjax(json, null, "json");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("a");
	}
}
	

