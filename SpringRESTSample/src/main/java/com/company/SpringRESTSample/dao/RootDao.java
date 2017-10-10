package com.company.SpringRESTSample.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;

public abstract class RootDao <K,T extends Serializable>{

	private Class<T> persistenceClass;
	
	@PersistenceContext
	EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public RootDao() {
		persistenceClass = (Class<T>) ((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}
	
	protected Session getSession()
	{
		return entityManager.unwrap(Session.class);
	}
	
	protected EntityManager getEntityManager()
	{
		return entityManager;
	}
	
	protected T getById(K id)
	{
		return (T) entityManager.find(persistenceClass, id);
	}

	protected void persist(T entity)
	{
		entityManager.persist(entity);
	}
	
	protected void update(T entity)
	{
		entityManager.merge(entity);
	}
	
	protected void delete(T entity)
	{
		entityManager.remove(entity);
	}
	
	protected Criteria getCriteria()
	{
		return getSession().createCriteria(persistenceClass);
	}
}
