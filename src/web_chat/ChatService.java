/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web_chat;

import DatabaseManager.DatabaseManagerUsers;
import com.google.gson.Gson;
import database.Users;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;

/**
 *
 * @author Dinaro Salvatore
 * @version 1.0
 */
@Path("chat")
public class ChatService {

    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        List<Users> users = new DatabaseManagerUsers().getInstance().getAllUsername();
        Gson gson = new Gson();
        String body = gson.toJson(users);
        return Response.ok(body).build();
    }

    @DELETE
    @Path("{Username}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteOne(@PathParam("Username") String Username){
        DatabaseManagerUsers.getInstance().remove(Username);
        return Response.ok().build(); 
    }

    @POST
    public Response createUser(String body) {
        
        Gson g = new Gson();

        UserCache u = g.fromJson(body, UserCache.class);

        DatabaseManagerUsers.getInstance().add(u.getUsername(), u.getPassword(), u.getNomeV());
        u.clear();
        return Response.ok().build();
    }

}
