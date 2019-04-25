package service;

import DAO.*;
import Models.Kweet;
import Models.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class KweetService {
    @Inject
    private IDAOUser userDAO;
    @Inject
    private IDAOKweet kweetDAO;

    public KweetService(){
    }

    public void addKweet(Kweet k){
        kweetDAO.addKweet(k);
    }

    public void removeKweet(Kweet k) {
        kweetDAO.deleteKweet(k);
    }

    public void editKweet(Kweet k){
        kweetDAO.editKweet(k);
    }

    public List<Kweet> getLast20(){
        return kweetDAO.getTop20Kweets();
    }
    public List<Kweet> getLast10User(String username){
        return kweetDAO.getTop10KweetsUser(username);
    }


    public List<Kweet> getAllKweets(){
       return kweetDAO.getAllKweets();
    }

    public Kweet getKweetByID(int id){
       return kweetDAO.getKweet(id);
    }
    public List<Kweet> getAllKweetsFromUser(String username){
        return kweetDAO.getAllKweetsFromUser(username);
    }

}
