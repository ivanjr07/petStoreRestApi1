package com.petStore.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

public abstract class BaseDao<T, ID extends Serializable> {
	private Class<T> entityType;
	protected HibernateTemplate hibernateTemplate;
	
	@SuppressWarnings("unchecked")
	public BaseDao() {
		entityType = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];		
	}
	
	@SuppressWarnings("unchecked")
	public T findById(ID id) {
		return (T) hibernateTemplate.get(entityType.getName(), id);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll(String orderBy) {
		String query = "from " + entityType.getName();
		if(orderBy != null) {
			query += " order by " + orderBy;
		}		
		return hibernateTemplate.find(query);
	}
	
	public List<T> findAll() {
		return findAll(null);
	}
	
	public void save(T obj){
		hibernateTemplate.save(obj);
	}
	
	public void update(T obj) {
		hibernateTemplate.update(obj);
	}
	 public void saveupdate(T obj) {
	        hibernateTemplate.saveOrUpdate(obj);
	    }
	public void delete(T obj) {
		hibernateTemplate.delete(obj);
	}
	
	public void flush() {
		hibernateTemplate.flush();
	}
	
	@Autowired
	public void setSesssionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
}
