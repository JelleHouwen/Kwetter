package rest.resources;

import Models.User;
import dto.UserDTO;
import service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Stateless
@Path("/users")
public class UserResource {

    @Inject
    UserService userService;
    @Context
    UriInfo uriInfo;


    @GET
    @Path("/{username}")
    @Produces(APPLICATION_JSON)
    public Response findUser(@PathParam("username") String username){
        User u =userService.getUser(username);
        UserDTO userDTO = new UserDTO (u);
        Link kweets = Link.fromUri(uriInfo.getBaseUriBuilder()
                .path(KweetResource.class)
                .path("user")
                .path(u.getUsername()).build()).build();

        Link followers = Link.fromUri(uriInfo.getBaseUriBuilder()
                .path(UserResource.class)
                .path("getfollowers")
                .path(u.getUsername()).build()).build();

        Link following = Link.fromUri(uriInfo.getBaseUriBuilder()
                .path(UserResource.class)
                .path("getfollowing")
                .path(u.getUsername()).build()).build();
        try {
            return Response.ok(userDTO).links(kweets).links(followers).links(following).build();
        }catch(Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("user not found ").build();
        }
    }

    @PUT
    @Path("update/{username}")
    @Consumes({APPLICATION_JSON})
    @Produces({APPLICATION_JSON})
    public Response updateUser(User user) {
        try {
        userService.editUser(user);
        return Response.ok(user, MediaType.APPLICATION_JSON).build();}
        catch(Exception e){
            return Response.status(Response.Status.BAD_REQUEST).entity("User not edited").build();
        }
    }

    @PUT
    @Path("follow/{follower}/{yourUsername}")
    @Produces({APPLICATION_JSON})
    public Response followUser(@PathParam("follower")String follower,@PathParam("yourUsername")String yourUsername) {
        boolean succes = false;
        if(follower!=null&&yourUsername!=null){
          succes =  userService.addFollower(yourUsername,follower);
            return Response.ok(succes, APPLICATION_JSON).build();
        }
        else return Response.status(Response.Status.BAD_REQUEST).entity("failed to add "+ follower).build();
    }

    @PUT
    @Path("unfollow/{follower}/{yourUsername}")
    @Produces({APPLICATION_JSON})
    public Response removeFollower(@PathParam("follower")String follower,@PathParam("yourUsername")String yourUsername) {
        boolean succes = false;
        if(follower!=null&&yourUsername!=null){
            succes =  userService.removeFollower(yourUsername,follower);
            return Response.ok(succes, APPLICATION_JSON).build();
        }
        else return Response.status(Response.Status.BAD_REQUEST).entity("failed to unfollow "+follower).build();

    }

    @GET
    @Path("getfollowers/{username}")
    @Produces({APPLICATION_JSON})
    public Response getFollowers(@PathParam("username")String user) {
    List<User> followers = userService.getFollowers(user);
            if(followers.size()>0){
                return Response.ok(followers, APPLICATION_JSON).build();
            } else return Response.ok(new ArrayList<User>()).build();

    }
    @GET
    @Path("getfollowing/{username}")
    @Produces({APPLICATION_JSON})
    public Response getFollowing(@PathParam("username")String user) {
        List<User> following = userService.getFollowing(user);
        if(following.size()>0){
            return Response.ok(following, APPLICATION_JSON).build();
        } else return Response.ok(new ArrayList<User>()).build();

    }
    @GET
    @Path("all")
    @Produces(APPLICATION_JSON)
    public Collection<UserDTO> findAllUsers(@Context HttpServletResponse response) {

        List<UserDTO> users = userService.getAllUsers().stream().map(m -> new UserDTO(m)).collect(Collectors.toList());
        return users;
    }

}
