package com.gdut.service;

import com.gdut.base.DaoSupport;
import com.gdut.domain.FeedBack;
import com.gdut.domain.PageBean;

public interface FeedBackService extends DaoSupport<FeedBack>{
	/**
	 * 查找学生的反馈信息
	 */
	public PageBean findForStu(Integer pageNum ,Integer pageSize);
	/**
	 * 查找商家的反馈信息
	 */
	public PageBean findForVendor(Integer pageNum,Integer pageSize);
}
