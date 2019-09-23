package com.vpsmith.addressbook.entity.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import com.vpsmith.addressbook.entity.Contact;

public class ContactDAO {

	private EntityManager entitymanager;

	public ContactDAO(EntityManager entitymanager) {
		super();
		this.entitymanager = entitymanager;
	}

	public List<Contact> findByUser(String username) {

		TypedQuery<Contact> query = entitymanager.createNamedQuery("Contact.findByUsername", Contact.class);
		query.setParameter("username", username);
		List<Contact> contacts = query.getResultList();

		return contacts;
	}

	public Contact findById(Integer id) {

		TypedQuery<Contact> query = entitymanager.createNamedQuery("Contact.findById", Contact.class);
		query.setParameter("id", id);

		Contact contact;
		try {
			contact = query.getSingleResult();
		} catch (javax.persistence.NoResultException e) {
			return null;
		}

		return contact;
	}

	public List<Contact> findAll() {

		TypedQuery<Contact> query = entitymanager.createNamedQuery("Contact.findAll", Contact.class);
		List<Contact> contacts = query.getResultList();

		return contacts;
	}

	public Contact save(Contact contact) {

		entitymanager.getTransaction().begin();

		entitymanager.persist(contact);

		entitymanager.getTransaction().commit();

		return contact;
	}

	public void update(Contact contact) {

		entitymanager.getTransaction().begin();

		Contact aContact = findById(contact.getId());

		aContact.setEmail(contact.getEmail());
		aContact.setFirstName(contact.getFirstName());
		aContact.setLastName(contact.getLastName());
		aContact.setPassword(contact.getPassword());
		aContact.setPhone(contact.getPhone());

		entitymanager.merge(aContact);

		entitymanager.getTransaction().commit();
	}

	public void remove(Integer Id) {

		entitymanager.getTransaction().begin();
		CriteriaBuilder cb = entitymanager.getCriteriaBuilder();

		// create delete
		CriteriaDelete<Order> delete = cb.createCriteriaDelete(Order.class);

		// set the root class
		Root e = delete.from(Order.class);

		// set where clause
		delete.where(cb.equal(e.get("id"), Id));

		// perform update
		entitymanager.createQuery(delete).executeUpdate();

		entitymanager.getTransaction().commit();

	}

}
