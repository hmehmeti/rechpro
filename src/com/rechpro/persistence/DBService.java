package com.rechpro.persistence;

import java.util.List;

public class DBService<T> {

	private String className;
	
	/**
	 * requires class name of the correspond entity class name as string
	 * @param className
	 */
	public DBService(String className){
		this.className = className;
	}
	
	public void addEntity(T entity) {
		HibernateUtil.executeHibernateTransaction("add", entity);
	}
	
	public void removeEntity(T entity) {
		HibernateUtil.executeHibernateTransaction("remove", entity);
	}
	
	public void updateEntity(T entity) {
		HibernateUtil.executeHibernateTransaction("update", entity);
	}
	
	public List<T> getEntities() {
		HibernateUtil<T> hibernateUtil = new HibernateUtil<>();
		List<T> entities = hibernateUtil.getObjectsWithHibernateTransaction(className);
		return entities;
	}
}
