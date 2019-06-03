import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import Models.Kweet;
import Models.Role;
import Models.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import service.UserService;

import javax.inject.Inject;

import static io.restassured.RestAssured.with;
import static org.mockito.Mockito.mock;

public class KweetRestAssured{

    @Inject
    UserService userService;
    static String url ="http://localhost:8080/Kwetter_versie_5001_war_exploded/api/kweets/";
    @Test
    public void shouldGetKweet() throws Exception{
        get(url+"1").then().statusCode(200).assertThat()
                .body("placer.username", equalTo("admin"));
    }
    @Test
    public void shouldAddKweet() {
        //arrange
        User u = new User("testuser");
        Kweet k = new Kweet("test kweet",u);

        //act
        RequestSpecification request = RestAssured.given();
        Response response = request.post(url+"add/"+u.getUsername()+"&"+k.getText());
        int statusCode = response.getStatusCode();

        //assert
        Assert.assertEquals(statusCode, "201");
    }



}
