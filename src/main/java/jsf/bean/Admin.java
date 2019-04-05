package jsf.bean;
import Models.Kweet;
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
        public void deleteKweet(Kweet kweet) {
            kwetterService.removeKweet(kweet);
        }

        public void deleteUser(User user) {
            ArrayList<User> followings = new ArrayList<>();
            ArrayList<User> followers = new ArrayList<>();
            ArrayList<User> following2 = new ArrayList<>();
            for (User following : user.getFollowing()) {
                followings.add(following);
            }
            for (User follower : user.getFollowers()) {
                followers.add(follower);
            }


            for (User u : followers) {
                for (User u2 : u.getFollowing()) {
                    following2.add(u2);
                }

            }
            userService.removeUser(user);
        }

        public List<User> getAllUsers() {
            return userService.getAllUsers();
        }

        public List<Kweet> getAllKweets() {
            return kwetterService.getAllKweets();
        }
    }

