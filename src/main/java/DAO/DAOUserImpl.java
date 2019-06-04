package DAO;

import Models.User;

import javax.enterprise.inject.Alternative;
import java.util.ArrayList;
import java.util.List;

@Alternative
public class DAOUserImpl implements IDAOUser {
    List<User> users;
    public DAOUserImpl(){
        users = new ArrayList<>();
    }
    @Override
    public User getUser(String username) {
        User returnUser = null;
        if(this.users==null){
            this.users = new ArrayList<>();
        }
        for (User u : this.users){
            if(u.getUsername().equals(username)){
                returnUser = u;
            }
        }
        return returnUser;
    }



    @Override
    public List<User> getAllUsers() {
        if(this.users!= null) {
            List<User> returnUsers = this.users;
            return returnUsers;
        }
        else return new ArrayList<>();
    }

    @Override
    public void addUser(User user) {
        if(!this.users.contains(user)) {
            this.users.add(user);

        }

    }

    @Override
    public boolean addFollower(User user,User follower){


       user.addFollower(follower);
       return follower.addFollowing(user);
    }
    @Override
    public boolean removeFollower(User user,User follower){


        user.removeFollower(follower);
        return follower.removeFollowing(user);
    }

    @Override
    public List<User> getFollowing(String username) {
        return null;
    }

    @Override
    public List<User> getFollowers(String username) {
        return null;
    }

    @Override
    public void removeUser(User user) {
        if (this.users.contains(user)) {
            this.users.remove(user);

        }

    }

    @Override
    public void editUser(User user) {
       for(User u : this.users){
           if (u.getUsername()==user.getUsername()){
               u.setUsername(user.getUsername());
               u.setFollowers(user.getFollowers());
               u.setFollowing(user.getFollowing());
               u.setKweets(user.getKweets());
           }
       }

    }



}
