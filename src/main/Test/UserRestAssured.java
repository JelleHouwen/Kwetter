import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import Models.Role;
import Models.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import service.UserService;

import javax.inject.Inject;

import static io.restassured.RestAssured.with;
import static org.mockito.Mockito.mock;

public class UserRestAssured {

    @Inject
    UserService userService;
    static String url ="http://localhost:8080/Kwetter_versie_5001_war_exploded/api/users/";
    @Test
    public void shouldGetUser() throws Exception{
        get(url+"admin").then().statusCode(200).assertThat()
                .body("username", equalTo("admin"));
    }
    @Test
    public void whenRequestedPost_thenCreated() {
        //arrange
        User test = userService.getUser("admin");

        //act
        test.setBio("new bio");

        //assert
        with().body(test)
                .when()
                .request("PUT", url+"update "+test.getUsername())
                .then()
                .statusCode(201).assertThat()
                .body("bio", equalTo("new bio"));
    }



}
