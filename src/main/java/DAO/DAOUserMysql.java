package DAO;

import Models.User;

import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.*;
import java.util.List;


@Stateless
public class DAOUserMysql implements IDAOUser {

    @PersistenceContext(unitName = "Kwetter")
    private EntityManager em;

    @Override
    public User getUser(String username) {
        Query q = em.createNamedQuery("Account.findByUsername");
        q.setParameter("userName", username);
        try {
            return (User) q.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
    @Override
    public List<User> getAllUsers() {
            Query query = em.createNamedQuery("Account.findAll");
            return (List<User>) query.getResultList();
    }



@Override
public boolean addFollower(String user,String follower){
        boolean returnValue = false;
       User parent = getUser(user);
       User followerUser = getUser(follower);
      returnValue = parent.addFollower(followerUser);
       followerUser.addFollowing(parent);

       em.persist(parent);
       em.persist(followerUser);

       return returnValue;
}
    @Override
    public boolean removeFollower(String user,String follower){
        boolean returnValue = false;
        User parent = getUser(user);
        User followerUser = getUser(follower);
        returnValue = parent.removeFollower(followerUser);
        followerUser.removeFollowing(parent);

        em.persist(parent);
        em.persist(followerUser);

        return returnValue;
    }

    @Override
    public void addUser(User user) {
        if(!this.getAllUsers().contains(user)) {
            em.persist(user);
        }
    }

    @Override
    public void removeUser(User user) {
        if(this.getAllUsers().contains(user)) {
            em.remove(user);
        }
    }

    @Override
    public void editUser(User user) {
     em.merge(user);
    }




}
