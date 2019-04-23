package security;

import Models.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;


import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class JWTTokenGenerator {
    public static String generateToken(User user) throws UnsupportedEncodingException {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        Map<String, Object> headerClaims = new HashMap<>();
        headerClaims.put("owner", "auth0");
        headerClaims.put("roles", user.getRoles());
        return JWT.create()
                .withIssuer("auth0")
                .withHeader(headerClaims)
                .sign(algorithm);
    }
}
