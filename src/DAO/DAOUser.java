package DAO;

import Models.User;

import java.util.List;

public class DAOUser implements IDAOUser {
    private DAOUser daoUser;
    public DAOUser(DAOUser DAOUser){
        this.daoUser=DAOUser;
    }

    @Override
    public User getUser(String username) {
        return daoUser.getUser(username);
    }

    @Override
    public List<User> getAllUsers() {
      return daoUser.getAllUsers();
    }

    @Override
    public boolean addUser(String username, String password) {
        return daoUser.addUser(username,password);
    }

    @Override
    public boolean removeUser(User user) {
        return daoUser.removeUser(user);
    }

    @Override
    public boolean editUser(User user) {
        return daoUser.editUser(user);
    }
}
