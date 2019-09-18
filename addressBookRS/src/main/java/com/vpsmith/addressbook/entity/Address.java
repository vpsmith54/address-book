package com.vpsmith.addressbook.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 * Entity implementation class for Entity: Address
 *
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "Address.findAll", query = "SELECT c FROM Address c"),
	@NamedQuery(name = "Address.findById", query = "SELECT c FROM Address  c WHERE c.id = :id"),
	@NamedQuery(name = "Address.findByContact", query = "SELECT c FROM Address c WHERE c.contact = :contact")
})
public class Address implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String line1;
	private String line2;
	private String city;
	private String state;
	private String postalCode;
	private static final long serialVersionUID = 1L;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ADDRESSTYPE")
	private AddressType addressType;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CONTACT")
	private Contact contact;

	public Address() {
		super();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", line1=" + line1 + ", line2=" + line2 + ", city=" + city + ", state=" + state
				+ ", postalCode=" + postalCode + ", addressType=" + addressType + ", contact=" + contact + "]";
	}

	public AddressType getAddressType() {
		return addressType;
	}

	public void setAddressType(AddressType addressType) {
		this.addressType = addressType;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

}
