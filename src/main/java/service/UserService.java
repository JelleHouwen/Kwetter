package service;
import DAO.DAOLogin;
import DAO.IDAORoles;
import DAO.IDAOUser;
import Models.Role;
import Models.User;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
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

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public void addUser(User u) {
        if(!this.getAllUsers().contains(u)) {
            userDAO.addUser(u);
        }
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

    public boolean addRole(User user,String role){
        boolean succes= false;
        List<Role> temp = new ArrayList<>();
        if(temp.size()>0) {
            for (Role r : user.getRoles()) {
                if (r.getRoleName()!=role) {
                    temp.add(r);
                    succes = true;
                }
            }
            user.getRoles().addAll(temp);
            this.editUser(user);
        }
        else {
            Role r =new Role(role);
            user.addRole(r);
            this.editUser(user);
            succes = true;
        }
        return succes;
    }

    public boolean addFollower(String user, String follower) {
        User parent = getUser(user);
        User followerUser = getUser(follower);
        if(!parent.getFollowers().contains(followerUser)&&!parent.getUsername().equals(followerUser.getUsername())) {
            parent.addFollower(followerUser);
            followerUser.addFollowing(parent);
            return this.userDAO.addFollower(parent, followerUser);
        }
        return false;
    }

    public boolean removeFollower(String user, String follower) {
        User parent = getUser(user);
        User followerUser = getUser(follower);
        if(parent.getFollowers().contains(followerUser)) {
            parent.removeFollower(followerUser);
            followerUser.removeFollowing(parent);
            return this.userDAO.removeFollower(parent, followerUser);
        }
        return false;
    }
    public List<User>getFollowing(String username){
        return this.userDAO.getFollowing(username);
    }
    public List<User>getFollowers(String username){
        return this.userDAO.getFollowers(username);
    }
}



