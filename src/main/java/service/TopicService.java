package service;

import DAO.IDAOKweet;
import DAO.IDAOTopic;
import Models.Topic;

import javax.inject.Inject;
import java.util.List;

public class TopicService {
    @Inject
    private IDAOTopic topicDAO;
    public TopicService(){
    }

   public List<Topic> getTrendingTopics(){
       return this.topicDAO.getTrendingTopics();
    }
    public void addTopic(Topic topic){ this.topicDAO.addTopic(topic);}
}
