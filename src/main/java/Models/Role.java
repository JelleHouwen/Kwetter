package Models;

import Models.User;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Role.findAll",
                query = "SELECT r FROM Role r"),
        @NamedQuery(name = "Role.findByRoleName",
                query = "SELECT r FROM Role r where r.roleName = :roleName"),
})
@Table(name = "Role")
@XmlRootElement

public class Role implements Serializable {

    public Role() {
        users = new ArrayList<>();
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    @Column(unique = true)
    private String roleName;
    @ManyToMany
    private List<User> users;

    @ManyToMany
    @JoinTable(name="Role_Permission"
            , joinColumns = @JoinColumn(name = "RoleID", referencedColumnName = "ID")
            , inverseJoinColumns = @JoinColumn(name = "PermissionID", referencedColumnName = "ID"))
    private List<Permission> permissions;


    public Role(String roleName) {
        this.roleName = roleName;
    }


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    public List<User> getUsers() {
        return users;
    }


    public List<Permission> getPermissions() {
        return permissions;
    }

    @Override
    public String toString() {
        return  roleName;
    }
}