package com.akadatsky.api;

import com.akadatsky.dao.UserDao;
import com.akadatsky.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/")
public class UserApi {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getAll() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<User> users = UserDao.getInstance().getAll();
        String json = gson.toJson(users);
        return Response
                .status(Response.Status.OK)
                .entity(json)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/add")
    public Response addUser(@FormParam("firstName") String firstName, @FormParam("lastName") String lastName, @FormParam("age") int age) {
        UserDao.getInstance().addUser(new User(firstName, lastName, age));
        return Response
                .status(Response.Status.OK)
                .entity("User added")
                .build();
    }

}
