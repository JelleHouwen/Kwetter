import Models.Kweet;
import Models.User;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserTest {
    private static List<User> users;
    private static List<User> following;

    @Before
    public void setUp() throws Exception {
        users = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            users.add(new User("user" + i));
        }

        following = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            following.add(new User("following" + i));
        }

        for(User u : this.users){
            for(User f : this.following) {
                u.addFollower(f);
            }
        }
    }

    @Test
    public void followingTest(){
        assertEquals(this.users.get(1).getFollowers().size(),10);
        assertEquals(this.users.get(9).getFollowers().size(),10);

        //add existing follower
        this.users.get(1).addFollower(this.following.get(1));
        assertEquals(this.users.get(1).getFollowers().size(),10);

        this.users.get(1).removeFollower(this.following.get(1));
        assertEquals(this.users.get(1).getFollowers().size(),9);

    }
    @Test
    public void addKweet() throws Exception {
        int i;

        for (i = 0; i < 10; i++) {
            Kweet testKweet = new Kweet("Test Kweet " + i);
            users.get(1).addKweet(testKweet);
        }
        assertEquals(10, users.get(1).getKweets().size());

        Kweet nullkweet = null;
        users.get(1).addKweet(nullkweet);

        assertEquals(10, users.get(1).getKweets().size());
    }
}