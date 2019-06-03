package dto;

import Models.Kweet;
import Models.Role;
import Models.User;

import java.util.List;

public class UserDTO {
    int id;
    String username;
    String bio;
    String location;
    String website;
    String profilePicture;
    List<User> followers;
    List<User> following;
    List<Kweet> kweets;
    List<Role> roles;

    public UserDTO(User u){
        this.id = u.getID();
        this.username = u.getUsername();
        this.bio = u.getBio();
        this.location = u.getLocation();
        this.website = u.getWebsite();
        this.profilePicture = u.getProfilePicture();
        this.followers = u.getFollowers();
        this.following = u.getFollowing();
        this.kweets = u.getKweets();
        this.roles = u.getRoles();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public List<Kweet> getKweets() {
        return kweets;
    }

    public void setKweets(List<Kweet> kweets) {
        this.kweets = kweets;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
