package com.vpsmith.addressbook.entity.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.vpsmith.addressbook.entity.AddressType;

class CreateAddressType {

	public static void main(String[] args) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ADDRESS_JPA");

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		AddressType addressType = new AddressType();
//		addressType.setId(id);
		addressType.setName("Work");
		addressType.setDescription("Work Address");
		entitymanager.persist(addressType);
		
		AddressType addressType1 = new AddressType();
//		addressType.setId(id);
		addressType1.setName("Home");
		addressType1.setDescription("Home Address");

		entitymanager.persist(addressType1);
		entitymanager.getTransaction().commit();

		entitymanager.close();
		emfactory.close();

	}
}
