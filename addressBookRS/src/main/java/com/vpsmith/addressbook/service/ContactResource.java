package com.vpsmith.addressbook.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.vpsmith.addressbook.entity.Contact;
import com.vpsmith.addressbook.entity.dao.ContactDAO;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("contact")
public class ContactResource {

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to the
	 * client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get() {

		ContactDAO dao = new ContactDAO();
		List<Contact> contacts = dao.findAll();
		GenericEntity<List<Contact>> myEntity = new GenericEntity<List<Contact>>(contacts) {
		};

		return Response.status(200).entity(myEntity).build();
	}

}
