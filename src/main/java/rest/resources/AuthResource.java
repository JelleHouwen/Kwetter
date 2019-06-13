package rest.resources;



import Models.User;
import dto.UserDTO;
import security.JWTTokenGenerator;
import service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/auth")
@Stateless
public class AuthResource {

    @Inject
    UserService userService;

    @POST
    @Path("/login/{userName}&{password}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response login(@PathParam("userName") String userName, @PathParam("password") String password) {
        try {
            boolean found = userService.validateUser(userName,password);
            if (found) {
                User user = userService.getUser(userName);
                String token = JWTTokenGenerator.generateToken(user);
                UserDTO userDTO = new UserDTO(user);

                return Response.ok(userDTO).header(HttpHeaders.AUTHORIZATION, "Bearer " + token).header("Access-Control-Expose-Headers", "Authorization").build();
            }
            return Response.status(Response.Status.FORBIDDEN).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
