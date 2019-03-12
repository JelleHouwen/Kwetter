package DAO;

import Models.Kweet;
import Models.User;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.*;
import java.util.List;


@Stateless
public class DaoKweetMysql implements IDAOKweet
{

    @PersistenceContext(unitName = "Kwetter")
    private EntityManager em;
    @Inject
    public DaoKweetMysql(){
        em= Persistence.createEntityManagerFactory("Kwetter").createEntityManager();
    }


    @Override
    public boolean addKweet(Kweet kweet) {
        em.getTransaction().begin();
        em.persist(kweet);
        em.getTransaction().commit();
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
    public List<Kweet> getAllKweetsFromUser(User user) {
        return (List<Kweet>) em.createNamedQuery("Kweet.findByUser", Kweet.class).getResultList();
    }

    @Override
    public List<Kweet> getAllKweets() {
        return (List<Kweet>) em.createNamedQuery("Kweet.findAll", Kweet.class).getResultList();
    }

    @Override
    public void deleteKweet(Kweet kweet) {
        Kweet k = em.find(Kweet.class, 1);
        em.getTransaction().begin();
        em.remove(k);
        em.getTransaction().commit();
    }

    @Override
    public void editKweet(Kweet kweet) {
        em.merge(kweet);
    }

    public EntityManager getEM(){
        return this.em;
    }
}
