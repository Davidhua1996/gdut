package com.gdut.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
import com.gdut.domain.PageBean;
import com.gdut.domain.Product;
import com.gdut.domain.Student;
import com.gdut.domain.User;
import com.gdut.dto.ProductDto;
@Controller("product")
@Scope("prototype")
@SuppressWarnings("serial")
public class ProductAction extends BaseAction<Product> {
	//===================商品查询===========================
	/**
	 * 查找对应区域和栋商品的详细信息(ajax)
	 */
	public void findbyStu(){
		Student student=(Student)session.get("student");
		String areaName=student.getAreaName();
		String buildingName=student.getDormitory();
		List<Product> list=productService.findbyAreaAndBuiding(areaName, buildingName);
		String json=changeListToStr(list);
		try {
			sendTypeMsgAjax(json, null,"json");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 根据商家查找商品(ajax)
	 */
	public void findbyVendor(){
		User user=(User)session.get("user");
		String id=user.getVendor().getId();
		List<Product> list=productService.findbyVendor(id);
		String json=changeListToStr(list);
		try {
			sendTypeMsgAjax(json, null,"json");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//==================管理员查找商品==============================
	/**
	 * 跳转到商品列表页面
	 */
	public String toProductInfo(){
		return "productInfo";
	}
	/**
	 * 查找商品列表
	 */
	@SuppressWarnings("unchecked")
	public void findProducts(){
		Map<String,Object> map = new HashMap<String,Object>();
		PageBean pageBean = productService.findbyDiv(pageNum, pageSize);
		List<Product> list = (List<Product>)pageBean.getRecordList();
		List<ProductDto> listDto = new ArrayList<ProductDto>();
		for(Product product : list){
			ProductDto productDto = new ProductDto(product);
			listDto.add(productDto);
		}
		pageBean.setRecordList(listDto);
		map.put("pager", pageBean);
		JSONObject json = JSONObject.fromObject(map);
		System.out.println(json.toString());
		try{
			sendTypeMsgAjax(json.toString(),null, "json");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//==================商品上架=======================
	/**
	 * 转发到商家上架页面
	 */
	public String savePage(){
		return "savePage";
	}
	/**
	 * 商品上架验证
	 */
	public void validateSave(){
		inputURL=inputProperties.getProperty("productSave");
		if(null==model.getBrand()||"".equals(model.getBrand().trim())){
			this.addFieldError("brand", "请输入品牌");
		}
		if(null==model.getStock()){
			this.addFieldError("stock", "请输入商品数量");
		}
		if(null==model.getDescription()||"".equals(model.getDescription().trim())){
			this.addFieldError("description", "请填写商品详情");
		}
		if(0==model.getPrice()){
			this.addFieldError("price", "请填写价格");
		}
		if(null==picture){
			this.addFieldError("picture", "请上传商品图片");
		}
	}
	/**
	 * 商品上架
	 */
	public String save(){
		User user=(User)session.get("user");
		String path=ServletActionContext.getServletContext().getRealPath("/upload/product");
		String ext=pictureFileName.substring(pictureFileName.indexOf("."));
		String newName=UUID.randomUUID().toString().replace("-", "")+ext;
		File file=new File(path,newName);
		try{
			FileUtils.copyFile(picture, file);
		}catch(IOException e){
			e.printStackTrace();
		}
		picture.delete();
		model.setState(1);
		model.setVendor(user.getVendor());
		model.setAddTime(new Date());
		productService.save(model);
		return "saveSuccess";
	}
	//==================商品状态改变=======================
	/**
	 * 改变商品上下架状态
	 */
	public String stateChange(){
		Product product=productService.getById(model.getId().toString());
		product.setState(model.getState());
		productService.update(product);
		return "stateChangeSuccess";
	}
	//===================商品补货=============================
	/**
	 * 转发到补货界面
	 * @return
	 */
	public String replenishPage(){
		Product product=productService.getById(model.getId().toString());
		model=product;
		return "replenishPage";
	}
	/**
	 * 商品补货验证
	 */
	public void validateReplenish(){
		inputURL=inputProperties.getProperty("productReplenish");
		if(null==model.getStock()){
			this.addFieldError("stock", "请输入商品数量");
		}
	}
	/**
	 * 商品补货
	 * @return
	 */
	public String replenish(){
		Product product=productService.getById(model.getId().toString());
		product.setStock(product.getStock()+model.getStock());
		productService.update(product);
		return "replenishSuccess";
	}
	//===================商品信息修改=============================
	/**
	 * 转发到信息修改界面
	 * @return
	 */
	public String preparedUpdate(){
		Product product=productService.getById(model.getId().toString());
		model=product;
		return "productUpdate";
	}
	/**
	 * 商品信息修改验证
	 */
	public void validateUpdate(){
		inputURL=inputProperties.getProperty("productUpdate");
		if(null==model.getDescription()||"".equals(model.getDescription().trim())){
			this.addFieldError("description", "请填写商品详情");
		}
		if(0==model.getPrice()){
			this.addFieldError("price", "请填写价格");
		}
	}
	/**
	 * 商品信息修改
	 * @return
	 */
	public String update(){
		Product product=productService.getById(model.getId().toString());
		if(null!=picture){
			String path=ServletActionContext.getServletContext().getRealPath("/upload/product");
			String ext=pictureFileName.substring(pictureFileName.indexOf("."));
			String newName=UUID.randomUUID().toString().replace("-", "")+ext;
			File file=new File(path,product.getPicUrl());
			file.delete();
			file=new File(path,newName);
			try{
				FileUtils.copyFile(picture, file);
			}catch(IOException e){
				e.printStackTrace();
			}
			picture.delete();
			product.setPicUrl(newName);
		}
		product.setDescription(model.getDescription());
		product.setPrice(model.getPrice());
		productService.update(product);
		return "productUpdateSuccess";
	}
	
}
