package DAO;

import Models.User;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;


@Stateless
public class DAOUserMysql implements IDAOUser {

    @PersistenceContext(unitName = "kwetter")
    private EntityManager em;
    public DAOUserMysql() {
        em=Persistence.createEntityManagerFactory("Kwetter").createEntityManager();

    }
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
    public boolean addUser(User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        return true;
    }

    @Override
    public boolean removeUser(User user) {

        em.getTransaction().begin();
        em.remove(user);
        em.getTransaction().commit();
        return true;
    }

    @Override
    public boolean editUser(User user) {
        User u = em.find(User.class, 1);
        em.getTransaction().begin();
        u.setUserName(user.getUserName());
        em.getTransaction().commit();
        return true;
    }

    @Override
    public void followerUser(User followee, User follower) {

    }

    public EntityManager getEM(){
        return this.em;
    }
}
