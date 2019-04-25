package rest;

import Models.User;
import dto.UserDTO;
import dto.UserMapper;
import service.UserService;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Stateless
@Path("/users")
public class UserApi {

    @Inject
    UserService userService;

    @Inject
    UserMapper modelMapper;
    @GET
    @Path("all")
    @Produces(APPLICATION_JSON)
    public Collection<UserDTO> findAllUsers(@Context HttpServletResponse response) {
        List<User> users = userService.getAllUsers();
        List<UserDTO> userDTOS = new ArrayList<>();
        for(User u : users) {
            UserDTO userDTO = modelMapper.mapUserToDTO(u);
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }


    @GET
    @Path("/{username}")
    @Produces(APPLICATION_JSON)
    public UserDTO findUser(@PathParam("username") String username){
        User u =userService.getUser(username);
        UserDTO userDTO = modelMapper.mapUserToDTO(u);
        return userDTO;
    }

    @GET
    @Path("/following/{username}")
    @Produces(APPLICATION_JSON)
    public List<User> getFollowings(@PathParam("username") String username){
        return userService.getUser(username).getFollowing();
    }
    @GET
    @Path("/followers/{username}")
    @Produces(APPLICATION_JSON)
    public List<User> getFollowers(@PathParam("username") String username){
        return userService.getUser(username).getFollowers();
    }


    @POST
    @Path("remove/{username}")
    @RolesAllowed("Admin")
    public String removeUser(@PathParam("username") String userName) {
        User user = userService.getUser(userName);
        userService.removeUser(user);
        return "User has been removed.";
    }

    @POST
    @Path("create/{username}")
    public String createUser(@PathParam("username") String userName) {

        User user = new User(userName);
        userService.addUser(user);
        User addedUser = userService.getUser(userName);
        if (addedUser != null) {
            return user.getUsername() + " successfully added.";
        }
        return "User could not be added.";
    }
    @PUT
    @Path("update/{username}")
    @Consumes({APPLICATION_JSON})
    @Produces({APPLICATION_JSON})
    public Response updateUser(User user) {
        userService.editUser(user);
        return Response.ok(user, MediaType.APPLICATION_JSON).build();
    }
    @PUT
    @Path("follow/{follower}/{yourUsername}")
    @Produces({APPLICATION_JSON})
    public Response updateUser(@PathParam("follower")String follower,@PathParam("yourUsername")String yourUsername) {
        boolean succes = false;
        if(follower!=null&&yourUsername!=null){
          succes =  userService.addFollower(yourUsername,follower);
            return Response.ok(succes, APPLICATION_JSON).build();
        }
        else return Response.status(Response.Status.BAD_REQUEST).entity("follower not added").build();

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
        else return Response.status(Response.Status.BAD_REQUEST).entity("follower not added").build();

    }


}
