import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserRestServiceTest {

    @Before
    public void setUp()throws IOException {
        HttpPost post = new HttpPost("http://localhost:8080/Kwetter_versie_5001_war_exploded/api/users/remove");
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
        nameValuePairs.add(new BasicNameValuePair("userName", "Steffie"));
        post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(post);
    }

    @Test
    public void testAddAndGetUser() throws IOException {
        // verify that the user does not exist yet
        HttpUriRequest request = new HttpGet("http://localhost:8080/Kwetter_versie_5001_war_exploded/api/users/Steffie");
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(204, httpResponse.getStatusLine().getStatusCode());

        // add user with succes
        HttpPost post = new HttpPost("http://localhost:8080/Kwetter_versie_5001_war_exploded/api/users/create");
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
        nameValuePairs.add(new BasicNameValuePair("userName", "Steffie"));
        post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        httpResponse = HttpClientBuilder.create().build().execute(post);
        assertEquals(200,httpResponse.getStatusLine().getStatusCode());

        // verify user is indeed added
        request = new HttpGet("http://localhost:8080/JEAKwetter_war_exploded/api/users/Steffie");
        httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(200,httpResponse.getStatusLine().getStatusCode());
    }

}
