package Models;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@Table(name = "Topic")
@XmlRootElement
@NamedQuery(name = "Topic.getTrendingTopics",
        query = "SELECT t.title, count(t)   from Topic t group by t.title")
public class Topic implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    private String title;
    public int getId() {
        return ID;
    }

    public void setId(int id) {
        this.ID = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
