package DAO;

import Models.Topic;

import java.util.List;

public interface IDAOTopic {

    List<Topic> getTrendingTopics();
    void addTopic(Topic topic);
}
