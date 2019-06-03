package jsf;

import Models.Kweet;
import Models.Role;
import Models.User;
import service.KweetService;
import service.UserService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
@Named
public class Admin {


    @EJB
    KweetService kwetterService;

    @EJB
    UserService userService;

    private String role;
    public void removeRole(User user,String roleName) {
        Role r = userService.getRole(roleName);
        user.removeRole(r);
        userService.editUser(user);
    }
    public void giveRole(User user,String roleName){
        System.out.println("adding role...");
       userService.addRole(user,roleName);
    }
    public void deleteKweet(Kweet kweet) {
       Kweet remove = kwetterService.getKweetByID(kweet.getID());
        kwetterService.removeKweet(remove);
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Role> getRoles(){
        return userService.getAllRoles();
    }

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public List<Kweet> getAllKweets() {
        return kwetterService.getAllKweets();
    }
}

