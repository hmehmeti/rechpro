package com.rechpro.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.SessionFactoryObserver;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * This code was taken almost in entirety from the Hibernate 3.1 reference
 * manual. The package name and formatting has been changed only.
 */
public class HibernateUtil<T> {
	private static final SessionFactory sessionFactory;
	private static final ServiceRegistry serviceRegistry;

	static {
		try {
			// Get the configuration from hibernate.cfg.xml
			Configuration config = new Configuration().configure("/resources/hibernate.cfg.xml");

			serviceRegistry = new StandardServiceRegistryBuilder().configure("/resources/hibernate.cfg.xml").build();
			config.setSessionFactoryObserver(new SessionFactoryObserver() {
				private static final long serialVersionUID = 1L;

				@Override
				public void sessionFactoryCreated(SessionFactory factory) {
				}

				@Override
				public void sessionFactoryClosed(SessionFactory factory) {
					StandardServiceRegistryBuilder.destroy(serviceRegistry);
				}
			});
			sessionFactory = config.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static Session openSession() {
		return sessionFactory.openSession();
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static void executeHibernateTransaction(String operation, Object object) {
		Session s = HibernateUtil.openSession();
        s.beginTransaction();
        switch(operation) {
        case "add" : s.save(object); break;
        case "remove" : s.delete(object); break;
        case "update" : s.update(object); break;
        }
        s.getTransaction().commit();
        s.close();
	}
	
	public List<T> getObjectsWithHibernateTransaction(String tableName) {
		List<T> list = new ArrayList<>();
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		list = s.createQuery("from ".concat(tableName)).list();
		s.getTransaction().commit();
		s.close();
		return list;
	}
}
