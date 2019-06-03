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
public boolean addFollower(User user,User follower){
       em.persist(user);
       em.persist(follower);
       return true;
}
    @Override
    public boolean removeFollower(User user,User follower){

        em.persist(user);
        em.persist(follower);

        return true;
    }

    @Override
    public List<User> getFollowing(String username) {
        return this.getUser(username).getFollowing();
    }

    @Override
    public List<User> getFollowers(String username) {
        return  this.getUser(username).getFollowers();
    }

    @Override
    public void addUser(User user) {
            em.persist(user);
    }

    @Override
    public void removeUser(User user) {
        em.remove(user);
    }

    @Override
    public void editUser(User user) {
     em.merge(user);
    }




}
