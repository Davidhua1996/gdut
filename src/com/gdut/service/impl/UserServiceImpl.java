package com.gdut.service.impl;



import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gdut.base.DaoSupportImpl;
import com.gdut.base.Role;
import com.gdut.domain.PageBean;
import com.gdut.domain.User;
import com.gdut.service.UserService;
import com.gdut.util.Encrypter;
import com.gdut.util.QueryHelper;


@Service("userService")
public class UserServiceImpl extends DaoSupportImpl<User> implements UserService
{
	/**
	 * 加密后保存
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(User user) {
		String encodedPwd=Encrypter.encodePassword(user.getLoginName(), user.getPassword());
		user.setPassword(encodedPwd);
		super.save(user);
	}
	/**
	 * 加密后更新
	 */
	@Override
	@Transactional(readOnly=false)
	public void update(User user){
		String encodedPwd=Encrypter.encodePassword(user.getLoginName(), user.getPassword());
		user.setPassword(encodedPwd);
		super.update(user);
	}
	/**
	 * 加密后登录
	 */
	@Override
	public User findByNameAndPass(String name, String password) {
		password=Encrypter.encodePassword(name, password);
		return super.findByNameAndPass(name, password);
	}
	/**
	 * 重用名查询
	 */
	public User findByName(String name) {
		QueryHelper queryHelper=new QueryHelper(this.clazz,"u");
		queryHelper.addCondition("u.loginName=?", name);
		User user = (User)getSession().createQuery(queryHelper.getCountQueryHql()).uniqueResult();
		return user;
	}
	/**
	 * 分页查询所有用户的信息
	 */
	@Transactional(readOnly=false)
	public PageBean findByDiv(Integer pageNum,Integer pageSize,Role role){
		QueryHelper queryHelper=new QueryHelper(this.clazz,"u");
		queryHelper.addCondition("u.role=? and u.status='S_APPROVED' ",role);
		return getPageBean(pageNum, pageSize, queryHelper);
	}
	/**
	 * 根据名字模糊查询
	 */
	public PageBean fuzzyQuery(Integer pageNum,Integer pageSize,String name,Role role){
		QueryHelper queryHelper=new QueryHelper(this.clazz,"u");
		queryHelper.addCondition("u.loginName like ? and u.role=? and u.status='S_APPROVED'","%"+name+"%",role);
		return getPageBean(pageNum, pageSize, queryHelper);
	}
	/**
	 * 查询未审核商家
	 */
	public PageBean auditedVendors(Integer pageNum,Integer pageSize){
		QueryHelper queryHelper=new QueryHelper(this.clazz,"u");
		queryHelper.addCondition("u.status='S_AUDIT' and u.role='BUSINESS'");
		return getPageBean(pageNum, pageSize, queryHelper);
	}
	/**
	 * 查找有激活码，未审核的商家
	 */
	@SuppressWarnings("unchecked")
	public List<User> getAuditedVendor(){
		QueryHelper queryHelper=new QueryHelper(this.clazz,"u");
		//判断用户的激活状态和激活码是否为空
		queryHelper.addCondition("u.activatedCode!='' and u.status='S_AUDIT' ");
		String query=queryHelper.getListQueryHql();
		List<User> list= (List<User>)getSession().createQuery(query).list();
		return list;
	}
	/**
	 * 查找黑名单
	 */
	@Override
	public PageBean blackList(Integer pageNum, Integer pageSize) {
		QueryHelper queryHelper = new QueryHelper(this.clazz,"u");
		queryHelper.addCondition("u.status='S_BLACKLIST' and u.role='BUSINESS'");
		return getPageBean(pageNum, pageSize, queryHelper); 
	}
	@Override
	public PageBean findStuByDiv(Integer pageNum, Integer pageSize,
			String name, String major, String areaName, String dormitory) {
		QueryHelper queryHelper = new QueryHelper(clazz,"u");
		queryHelper.addCondition("u.status!='S_DELETE' and role='STUDENT'");
		if(null!=name&&!"".equals(name)){
			queryHelper.addCondition("u.realName like ?","%"+name+"%");
		}
		if(null!=major&&!"".equals(major)){
			queryHelper.addCondition("u.student.major like ?","%"+major+"%");
		}
		if(null!=areaName&&!"".equals(areaName)){
			queryHelper.addCondition("u.student.areaName=?", areaName);
		}
		if(null!=dormitory&&!"".equals(dormitory)){
			queryHelper.addCondition("u.student.dormitory=?", dormitory);
		}
		return getPageBean(pageNum, pageSize, queryHelper); 
	}
}
