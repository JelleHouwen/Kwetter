package security;

import Models.Role;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.Method;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Provider
@JWTTokenNeeded
@Priority(Priorities.AUTHENTICATION)
public class JWTTokenNeededFilter implements ContainerRequestFilter {

    @Context
    UriInfo uriInfo;

    @Context
    ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        // Get the HTTP Authorization header from the request
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        // Extract the token from the HTTP Authorization header
        String token = authorizationHeader.substring("Bearer".length()).trim();


        try {
            // Validate the token
            DecodedJWT decodedJWT = JWT.decode(token);
            requestContext.setSecurityContext(new SecurityContext() {
                @Override
                public Principal getUserPrincipal() {
                    return decodedJWT::getSignature;
                }
                @Override
                public boolean isUserInRole(String role) {
                    List<Role> roles = decodedJWT.getHeaderClaim("roles").asList(Role.class);
                    return roles.stream().anyMatch(a -> a.getRoleName().equals(role));
                }
                @Override
                public boolean isSecure() {
                    return uriInfo.getAbsolutePath().toString().startsWith("https");
                }
                @Override
                public String getAuthenticationScheme() {
                    return "Token-Based-Auth-Scheme";
                }
            });

            Method resourceMethod = resourceInfo.getResourceMethod();
            JWTTokenNeeded annotation = resourceMethod.getAnnotation(JWTTokenNeeded.class);

            boolean accessAllowed = Arrays.stream(annotation.allowedRoles()).anyMatch(a -> requestContext.getSecurityContext().isUserInRole(a) || a.equals("ALL"));
            if(!accessAllowed){
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            }
        } catch (Exception e) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}
