package com.vpsmith.addressbook.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class EntityManagerFactoryHelper implements ServletContextListener, ServletRequestListener {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {

		ServletRequestListener.super.requestDestroyed(sre);
		if (EntityManagerFactoryHelper.entityManager != null) {
			EntityManagerFactoryHelper.entityManager.flush();
			EntityManagerFactoryHelper.entityManager.close();
			System.out.println("Destroyed em");
		}
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		// TODO Auto-generated method stub
		ServletRequestListener.super.requestInitialized(sre);
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		entityManagerFactory = Persistence.createEntityManagerFactory("ADDRESSBOOK_JPA");
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		entityManagerFactory.close();
	}

	public static synchronized EntityManager createEntityManager() {

		if (entityManagerFactory == null) {
			throw new IllegalStateException("Context is not initialized yet.");
		}

		entityManager = entityManagerFactory.createEntityManager();
		System.out.println("caching em");

		return entityManager;
	}

}
