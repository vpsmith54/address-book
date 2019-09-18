package com.vpsmith.addressbook.entity.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.vpsmith.addressbook.entity.Contact;

public class CreateAddress {

	public static void main(String[] args) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ADDRESSBOOK_JPA");

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		TypedQuery<Contact> query = entitymanager.createNamedQuery("Contact.findAll", Contact.class);
		List<Contact> contacts = query.getResultList();
		for (Contact contact : contacts) {
			System.out.println(contact);
		}

		entitymanager.getTransaction().commit();

		entitymanager.close();
		emfactory.close();

	}
}
