package DAO;

import Models.Kweet;
import Models.User;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.*;
import java.util.List;

@Stateless
public class DaoKweetMysql implements IDAOKweet
{

    @PersistenceContext(unitName = "Kwetter")
    private EntityManager em;

    @Override
    public boolean addKweet(Kweet kweet) {
        em.persist(kweet);
        return true;
    }

    @Override
    public Kweet getKweet(int id) {
        Query q = em.createNamedQuery("Kweet.getById");
        q.setParameter("id", id);
        try {
            return (Kweet) q.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<Kweet>getTop10KweetsUser(String username){
        Query q = em.createNamedQuery("Kweet.findByUser");
        q.setParameter("username", username);
        q.setMaxResults(10);
        try {
            return (List<Kweet>) q.getResultList();
        } catch (NoResultException ex) {
            return null;
        }
    }
    @Override
    public List<Kweet> getAllKweetsFromUser(String username) {
        Query q = em.createNamedQuery("Kweet.findByUser");
        q.setParameter("username", username);
        try {
            return (List<Kweet>) q.getResultList();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<Kweet> getAllKweets() {
        Query q = em.createNamedQuery("Kweet.findAll");
        try {
            return (List<Kweet>) q.getResultList();
        } catch (NoResultException ex) {
            return null;
        }

    }
    @Override
    public List<Kweet> getTop20Kweets() {
        Query q = em.createNamedQuery("Kweet.findLast20");
        q.setMaxResults(20);
        try {
            return (List<Kweet>) q.getResultList();
        } catch (NoResultException ex) {
            return null;
        }
    }


    @Override
    public void deleteKweet(Kweet kweet) {
        em.remove(kweet);
    }


    @Override
    public List<Kweet> searchKweets(String search) {
        Query q = em.createNamedQuery("Kweet.search");
        q.setParameter("search", "%" + search + "%");
        try {
            return (List<Kweet>) q.getResultList();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public void editKweet(Kweet kweet) {
        em.merge(kweet);
    }

    @Override
    public List<Kweet> getKweetsFollowing(int id){
        Query q1 = em.createNativeQuery("SELECT distinct k.* FROM Kweet k inner join User u inner join user_user uu WHERE k.PLACER_ID in (select u.id from user u ,user_followers uu where uu.User_ID = u.id and uu.follower_id = ? or u.id=?)ORDER BY k.dateTime desc",Kweet.class);
        q1.setParameter(1, id);
        q1.setParameter(2, id);
        q1.setMaxResults(20);
        try {
            return (List<Kweet>) q1.getResultList();
        } catch (NoResultException ex) {
            return null;
        }

    }
}
