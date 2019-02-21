package Models;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private String userName;
    private String password;
    private int ID;
    private Image profilePicture;
    private String bio;
    private String location;
    private String website;
    private List<User> followers;
    private List<User> following;
    private List<Kweet> kweets;

    public List<Kweet> getKweets() {
        List<Kweet> tempKweets = this.kweets;
        return tempKweets;
    }

    public void addKweet(Kweet kweet){
       if(kweet!=null) {
           this.kweets.add(kweet);
       }
    }
    public void setKweets(List<Kweet> kweets) {
        this.kweets = kweets;
    }

    public User(String username) {
        this.userName = username;
        this.kweets = new ArrayList<>();
        this.followers = new ArrayList<>();
        this.following = new ArrayList<>();
    }

    public boolean login(String username, String password) {
        throw new NotImplementedException();
    }

    public void logout() {
        throw new NotImplementedException();

    }

    public void register(String username, String password) {
        throw new NotImplementedException();

    }

    public void updateProfile(String username) {
        throw new NotImplementedException();

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Image getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Image profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public List<User> getFollowers() {
        List<User> tempFollers = this.followers;
        return tempFollers;
    }

    public void setFollowers(ArrayList<User> followers) {
        this.followers = followers;
    }

    public List<User> getFollowing() {
        List<User> tempFollowing = this.following;
        return tempFollowing;
    }

    public void setFollowing(ArrayList<User> following) {
        this.following = following;
    }

    public void addFollower(User user){
        if(!this.followers.contains(user)) {
            this.followers.add(user);
        }
    }


    public void removeFollower(User user){
        if(this.followers.contains(user)) {
            this.followers.remove(user);
        }
    }

    public Boolean follow(User user) {
            this.addFollower(user);
            return true;
    }
    public Boolean unFollow(User user) {
            this.removeFollower(user);
            return true;

    }

    public void saveUser(){
        ObjectOutput out;
        try {
            out = new ObjectOutputStream(new FileOutputStream("appUser.data"));
            out.writeObject(this);
            out.close();
        } catch (Exception e) {e.printStackTrace();}
    }


}
