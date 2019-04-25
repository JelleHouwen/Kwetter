import DAO.IDAOUser;
import Models.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import service.UserService;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    @InjectMocks
    UserService userService;
    @Mock
    private IDAOUser userDAO;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void shouldAddUserService() throws Exception{
        // Arrange
        User user1 = mock(User.class);

        // Act
        userService.addUser(user1);

        // Assert
        verify(userDAO, Mockito.times(1)).addUser(user1);
    }



    @Test
    public void shouldEditUserService() throws Exception{
        // Arrange
        User user1 = mock(User.class);
        userService.addUser(user1);
        user1.setBio("new Bio");

        // Act
        userService.editUser(user1);

        // Assert
        Mockito.verify(userDAO, Mockito.times(1)).editUser(user1);
    }


    @Test
    public void shouldAddAFollowerService(){
        // Arrange
        User user1 = mock(User.class);
        userService.addUser(user1);
        User user2 = mock(User.class);
        userService.addUser(user2);

        // Act
        userService.addFollower(user1.getUsername(), user2.getUsername());
        userService.editUser(user1);

        // Assert
        Mockito.verify(userDAO, Mockito.times(1)).editUser(user1);

    }
}
