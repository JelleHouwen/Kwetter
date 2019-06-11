package dto;

import Models.Kweet;
import Models.Topic;
import Models.User;

import java.util.Date;
import java.util.List;

public class KweetDTO {

     int ID;
     String text;
     Date dateTime;
     List<User> likes;
     List<User> mentions;
     User placer;
     List<Topic> topics;


    public KweetDTO(Kweet kweet) {
        this.ID = kweet.getID();
        this.text = kweet.getText();
        this.dateTime = kweet.getDateTime();
        this.likes = kweet.getLikes();
        this.mentions = kweet.getMentions();
        this.placer = kweet.getPlacer();
        this.topics = kweet.getTopics();
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

    public List<User> getLikes() {
        return likes;
    }

    public void setLikes(List<User> likes) {
        this.likes = likes;
    }

    public List<User> getMentions() {
        return mentions;
    }

    public void setMentions(List<User> mentions) {
        this.mentions = mentions;
    }

    public User getPlacer() {
        return placer;
    }

    public void setPlacer(User placer) {
        this.placer = placer;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }
}
