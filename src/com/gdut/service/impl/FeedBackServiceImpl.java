package com.gdut.service.impl;


import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.gdut.base.DaoSupportImpl;
import com.gdut.domain.FeedBack;
import com.gdut.domain.PageBean;
import com.gdut.service.FeedBackService;
import com.gdut.util.QueryHelper;
@Service("feedBackServiceImpl")
public class FeedBackServiceImpl extends DaoSupportImpl<FeedBack> implements FeedBackService{
	/**
	 * 查找商家的反馈信息
	 */
	public PageBean findForStu(Integer pageNum ,Integer pageSize){
		QueryHelper queryHelper=new QueryHelper(this.clazz,"f");
//		queryHelper.addCondition(false," left join f.student s where s is not null");
		queryHelper.addCondition("f.student is not null");
		PageBean pageBean=getPageBean(pageNum, pageSize, queryHelper);
		return pageBean;
	}

	@Override
	public PageBean findForVendor(Integer pageNum, Integer pageSize) {
		QueryHelper queryHelper=new QueryHelper(this.clazz,"f");
//		queryHelper.addCondition(false," left join f.vendor v where v is not null");
		queryHelper.addCondition("f.vendor is not null");
		PageBean pageBean=getPageBean(pageNum, pageSize, queryHelper);
		return pageBean;
	}
}
