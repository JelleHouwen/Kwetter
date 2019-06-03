package DAO;

import Models.Kweet;
import Models.User;

import java.util.List;

public interface IDAOKweet {
    boolean addKweet(Kweet kweet);
    Kweet getKweet(int id);
    List<Kweet> getAllKweetsFromUser(String username);
    List<Kweet> getAllKweets();
    void deleteKweet(Kweet kweet);
    void editKweet(Kweet kweet);
    List<Kweet> getTop20Kweets();
    List<Kweet> getTop10KweetsUser(String username);
    List<Kweet> getKweetsFollowing(int id);
    List<Kweet> searchKweets(String search);
}
