package com.vpsmith.addressbook.entity.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.vpsmith.addressbook.entity.Contact;
import com.vpsmith.addressbook.service.EntityManagerFactoryHelper;

public class ContactDAO {

	public List<Contact> findAll() {

		EntityManager entitymanager = EntityManagerFactoryHelper.createEntityManager();
		entitymanager.getTransaction().begin();

		TypedQuery<Contact> query = entitymanager.createNamedQuery("Contact.findByUsername", Contact.class);
		query.setParameter("username", "user");
		List<Contact> contacts = query.getResultList();

		return contacts;
	}

	public Contact findById(int id) {
		return null;
	}

	public Contact save(Contact contact) {
		return null;
	}

	public void remove(Contact contact) {
	}

}
