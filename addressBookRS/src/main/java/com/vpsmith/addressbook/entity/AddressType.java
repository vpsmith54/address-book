package com.vpsmith.addressbook.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: AddressType
 *
 */
@Entity
@Table(name = "address_type")
@NamedQueries({ 
	@NamedQuery(name = "AddressType.findAll", query = "SELECT c FROM AddressType c"),
	@NamedQuery(name = "AddressType.findById", query = "SELECT c FROM AddressType  c WHERE c.id = :id")
})
public class AddressType implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String description;
	private static final long serialVersionUID = 1L;

	public AddressType() {
		super();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "AddressType [id=" + id + ", name=" + name + ", description=" + description + "]";
	}

}
