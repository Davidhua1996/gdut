package com.gdut.base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;


import com.gdut.service.AreaService;
import com.gdut.service.FeedBackService;
import com.gdut.service.OrderService;
import com.gdut.service.ProductService;
import com.gdut.service.StudentService;
import com.gdut.service.UserService;
import com.gdut.service.VendorService;
import com.gdut.service.WorkerService;
import com.gdut.sms.SMSService;
import com.gdut.util.EmailUtil;
import com.gdut.util.SendEmail;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings({  "serial", "unchecked"})
public abstract class BaseAction<T> extends ActionSupport implements
			ModelDriven<T>,ServletRequestAware,ServletResponseAware,SessionAware {
	// =============== ModelDriven的支持 ==================
	protected T model;
	public BaseAction() {
		try {
			// 通过反射获取model的真实类型
			ParameterizedType pt = (ParameterizedType) this.getClass()
					.getGenericSuperclass();
			Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
			// 通过反射创建model的实例
			model = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public T getModel() {
		return model;
	}

	/**
	 * 把obj转换为json数组
	 * 
	 * @param list
	 *            对象
	 */
	public String changeListToStr(Object obj) {
		if (obj.equals(null) || null == obj) {
			return null;
		} else {
			JSONArray json = JSONArray.fromObject(obj);
			return json.toString();
		}
	}

	/**
	 * 使用ajax发送text数据
	 * 
	 * @param content
	 * @throws IOException
	 */
	public void sendMsgAjax(String content, String code) throws IOException {
		if (code == null) {
			response.setCharacterEncoding("UTF-8");
		} else {
			response.setCharacterEncoding("" + code + "");
		}
		response.getWriter().write(content);
		response.setContentType("text/html");
		response.getWriter().close();
	}

	/**
	 * 使用ajax发送text数据
	 * 
	 * @param content
	 * @param type
	 * @throws IOException
	 */
	public void sendTypeMsgAjax(String content, String code, String type)
			throws IOException {
		if (code == null) {
			response.setCharacterEncoding("UTF-8");
		} else {
			response.setCharacterEncoding("" + code + "");
		}

		if ("text".equals(type)) {
			response.setContentType("text/html;charset=utf-8");
		} else if ("json".equals(type)) {
			response.setContentType("application/json");
		}
		response.getWriter().write(content);
		response.getWriter().close();
	}

	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}
	// =============== 图片上传 ==================
	//上传图片
	protected File picture;
	protected String pictureFileName;
	protected String pictureContentType;		
	protected File getPicture() {
		return picture;
	}

	public void setPicture(File picture) {
		this.picture = picture;
	}

	public String getPictureFileName() {
		return pictureFileName;
	}

	public void setPictureFileName(String pictureFileName) {
		this.pictureFileName = pictureFileName;
	}
	public String getPictureContentType() {
		return pictureContentType;
	}

	public void setPictureContentType(String pictureContentType) {
		this.pictureContentType = pictureContentType;
	}
	// =============== Service实例的声明 ==================
	@Resource
	protected UserService userService;
	@Resource
	protected OrderService orderService;
	@Resource
	protected StudentService studentService;
	@Resource
	protected AreaService areaService;
	@Resource
	protected ProductService productService;
	@Resource
	protected FeedBackService feedBackService;
	@Resource
	protected WorkerService workerService;
	@Resource 
	protected VendorService vendorService;
	@Resource(name="SMSService")
	protected SMSService smsService;
	@Resource(name="emailUtil")
	protected EmailUtil emailUtil;
	//=====================input页面资源文件================================
	@Resource
	protected Properties inputProperties;
	//input页面地址
	public String inputURL;
	
	public String getinputURL() {
		return inputURL;
	}

	public void setinputURL(String inputURL) {
		this.inputURL = inputURL;
	}
	// ======================分页数据=========================
	protected int pageNum = 1;// 当前页
	protected int pageSize = 6;// 每页显示多少条

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	//======================发送邮件的配置====================================
	protected SendEmail sendEmail;
	// ======================实现Aware接口=========================
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected Map<String,Object> session;
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
		
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
		
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;;
		
	}
	
	
}

