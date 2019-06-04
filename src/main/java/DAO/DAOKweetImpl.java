package DAO;

import Models.Kweet;
import Models.User;
import service.UserService;

import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Alternative
public class DAOKweetImpl implements IDAOKweet{
    List<Kweet> kweets = new ArrayList<>();
    @Inject
    UserService userService;
    public  DAOKweetImpl(){}
    @Override
    public boolean addKweet(Kweet kweet) {
        if(kweet != null){
            this.kweets.add(kweet);
            return true;
        }
        else return false;
    }

    @Override
    public Kweet getKweet(int id) {
        Kweet returnKweet = null;
        for(Kweet k :this.kweets){
            if(k.getID()==id){
                returnKweet = k;
            }
        }
        return returnKweet;
    }
    @Override
    public List<Kweet>getTop10KweetsUser(String username){return null;
    }

    @Override
    public List<Kweet> getKweetsFollowing(int id) {
        return null;
    }

    @Override
    public List<Kweet> searchKweets(String search) {
        return null;
    }

    @Override
    public List<Kweet> getAllKweetsFromUser(String username) {
        User user =userService.getUser(username);
      List<Kweet>userKweets = user.getKweets();
        return userKweets;
    }

    @Override
    public List<Kweet> getAllKweets() {
        List<Kweet> allKweets = this.kweets;
        return allKweets;
    }

    @Override
    public void deleteKweet(Kweet kweet) {
        this.kweets.remove(kweet);
    }

    @Override
    public void editKweet(Kweet kweet) {
        for(Kweet k : this.kweets){
            if(k.getID()==kweet.getID()){
                k.setText(kweet.getText());
            }
        }
    }

    @Override
    public List<Kweet> getTop20Kweets() {
        return null;
    }
}
