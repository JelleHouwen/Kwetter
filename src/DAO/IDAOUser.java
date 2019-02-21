package DAO;

import Models.User;

import java.util.List;

public interface IDAOUser {
    User getUser(String username);
    List<User> getAllUsers();
    boolean addUser(String username,String password);
    boolean removeUser(User user);
    boolean editUser(User user);
}
