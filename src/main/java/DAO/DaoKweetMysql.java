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
    public List<Kweet> getAllKweetsFromUser(String username) {
        Query q = em.createNamedQuery("Kweet.findByUser");
        q.setParameter("username", username);
        try {
            return (List<Kweet>) q.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<Kweet> getAllKweets() {
        return (List<Kweet>) em.createNamedQuery("Kweet.findAll", Kweet.class).getResultList();
    }

    @Override
    public void deleteKweet(Kweet kweet) {
        if (!em.contains(kweet)) {
            kweet = em.merge(kweet);
        }
        em.remove(kweet);
    }

    @Override
    public void editKweet(Kweet kweet) {
        em.merge(kweet);
    }

    public EntityManager getEM(){
        return this.em;
    }
    public void setEM(EntityManager EM){
        this.em= EM;
    }
}
