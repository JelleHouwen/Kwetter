package dto;

import Models.Kweet;
import Models.User;

import java.util.Date;

public class KweetDTO {
    int id;
    String text;
    Date date;
    User placer;

    public KweetDTO(Kweet kweet) {
        this.id = kweet.getID();
        this.text = kweet.getText();
        this.date = kweet.getDateTime();
        this.placer = kweet.getPlacer();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getPlacer() {
        return placer;
    }

    public void setPlacer(User placer) {
        this.placer = placer;
    }
}
