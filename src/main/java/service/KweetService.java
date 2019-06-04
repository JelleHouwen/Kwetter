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
    private IDAOKweet kweetDAO;
    @Inject IDAOUser userDAO;
    public KweetService(){
    }
    public void addKweet(Kweet k){
        User kweetUser = userDAO.getUser(k.getPlacer().getUsername());
        k.setPlacer(kweetUser);
        kweetDAO.addKweet(k);
    }
    public void removeKweet(Kweet k) {
        List<Kweet> kweets = this.getAllKweets();
        if(kweets.contains(k)){
            kweetDAO.deleteKweet(k);
        }
    }
    public void editKweet(Kweet k){
        kweetDAO.editKweet(k);
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
    public List<Kweet> getAllKweetsFollowing(int id){return kweetDAO.getKweetsFollowing(id);}
    public List<Kweet> searchKweets(String search){return kweetDAO.searchKweets(search);}

}
