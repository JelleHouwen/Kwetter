package rest;

import Models.Kweet;
import Models.User;
import dto.KweetDTO;
import security.JWTTokenNeeded;
import service.KweetService;
import service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;
import java.util.stream.Collectors;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;


@Stateless
@Path("/kweets")
public class KweetApi {

    @Inject
    KweetService kweetService;
    @Inject
    UserService userService;
    @Inject
    KweetWs ws;
    @Context
    UriInfo uriInfo;


    @GET
    @Path("{ID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getKweetById(@PathParam("ID") int id) {
        KweetDTO kweet =new KweetDTO(kweetService.getKweetByID(id));
        try{
            Link user = Link.fromUri(uriInfo.getBaseUriBuilder()
                    .path(UserApi.class)
                    .path("user")
                    .path(kweet.getPlacer().getUsername()).build()).build();

            return Response.ok(kweet).links(user).build();
        }
        catch(Exception e) {
          return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("user/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response  getLast10User(@PathParam("username") String username) {
        List<KweetDTO> kweets =kweetService.getAllKweetsFromUser(username).stream().map(m -> new KweetDTO(m)).collect(Collectors.toList());
        try{
            return Response.ok(kweets).build();

        }
        catch(Exception e) {
           return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("search/{search}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchKweets(@PathParam("search") String search) {
        List<KweetDTO> kweets =kweetService.searchKweets(search).stream().map(m -> new KweetDTO(m)).collect(Collectors.toList());
        try{
            return Response.ok(kweets).build();

        }
        catch(Exception e) {
           return Response.status(Response.Status.BAD_REQUEST).build();
        }
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
    public Response getKweetsByUserName(@PathParam("username") String username) {
        List<KweetDTO> kweetList = kweetService.getAllKweetsFromUser(username).stream().map(m -> new KweetDTO((Kweet) m)).collect(Collectors.toList());;
        try{
            return Response.ok(kweetList).build();
        }
        catch(Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
    @GET
    @Path("getAllFollowing/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getKweetsFollowing(@PathParam("id") int id) {
        List<KweetDTO> kweetList = kweetService.getAllKweetsFollowing(id).stream().map(m -> new KweetDTO((Kweet) m)).collect(Collectors.toList());
        try{
            return Response.ok(kweetList).build();

        }
        catch(Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

    }

    @POST
    @Path("/add/{userName}&{text}")
    @Produces(APPLICATION_JSON)
    public Response insertKweet(@PathParam("userName") String userName, @PathParam("text") String text,@Context HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin" , "*");
        User u =userService.getUser(userName);
        Kweet kweet = new Kweet(text,u);
        if(u!=null && kweet !=null){

            kweetService.addKweet(kweet);
            ws.sendMessages();
            return Response.ok(kweet).build();}

        return Response.status(Response.Status.BAD_REQUEST).build();
    }
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllKweets(@Context HttpServletResponse response) {
        List<KweetDTO> kweets =kweetService.getAllKweets().stream().map(m -> new KweetDTO(m)).collect(Collectors.toList());
        try{
            return Response.ok(kweets).build();

        }
        catch(Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}