import DAO.DAOKweetImpl;
import DAO.DAOUserImpl;
import DAO.DAOUserMysql;
import DAO.DaoKweetMysql;
import Models.Kweet;
import Models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import service.KweetService;
import service.UserService;

import javax.inject.Inject;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class KweetTest {

    @InjectMocks
    DAOKweetImpl daoKweet;
    @InjectMocks
    DaoKweetMysql daoKweetMysql;
    @InjectMocks
    DAOUserMysql daoUserMysql;
    @InjectMocks
    KweetService kweetService;
    @InjectMocks
    UserService userService;
    @Mock
    private DAOKweetImpl kweetDAO;
    @Mock
    private DAOUserImpl userDAo;
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
    public void shouldAddAKweet(){
        // Arrange
        User kweeter = new User("kweeter");
        Kweet kweet = new Kweet("test kweet",kweeter);

        // Act
        kweeter.addKweet(kweet);

        // Assert
        assertEquals(1,kweeter.getKweets().size());
    }

    @Test
    public void shouldPersistKweetToDatabase(){
        //arrange
        daoUserMysql.addUser(new User("Steef"));
        User poster =daoUserMysql.getUser("Steef");
        Kweet f =new Kweet("hi",poster);
        daoKweetMysql.addKweet(f);

        //act
        Kweet k = daoKweetMysql.getKweet(f.getID());

        //assert
        assertEquals(k.getID(),4);
    }

    @Test
    public void shouldEditAKweet(){
        // Arrange
        User kweeter = new User("kweeter");
        Kweet kweet = new Kweet("test kweet",kweeter);
        kweeter.addKweet(kweet);
        daoKweet.addKweet(kweet);

        // Act
        kweet.setText("new kweet");
        daoKweet.editKweet(kweet);

        // Assert
        assertEquals("new kweet",kweeter.getKweets().get(0).getText());
    }

    @Test
    public void testing(){
        User a = new User("test");
        Kweet k = new Kweet("hihi",a);
        userService.addUser(a);
        kweetService.addKweet(k);

        userService.getAllUsers();

    }
    @Test
    public void shouldGetPlacerKweet(){
        //arrange
        daoUserMysql.addUser(new User("Steef1"));
        User poster =daoUserMysql.getUser("Steef1");
        Kweet f = new Kweet("hi1",poster);
        daoKweetMysql.addKweet(f);
        Kweet k = daoKweetMysql.getKweet(f.getID());

        //act
        User kweetPlacer = k.getPlacer();

        //assert
        assertEquals(kweetPlacer,poster);
    }
}
