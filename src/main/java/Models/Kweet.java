package Models;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Kweet.findAll",
                query = "SELECT k FROM Kweet k"),
        @NamedQuery(name = "Kweet.findLast20",
                query = "SELECT k FROM Kweet k"),
        @NamedQuery(name = "Kweet.getById",
                query = "SELECT k FROM Kweet k where k.ID = :id"),
        @NamedQuery(name = "Kweet.findByUser",
                query = "SELECT k FROM Kweet k where k.placer.username = :username")
})
@Table(name = "Kweet")
@XmlRootElement
public class Kweet implements Serializable {
    @Id
    @GeneratedValue
    private int ID;
    private String text;
    private Date dateTime;
    @JsonbTransient
    @OneToMany
    private List<User> likes;
    @JsonbTransient
    @ManyToOne(cascade = CascadeType.PERSIST)
    private User placer;
    @JsonbTransient
    @OneToMany
    private List<Topic> topics;


    public Kweet(String text,User user){
        this.text = text;
        this.placer = user;
        this.dateTime= new Date();
   }

    public Kweet() {
        this.dateTime= new Date();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
//
//    public List<User> getLikes() {
//       List<User>tempLikes = this.likes;
//        return tempLikes;
//    }
//    public void like(User user){
//        this.likes.add(user);
//    }
//
//    public void setLikes(List<User> likes) {
//        this.likes = likes;
//    }

    public User getPlacer(){
        return this.placer;
    }
}
