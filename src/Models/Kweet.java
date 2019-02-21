package Models;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class Kweet implements Serializable {
    private int ID;
    private String text;
    private Date dateTime;
    private List<User> likes;

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

    public List<User> getLikes() {
       List<User>tempLikes = this.likes;
        return tempLikes;
    }
    public void addLikes(User user){
        this.likes.add(user);
    }

    public void setLikes(List<User> likes) {
        this.likes = likes;
    }

    public Kweet(String text){
        this.text = text;
    }

    public void sendKweet(){
        throw new NotImplementedException();
    }

    public void like(User user){
        this.addLikes(user);
    }
}
