package Models;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Account.findAll",
                query = "SELECT u FROM User u"),
        @NamedQuery(name = "Account.findByUsername",
                query = "SELECT u FROM User u where u.username = :userName"),
        @NamedQuery(name = "Account.findByID",
                query = "SELECT u FROM User u where u.ID = :id"),
        @NamedQuery(name = "Account.getByUserNameContains",
                query = "SELECT u FROM User u where u.username like :partOfUsername"),
        @NamedQuery(name = "Account.validateUser",
        query = "SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
})
@Table(name = "User")
public class User implements Serializable {
    @Id
    @GeneratedValue
    private int ID;
    @Column(unique = true)
    private String username;
    private String password;
    private String profilePicture;
    private String bio;
    private String location;
    private String website;

    @JsonbTransient
    @ManyToMany
    @JoinTable(name = "user_followers"
            , joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "ID", nullable = false)
            , inverseJoinColumns = @JoinColumn(name = "follower_id", referencedColumnName = "ID", nullable = false))
    private List<User> followers;

@JsonbTransient
    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private List<User> following;

    @JsonbTransient
    @OneToMany(mappedBy = "placer", cascade = CascadeType.PERSIST)
    private List<Kweet> kweets;


    @JsonbTransient
    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private List<Role> roles;

    public User(String username, String password, String profilePicture, String bio, String location, String website, List<User> followers, List<User> following, List<Kweet> kweets, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.profilePicture = profilePicture;
        this.bio = bio;
        this.location = location;
        this.website = website;
        this.followers = followers;
        this.following = following;
        this.kweets = kweets;
        this.roles = roles;
    }

    public User() {
        this.roles = new ArrayList<>();
        this.kweets = new ArrayList<>();
        this.followers = new ArrayList<>();
        this.following = new ArrayList<>();
    }

    public User(String username) {
        this.username = username;
        this.roles = new ArrayList<>();
        this.kweets = new ArrayList<>();
        this.followers = new ArrayList<>();
        this.following = new ArrayList<>();
    }

    public User(String username,String password) {
        this.username = username;
        this.password=password;
        this.roles = new ArrayList<>();
        this.kweets = new ArrayList<>();
        this.followers = new ArrayList<>();
        this.following = new ArrayList<>();
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }


    public List<Kweet> getKweets() {
        List<Kweet> tempKweets = this.kweets;
        return tempKweets;
    }

    public void setKweets(List<Kweet> kweets) {
        this.kweets = kweets;
    }

    public List<Role> getRoles() {
        List<Role> tempRoles = this.roles;
        return tempRoles;
    }

    public void addKweet(Kweet kweet) {
        if (kweet != null) {
            this.kweets.add(kweet);
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
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

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getRolesString() {
        String roles = "";
            for (int i = 0; i < this.getRoles().size(); i++) {
                if (i != this.getRoles().size()-1) {
                    roles += this.getRoles().get(i).getRoleName() + ", ";
                } else roles += this.getRoles().get(i).getRoleName();
            }


        return roles;
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

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public List<User> getFollowing() {
        List<User> tempFollowing = this.following;
        return tempFollowing;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public boolean addFollower(User user) {
            this.followers.add(user);
            return true;
    }
    public boolean addFollowing(User user) {
            this.following.add(user);
            return true;
    }

    public boolean removeFollower(User user) {
            this.followers.remove(user);
            return true;
    }
    public boolean removeFollowing(User user) {
            this.following.remove(user);
            return true;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public void removeRole(Role role) {
        this.roles.remove(role);
    }


}
