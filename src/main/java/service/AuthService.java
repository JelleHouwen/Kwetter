package service;


import Models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;

@Stateless
public class AuthService {

    @Inject
    UserService userService;


    public String login(String username, String password) throws Exception {

        if (userService.validateUser(username, password)) {
            User u = userService.getUser(username);
            return genToken(u);
        } else {
            throw new Exception("Login error");
        }

    }


    public String genToken(User user) throws UnsupportedEncodingException {
        Calendar now = Calendar.getInstance();
        Calendar expired = Calendar.getInstance();

        expired.add(Calendar.HOUR, 1);

        return Jwts.builder()
                .setSubject(String.valueOf(user.getID()))
                .setId("15a96c27-f703-4f1b-adbd-e4c1b007cb83")
                .setIssuedAt( now.getTime())
                .setExpiration( expired.getTime())
                .claim("name", user.getUserName())
                .claim("admin", false)
                .claim("userid", user.getID())
                .signWith(SignatureAlgorithm.HS512, "kwetter".getBytes("UTF-8"))
                .compact();
    }
}
