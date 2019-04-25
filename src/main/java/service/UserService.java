package service;
import DAO.DAOLogin;
import DAO.IDAORoles;
import DAO.IDAOUser;
import Models.Role;
import Models.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Stateless
public class UserService {

    @Inject
    private IDAOUser userDAO;
    @Inject
    private IDAORoles rolesDAO;
    @Inject
    private DAOLogin login;

    public UserService() {
    }

    public User getUser(String username) {
        return userDAO.getUser(username);
    }

    public void editUser(User u) {
        userDAO.editUser(u);
    }

    public void removeUser(User u) {
        userDAO.removeUser(u);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public void addUser(User u) {
        userDAO.addUser(u);
    }

    public Role getRole(String roleName) {
        return rolesDAO.getRoleByName(roleName);
    }

    public boolean validateUser(String username, String password) {
        return login.validate(username, password);
    }

    public List<Role> getAllRoles() {
        return this.rolesDAO.getAllRoles();
    }

    public boolean addFollower(String user, String follower) {
        return this.userDAO.addFollower(user, follower);
    }

    public boolean removeFollower(String user, String follower) {
        return this.userDAO.removeFollower(user, follower);
    }
}



