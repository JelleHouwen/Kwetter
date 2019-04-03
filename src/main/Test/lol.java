import DAO.DAOKweetImpl;
import DAO.DAOUserImpl;
import Models.Kweet;
import Models.User;
import org.junit.Before;
import org.junit.Test;
import service.KweetService;

public class lol {
    DAOKweetImpl kweetDAO = new DAOKweetImpl();
    DAOUserImpl userDAO = new DAOUserImpl();

    @Test
    public void testSomething(){
        User u = new User("stef");

        userDAO.addUser(u);
        Kweet k = new Kweet("hoi",u);
        u.addKweet(k);
     User newUser =   userDAO.getUser(u.getUserName());
     newUser.addKweet(k);
     userDAO.editUser(newUser);
     kweetDAO.addKweet(k);

     System.out.println(userDAO.getAllUsers());
    }
}
