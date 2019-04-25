import DAO.DAOUserImpl;
import DAO.DAOUserMysql;
import Models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import service.UserService;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UserTest {
    @InjectMocks
    DAOUserImpl daoUserColl;
    @InjectMocks
    DAOUserMysql daoUserMysql;
    @InjectMocks
    UserService userService;

    DatabaseCleaner databaseCleaner;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

    }
    @After
    public void tearDown() throws SQLException {
            databaseCleaner = new DatabaseCleaner();
            databaseCleaner.clean();
    }
    @Test
    public void shouldPersistUserToDatabase(){
        //arrange
        daoUserMysql.addUser(new User("Steef"));

        //act
        User u =daoUserMysql.getUser("Steef");

        //assert
        assertEquals("Steef",u.getUsername());
    }

    @Test
    public void ShouldRemoveUserFromDB(){
        //arrange
        User u = new User("Steef1");
        daoUserMysql.addUser(u);


        //act
        daoUserMysql.removeUser(u);
        u=daoUserMysql.getUser("Steef");

        //assert
        assertNull(u);
    }
    @Test
    public void shouldAddAFollower(){
        // Arrange
        User follower = new User("follower");
        User followee = new User("followee");
        daoUserColl.addUser(follower);
        daoUserColl.addUser(followee);

        // Act
        followee.addFollower(follower);

        // Assert
       assertEquals(1,followee.getFollowers().size());
    }

    @Test
    public void shouldNotAddAFollowerTwice(){
        // Arrange
        User follower = new User("follower");
        User followee = new User("followee");
        daoUserColl.addUser(follower);
        daoUserColl.addUser(followee);
        followee.addFollower(follower);

        // Act
        followee.addFollower(follower);

        // Assert
        assertEquals(1,followee.getFollowers().size());
    }

    @Test
    public void shouldNotAddYourselfAsFollower(){
        // Arrange
        User self = new User("follower");
        daoUserColl.addUser(self);

        // Act
        self.addFollower(self);

        // Assert
        assertEquals(0,self.getFollowers().size());
    }
    @Test
    public void shouldRemoveAFollower(){
        // Arrange
        User followee = new User("followee");
        User follower =  new User("followee");
        followee.addFollower(follower);

        // Act
        followee.removeFollower(follower);

        // Assert
        assertEquals(0,followee.getFollowers().size());
    }


    @Test
    public void shouldGetAllUsers(){
        //arrange
        daoUserMysql.addUser(new User("Steef"));
        daoUserMysql.addUser(new User("Steef1"));
        daoUserMysql.addUser(new User("Steef2"));
        //act
        List<User> users =daoUserMysql.getAllUsers();

        //assert
        assertEquals(3,users.size());
    }




}