package DAO;

import Models.User;

import java.util.List;

public interface IDAOUser {
    User getUser(String username);
    List<User> getAllUsers();
    void addUser(User user);
    void removeUser(User user);
    void editUser(User user);
    boolean addFollower(String user,String follower);
    boolean removeFollower(String user,String follower);
}
