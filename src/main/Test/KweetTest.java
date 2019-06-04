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

    @Test
    public void addKweet(){
        Kweet k = new Kweet();

    }
}
