package com.appointmentmgmtsyst.api;

import java.util.Optional;

import javax.websocket.server.PathParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.simple.JSONObject;

import com.appointmentmgmtsyst.model.User;
import com.appointmentmgmtsyst.services.UserServiceImpl;

@SuppressWarnings("unchecked")
@Path("user")
public class UserResources {
	
	UserServiceImpl userService = new UserServiceImpl();
	
	@POST
	@Produces({ MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_JSON })
	@Path("register")
	public Response registerUser(User user) throws Exception {
		if(!userService.getUserByEmail(user.getEmail()).equals(Optional.empty())) {
			JSONObject msg = new JSONObject();
			msg.put("error", "Email already exists");
			return Response.status(Status.BAD_REQUEST).entity(msg).build();
		}
		else {
			JSONObject msg = new JSONObject();
			int id = userService.addUser(user);
			msg.put("userid", id);
			msg.put("msg","user created successfully");
		return Response.status(Status.CREATED)
		.entity(msg)
		.build();
		}
	}
	
	
	@POST
	@Produces({ MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_JSON })
	@Path("login")
	public Response loginUser(User user) {
		Optional<User> u = userService.getUserByEmail(user.getEmail());
		if(u.equals(Optional.empty())) {
			JSONObject msg = new JSONObject();
			msg.put("error", "email does not exists");
			return Response.status(Status.BAD_REQUEST).entity(msg).build();
		}
		else if(!userService.loginUser(user)) {
			JSONObject msg = new JSONObject();
			msg.put("error", "email and password do not match");
			return Response.status(Status.BAD_REQUEST).entity(msg).build();
		}
		else {
			JSONObject msg = new JSONObject();
			msg.put("user", u.get().getName());
			return Response.status(Status.OK).entity(msg).build();
		}
	}
	
	@PUT
	@Produces({ MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_JSON })
	@Path("update")
	public Response updateUser(User user) {
		Optional<User> u = userService.getUserById(user.getUserId());
		if(u.equals(Optional.empty())) {
			JSONObject msg = new JSONObject();
			msg.put("error", "user doesn't exists");
			return Response.status(Status.BAD_REQUEST).entity(msg).build();
		}
		else{
			JSONObject msg = new JSONObject();
			if(userService.updateUser(user)) {
				msg.put("success", "updated user successfully");
				return Response.status(Status.ACCEPTED).entity(msg).build();
			}
			else {
				msg.put("error", "some issue while updating");
				return Response.status(Status.BAD_REQUEST).entity(msg).build();
			}
		}
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_JSON })
	@Path("get/{id}")
	public Response getUser(@PathParam("id") int id) {
		Optional<User> u = userService.getUserById(id);
		if(u.equals(Optional.empty())) {
			JSONObject msg = new JSONObject();
			msg.put("error", "user doesn't exists");
			return Response.status(Status.BAD_REQUEST).entity(msg).build();
		}
		else{
			return Response.status(Status.OK).entity(u.orElse(null)).build();
		}
	}

	@DELETE
	@Produces({ MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_JSON })
	@Path("update/{id}")
	public Response deleteUser(@PathParam("id") int id) {
		Optional<User> user = userService.getUserById(id);
		if(user.equals(Optional.empty())) {
			JSONObject msg = new JSONObject();
			msg.put("error", "user doesn't exists");
			return Response.status(Status.BAD_REQUEST).entity(msg).build();
		}
		else{
			JSONObject msg = new JSONObject();
			if(userService.deleteUser(user.get())) {
				msg.put("success", "updated user successfully");
				return Response.status(Status.ACCEPTED).entity(msg).build();
			}
			else {
				msg.put("error", "some issue while updating");
				return Response.status(Status.BAD_REQUEST).entity(msg).build();
			}
		}
	}
	
}
