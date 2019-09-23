package com.vpsmith.addressbook.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
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

	@Context
	private HttpServletRequest httpServletRequest;

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to the
	 * client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getContacts() {

		ContactDAO dao = getDAO();
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

		ContactDAO dao = getDAO();
		Contact contact = dao.findById(id);
		if (contact == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		GenericEntity<Contact> myEntity = new GenericEntity<Contact>(contact) {
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
	@Path("/user/{user}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getContactByUser(@PathParam("user") String userName) {

		ContactDAO dao = getDAO();
		List<Contact> contacts = dao.findByUser(userName);

		if ((contacts == null) || (contacts.isEmpty())) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		GenericEntity<List<Contact>> myEntity = new GenericEntity<List<Contact>>(contacts) {
		};

		return Response.status(200).entity(myEntity).build();
	}

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to the
	 * client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save(Contact contact) {

		ContactDAO dao = getDAO();
		Contact aContact = dao.save(contact);
		System.out.println("the contact: " + aContact);

		if (aContact == null) {
			return Response.status(Response.Status.EXPECTATION_FAILED).build();
		}
		GenericEntity<Contact> myEntity = new GenericEntity<Contact>(aContact) {
		};

		return Response.status(Response.Status.CREATED).entity(myEntity).build();
	}

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to the
	 * client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(Contact contact) {

		ContactDAO dao = getDAO();
		dao.update(contact);
		System.out.println("the contact: " + contact);

		return Response.status(Response.Status.OK).build();
	}

	private ContactDAO getDAO() {
		return new ContactDAO((EntityManager) httpServletRequest.getAttribute("entity"));
	}

}
