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

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {

		ServletRequestListener.super.requestDestroyed(sre);
		if ((sre.getServletRequest() != null) && (sre.getServletRequest().getAttribute("entity") != null)) {
			EntityManager em = (EntityManager) sre.getServletRequest().getAttribute("entity");
			sre.getServletRequest().removeAttribute("entity");
			em.close();
			System.out.println("Destroyed em");
		}
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		// TODO Auto-generated method stub
		ServletRequestListener.super.requestInitialized(sre);
		sre.getServletRequest().setAttribute("entity", entityManagerFactory.createEntityManager());
		System.out.println("Created em");
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		entityManagerFactory = Persistence.createEntityManagerFactory("ADDRESSBOOK_JPA");
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		entityManagerFactory.close();
	}

}
