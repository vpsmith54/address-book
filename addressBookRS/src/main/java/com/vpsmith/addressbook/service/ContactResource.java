package com.vpsmith.addressbook.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.vpsmith.addressbook.entity.Contact;
import com.vpsmith.addressbook.entity.dao.ContactDAO;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("contacts")
public class ContactResource {

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to the
	 * client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getContacts() {

		ContactDAO dao = new ContactDAO();
		List<Contact> contacts = dao.findAll();

		if ((contacts == null) || (contacts.isEmpty())) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		GenericEntity<List<Contact>> myEntity = new GenericEntity<List<Contact>>(contacts) {
		};

		return Response.status(Response.Status.FOUND).entity(myEntity).build();
	}

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to the
	 * client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getContactById(@PathParam("id") Integer id) {
		
		System.out.println("got here");

		ContactDAO dao = new ContactDAO();
		Contact contact = dao.findById(id);
		if (contact == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		GenericEntity<Contact> myEntity = new GenericEntity<Contact>(contact) {
		};

		return Response.status(Response.Status.FOUND).entity(myEntity).build();
	}

//	/**
//	 * Method handling HTTP GET requests. The returned object will be sent to the
//	 * client as "text/plain" media type.
//	 *
//	 * @return String that will be returned as a text/plain response.
//	 */
//	@GET
//	@Path("/{user}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getContactByUser(@PathParam("user") String userName) {
//
//		ContactDAO dao = new ContactDAO();
//		Contact contact = dao.findByUser(userName);
//		GenericEntity<Contact> myEntity = new GenericEntity<Contact>(contact) {
//		};
//
//		return Response.status(200).entity(myEntity).build();
//	}

}
