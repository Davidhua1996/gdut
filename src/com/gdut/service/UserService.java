package com.gdut.service;

import java.util.List;

import com.gdut.base.DaoSupport;
import com.gdut.base.Role;
import com.gdut.domain.PageBean;
import com.gdut.domain.User;

public interface UserService extends DaoSupport<User>
{
	/**
	 * 重用名查询
	 * @param name
	 * @return
	 */
	public User findByName(String name);
	/**
	 * 条件查询学生信息
	 */
	public PageBean findStuByDiv(Integer pageNum,Integer pageSize, 
			String name, String major, String areaName, String dormitory);
	/**
	 * 分页查询所有用户
	 * @param pageNum
	 * @param pageSize
	 * @param role
	 * @return
	 */
	public PageBean findByDiv(Integer pageNum,Integer pageSize,Role role);
	/**
	 * 根据用户名模糊查询
	 * @param pageNum
	 * @param pageSize
	 * @param name
	 * @return
	 */
	public PageBean fuzzyQuery(Integer pageNum,Integer pageSize,String name,Role role);
	/**
	 * 查找有激活码，未审核的商家
	 * @return
	 */
	public List<User> getAuditedVendor();
	/**
	 * 查询未审核商家
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageBean auditedVendors(Integer pageNum,Integer pageSize);
	/**
	 * 查询黑名单
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageBean blackList(Integer pageNum,Integer pageSize);
}
