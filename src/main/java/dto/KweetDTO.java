package dto;

import Models.User;

import java.util.Date;

public class KweetDTO {
    int id;
    String text;
    Date date;
    String placer;

    public KweetDTO(int id, String text, Date date, String placer) {
        this.id = id;
        this.text = text;
        this.date = date;
        this.placer = placer;
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

    public String getPlacer() {
        return placer;
    }

    public void setPlacer(String placer) {
        this.placer = placer;
    }
}
