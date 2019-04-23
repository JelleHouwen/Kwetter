package jsf.bean;

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

    public void removeRole(User user,String roleName) {
        Role r = userService.getRole(roleName);
        user.removeRole(r);
        userService.editUser(user);
    }
    public void giveRole(User user,String roleName){
        Role r =userService.getRole(roleName);
        if(user.addRole(r)) {
            userService.editUser(user);
        }
    }
    public void deleteKweet(Kweet kweet) {
       Kweet remove = kwetterService.getKweetByID(kweet.getID());
        kwetterService.removeKweet(remove);
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

