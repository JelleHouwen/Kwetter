import DAO.DAOKweetImpl;
import DAO.DAOUserImpl;
import Models.Kweet;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import service.KweetService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class KweetServiceTest {

    @InjectMocks
    KweetService kweetService;
    @Mock
    private DAOKweetImpl kweetDAO;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void shouldAddKweetService() throws Exception{
        // Arrange
        Kweet kweet = mock(Kweet.class);

        // Act
        kweetService.addKweet(kweet);

        // Assert
        verify(kweetDAO, Mockito.times(1)).addKweet(kweet);
    }



    @Test
    public void shouldEditKweetService() throws Exception{
        // Arrange
        Kweet kweet = mock(Kweet.class);

        kweetService.addKweet(kweet);
        kweet.setText("hi");
        // Act
        kweetService.editKweet(kweet);

        // Assert
        Mockito.verify(kweetDAO, Mockito.times(1)).editKweet(kweet);
    }
}
