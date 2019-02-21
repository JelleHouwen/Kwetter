package DAO;

import Models.User;

import java.io.FileInputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.List;

public class DAOUserImpl implements IDAOUser {
    @Override
    public User getUser(String username) {

        ObjectInput in;
        User user = null;
        try {
            in = new ObjectInputStream(new FileInputStream("appSaveState.data"));
            user = (User) in.readObject();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }



    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public boolean addUser(String username, String password) {
        return false;
    }

    @Override
    public boolean removeUser(User user) {
        return false;
    }

    @Override
    public boolean editUser(User user) {
        return false;
    }

    public User makeNewUser(String username) {
        User u = new User(username);
        return u;
    }
}
