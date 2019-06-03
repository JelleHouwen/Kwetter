package DAO;

import Models.User;

import java.util.List;

public interface IDAOUser {
    User getUser(String username);
    List<User> getAllUsers();
    void addUser(User user);
    void removeUser(User user);
    void editUser(User user);
    boolean addFollower(User user,User follower);
    boolean removeFollower(User user,User follower);
    List<User>getFollowing(String username);
    List<User>getFollowers(String username);
}
