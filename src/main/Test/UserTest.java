import DAO.DAOUserImpl;
import DAO.DAOUserMysql;
import DAO.IDAOUser;
import Models.Role;
import Models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import service.UserService;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UserTest {

    @Before
    public void setup(){
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