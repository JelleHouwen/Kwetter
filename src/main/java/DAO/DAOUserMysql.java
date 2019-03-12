package DAO;

import Models.User;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Stateless
public class DAOUserMysql implements IDAOUser {

    @PersistenceContext(unitName = "kwetterPU")
    private EntityManager em;

    @Inject
    public DAOUserMysql(EntityManager em) {
        this.em = em;
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
        User u = em.find(User.class, 1);
        em.getTransaction().begin();
        em.remove(u);
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
}
