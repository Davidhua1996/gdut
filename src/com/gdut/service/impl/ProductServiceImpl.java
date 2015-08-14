package com.gdut.service.impl;



import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gdut.base.DaoSupportImpl;
import com.gdut.domain.PageBean;
import com.gdut.domain.Product;
import com.gdut.domain.User;
import com.gdut.service.ProductService;
import com.gdut.util.QueryHelper;
@Service("productService")
@Transactional(readOnly=true)
public class ProductServiceImpl extends DaoSupportImpl<Product> implements ProductService{
	/**
	 * 模糊查询
	 */
	@Override
	public void findbyDetails( int pageNum, int pageSize, String brand, String name) {
		QueryHelper query=new QueryHelper(this.clazz,"p");
		if(null!=brand&&!brand.trim().equals("")){
			query.addCondition("p.brand=?", brand);
		}
		if(null!=name&&!name.trim().equals("")){
			query.addCondition("p.productName like ? and p.vendor.user.status='S_APPROVED'", "%"+name+"%");
		}
		query.preparePageBean(this, pageNum, pageSize);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findbyArea(String area) {
		Query query=getSession().createQuery("From Product p inner join fetch p.vendor s where s.areaName=? " +
				"and p.state=1 and s.user.status='S_APPROVED'");
		List<Product> list=query.setParameter(0, area).list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findbyAreaAndBuiding(String area, String building) {
		Query query=getSession().createQuery("From Product p inner join fetch p.vendor s where s.areaName=? and s.buildingName=? " +
				"and p.state=1 and s.user.status='S_APPROVED'");
		query.setParameter(0, area);
		query.setParameter(1, building);
		List<Product> list = query.list();
		
		return (List<Product>)query.list();
	}

	@Override
	public Product findbyBrand(String brand) {
		Query query=getSession().createQuery("From Product p where p.brand=? and p.vendor.user.status='S_APPROVED'");
		query.setParameter(0, brand);
		return (Product)query.uniqueResult();
	}

	@Override
	public PageBean findbyDiv(Integer pageNum, Integer pageSize) {
		QueryHelper query=new QueryHelper(this.clazz,"p");
		query.addCondition("p.vendor.user.status='S_APPROVED'");
		PageBean pageBean = null;
		try{
			 pageBean = getPageBean(pageNum, pageSize, query);
		}catch(Exception e){
			e.printStackTrace();
		}
		return pageBean;
	}
	@SuppressWarnings("unchecked")
	public List<Product> findbyVendor(String id){
		Query query=getSession().createQuery("From Product p where p.vendor.id=? and p.vendor.user.status='S_APPROVED'");
		query.setParameter(0, id);
		return  (List<Product>)query.list();
	}
}
