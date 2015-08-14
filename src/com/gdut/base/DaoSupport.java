package com.gdut.base;



import java.util.List;

import com.gdut.domain.PageBean;
import com.gdut.util.QueryHelper;

public interface DaoSupport<T> {

	/**
	 * 保存实体
	 * 
	 * @param entity
	 */
	void save(T entity);

	/**
	 * 删除实体
	 * 
	 * @param id
	 */
	void delete(String id);
	void delete(Integer id);
	/**
	 * 更新实体
	 * 
	 * @param entity
	 */
	void update(T entity);

	/**
	 * 按id查询
	 * 
	 * @param id
	 * @return
	 */
	T getById(String id);
	T getById(Integer id);

	/**
	 * 按id查询
	 * 
	 * @param ids
	 * @return
	 */
	List<T> getByIds(String[] ids);
	List<T> getByIds(Integer[] ids);
	/**
	 * 查询所有
	 * 
	 * @return
	 */
	List<T> findAll();
	/**
	 * 
	 * 分页查找
	 * @param name
	 * @param password
	 * @return
	 */
	T findByNameAndPass(String name, String password);

	
	/**
	 * 公共的查询分页信息的方法（最终版）
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param queryHelper
	 *            HQL语句与参数列表
	 * @return
	 */
	PageBean getPageBean(int pageNum, int pageSize, QueryHelper queryHelper);
	/**
	 * 用sql语句查出结果集，转换成数据传输类对象
	 * 
	 */
	<N>List<N> findBySql(String sql,Class<N> clz,Object... args);
}
