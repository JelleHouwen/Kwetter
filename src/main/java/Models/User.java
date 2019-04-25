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
        query = "SELECT u FROM User u WHERE u.username = :username AND u.password = :password"),
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

    public boolean addRole(Role role) {
        boolean succes= false;
        List<Role> temp = new ArrayList<>();
        if(temp.size()>0) {
            for (Role r : this.roles) {
                if (r.getRoleName()!=role.getRoleName()) {
                   temp.add(r);
                 succes = true;
                }
            }
            this.roles.addAll(temp);
        }
        else {
            this.roles.add(role);
            succes = true;
        }
return succes;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void removeRole(Role role) {
        List<Role> temp = new ArrayList<>();
        for(Role r : this.roles){
            if(r.getRoleName().equals(role.getRoleName())){
               temp.add(r);
            }
        }
        this.roles.removeAll(temp);


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
        if (user != null && !this.followers.contains(user) && this.getUsername() != user.getUsername()) {
            this.followers.add(user);
            return true;
        }
        return false;
    }
    public boolean addFollowing(User user) {
        if (user != null && !this.following.contains(user) && this.getUsername() != user.getUsername()) {
            this.following.add(user);
            return true;
        }
        return false;
    }

    public boolean removeFollower(User user) {
        if (user != null && this.followers.contains(user)) {
            this.followers.remove(user);
            return true;
        }
        return false;
    }
    public boolean removeFollowing(User user) {
        if (user != null && this.following.contains(user)) {
            this.following.remove(user);
            return true;
        }
        return false;
    }

    public Boolean follow(User user) {
        this.addFollower(user);
        return true;
    }

    public Boolean unFollow(User user) {
        this.removeFollower(user);
        return true;

    }


}
