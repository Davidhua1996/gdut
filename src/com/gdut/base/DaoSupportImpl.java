package com.gdut.base;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.transaction.annotation.Transactional;

import com.gdut.domain.PageBean;
import com.gdut.util.QueryHelper;
@Transactional(readOnly=true)
@SuppressWarnings({ "unchecked", "rawtypes" })
public abstract class DaoSupportImpl<T> implements DaoSupport<T> {

	@Resource
	private SessionFactory sessionFactory;
	protected Class<T> clazz;

	public DaoSupportImpl() {
		// 使用反射技术得到T的真实类型
		ParameterizedType pt = (ParameterizedType) this.getClass()
				.getGenericSuperclass(); // 获取当前new的对象的 泛型的父类 类型
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0]; // 获取第一个类型参数的真实类型
		System.out.println("class: " + clazz);
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	@Transactional(readOnly=false)
	public void save(T entity) {
		getSession().save(entity);
	}
	@Transactional(readOnly=false)
	public void update(T entity) {
		getSession().update(entity);
	}
	@Transactional(readOnly=false)
	public void delete(String id) {
		T obj = getById(id);
		if (obj != null) {
			getSession().delete(obj);
		}
	}
	public void delete(Integer id){
		T obj = getById(id);
		if (null != obj){
			getSession().delete(obj);
		}
	}
	public T getById(String id) {
		if (id == null) {
			return null;
		} else {
			return (T) getSession().get(clazz, id);
		}
	}
	public T getById(Integer id){
		if (id == null){
			return null;
		}else{
			return (T) getSession().get(clazz, id);
		}
	}
	public List<T> getByIds(String[] ids) {
		return getSession().createQuery(//
				"FROM "+clazz.getSimpleName()+" WHERE id IN (:ids)")//
				.setParameterList("ids", ids)//
				.list();
	}
	public List<T> getByIds(Integer[] ids){
		return getSession().createQuery(
				"FROM "+clazz.getSimpleName()+" WHERE id IN (:ids)")//
				.setParameterList("ids", ids)//
				.list();
	}
	public List<T> findAll() {
		return getSession().createQuery(//
				"FROM " + clazz.getSimpleName())//
				.list();
	}

	public T findByNameAndPass(String name, String password) {
		return (T) getSession()
				.createQuery(//
						"FROM " + clazz.getSimpleName()
								+ " b WHERE b.loginName=? and passWord=?")//
				.setParameter(0, name).setParameter(1, password).uniqueResult();
	}
	@Transactional(readOnly=false)
	// 公共的查询分页信息的方法（最终版）
	public PageBean getPageBean(int pageNum, int pageSize,
			QueryHelper queryHelper) {
		// 参数列表
		List<Object> parameters = queryHelper.getParameters();

		// 查询本页的数据列表
		Query listQuery = getSession().createQuery(
				queryHelper.getListQueryHql()); // 创建查询对象
		if (parameters != null) // 设置参数
			for (int i = 0; i < parameters.size(); i++)
				listQuery.setParameter(i, parameters.get(i));

		listQuery.setFirstResult((pageNum - 1) * pageSize);
		listQuery.setMaxResults(pageSize);
		List list = listQuery.list(); // 执行查询

		// 查询总记录数量
		Query countQuery = getSession().createQuery(
				queryHelper.getCountQueryHql());
		if (parameters != null) // 设置参数
			for (int i = 0; i < parameters.size(); i++)
				countQuery.setParameter(i, parameters.get(i));

		Long count = (Long) countQuery.uniqueResult(); // 执行查询
		return new PageBean(pageNum, pageSize, count.intValue(), list);
	}

	@Override
	public <N> List<N> findBySql(String sql, Class<N> clz, Object... args) {
		SQLQuery query=getSession().createSQLQuery(sql);
		if(args!=null){
			for(int i=0;i<args.length;i++){
				query.setParameter(i, args[i]);
			}
		}
		query.setResultTransformer(Transformers.aliasToBean(clz));
		return (List<N>)query.list();
	}
	
}
