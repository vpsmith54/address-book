package com.vpsmith.addressbook.entity.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.vpsmith.addressbook.entity.Contact;

class CreateContact {

	public static void main(String[] args) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ADDRESS_JPA");

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		
		TypedQuery<Contact> query = entitymanager.createNamedQuery("Contact.findByUsername", Contact.class);
		query.setParameter("username", "user");
		List<Contact> contacts = query.getResultList();
		
		for (Contact contact : contacts) {
			contact.setLastName("lastname");
			entitymanager.persist(contact);
		}
		

//		Contact contact = new Contact("user","password","name",null,"user@gmail.com", "5032224444");
//		entitymanager.persist(contact);
//
//		Contact contact1 = new Contact("user1","password1","name1",null,"user1@gmail.com", "5032223333");
//		entitymanager.persist(contact1);
//
//		Contact contact2 = new Contact("user2","password2","name2",null,"user2@gmail.com", "5032225555");
//		entitymanager.persist(contact2);

		entitymanager.getTransaction().commit();

		entitymanager.close();
		emfactory.close();

	}
}
