package org.kou.javabrains.messenger.resources;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.kou.javabrains.messenger.model.Message;
import org.kou.javabrains.messenger.resources.beans.MessageFilterBean;
import org.kou.javabrains.messenger.service.MessageService;

//annotations over class are default to all methods
@Path("/messages") // parent path to all methods within class
@Consumes(MediaType.APPLICATION_JSON) // type of response-file
@Produces(MediaType.APPLICATION_JSON) // type of input-file
public class MessageResource {

	MessageService messageService = new MessageService();

	@GET // HTTP method
	// QueryParam annotation allows filtering in URI like .../messages?year=2015
	// or .../messages?start=1&size=2
	//we stored all QueryParams within a Bean. below is commented out previous version
	public List<Message> getMessage(@BeanParam MessageFilterBean filterBean) {
		if (filterBean.getYear()> 0) {
			return messageService.getAllMessagesForYear(filterBean.getYear());
		}
		if (filterBean.getStart()>= 0 && filterBean.getSize()> 0) {
			return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}
		return messageService.getAllMessages();
	}
		
/*	@GET // HTTP method
	// QueryParam annotation allows filtering in URI like .../messages?year=2015
	// or .../messages?start=1&size=2
	public List<Message> getMessage(@QueryParam("year") int year, @QueryParam("start") int start,
			@QueryParam("size") int size) {
		if (year > 0) {
			return messageService.getAllMessagesForYear(year);
		}
		if (start >= 0 && size > 0) {
			return messageService.getAllMessagesPaginated(start, size);
		}
		return messageService.getAllMessages();
	}*/

	@POST
	public Message addMessage(Message message) {
		return messageService.addMessage(message);
	}

	@PUT
	@Path("/{messageId}") // curly braces allow string variables (here regex is
							// somehow allowed too)
	// PathParam value must match variable from @Path. javax is smart enough to
	// do the casting
	public Message updateMessage(@PathParam("messageId") long id, Message message) {
		message.setId(id);
		return messageService.updateMessage(message);
	}

	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long id) {
		messageService.removeMessage(id);
	}

	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long id) {
		return messageService.getMessage(id);
	}
}
