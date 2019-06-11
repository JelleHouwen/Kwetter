import Models.Role;
import Models.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {

    @Before
    public void setup(){
    }
    @Test
    public void shouldAddARole(){
        // Arrange
        User user = new User("followee");
        Role admin = new Role("Admin");

        // Act
        user.addRole(admin);

        // Assert
        assertEquals(1,user.getRoles().size());
    }
    @Test
    public void shouldAddAndRemoveARole(){
        // Arrange
        User user = new User("followee");
        Role admin = new Role("Admin");
        user.addRole(admin);

        // Act
        user.removeRole(admin);

        // Assert
        assertEquals(0,user.getRoles().size());
    }

    @Test
    public void shouldAddAndRemoveAFollower(){
        // Arrange
        User followee = new User("followee");
        User follower =  new User("followee");
        followee.addFollower(follower);

        // Act
        followee.removeFollower(follower);

        // Assert
        assertEquals(0,followee.getFollowers().size());
    }

}