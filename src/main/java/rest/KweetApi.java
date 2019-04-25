package rest;

import Models.Kweet;
import Models.User;
import service.KweetService;
import service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;


@Stateless
@Path("/kweets")
public class KweetApi {

    @Inject
    KweetService kweetService;
    @Inject
    UserService userService;

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
        public List<Kweet> getAllKweets(@Context HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin" , "*");
        return kweetService.getAllKweets();
    }

    @GET
    @Path("last")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Kweet> getLast20(@Context HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin" , "*");
        return kweetService.getLast20();
    }
    @GET
    @Path("{ID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Kweet getKweetById(@PathParam("ID") int id) {
        return kweetService.getKweetByID(id);
    }

    @GET
    @Path("user/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Kweet>  getLast10User(@Context HttpServletResponse response,@PathParam("username") String username) {
        response.setHeader("Access-Control-Allow-Origin" , "*");
        return kweetService.getLast10User(username);
    }


    @DELETE
    @Path("remove/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeKweet(@Context HttpServletResponse response, @PathParam("id") int id) {
        Kweet removeKweet =kweetService.getKweetByID(id);
        if(removeKweet!=null) {
             kweetService.removeKweet(removeKweet);
            return Response.ok().entity("Kweet is removed").build();
        }
        else return Response.status(Response.Status.BAD_REQUEST).entity("kweet is not removed").build();

        }

    @GET
    @Path("getAll/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Kweet> getKweetsByUserName(@PathParam("username") String username) {
        List<Kweet> kweetList = kweetService.getAllKweetsFromUser(username);
        return kweetList;
    }

    @POST
    @Path("/add/{userName}&{text}")
    @Produces(APPLICATION_JSON)
    public String insertKweet(@PathParam("userName") String userName, @PathParam("text") String text,@Context HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin" , "*");
        User u =userService.getUser(userName);
        Kweet kweet = new Kweet(text,u);
        if(u!=null && kweet !=null){
            kweetService.addKweet(kweet);
        return u.getUsername() +" has posted "+kweet.getText();}
        else return "something went wrong lol try again nerd";
    }
}