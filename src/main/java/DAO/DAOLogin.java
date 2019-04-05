package DAO;

import Models.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class DAOLogin {
    @PersistenceContext(unitName = "Kwetter")
    private EntityManager em;

    public User validate(String username,String password){
        Query q = em.createNamedQuery("Account.validateUser");
        q.setParameter("username", username);
        q.setParameter("password",password);
        try {
            return(User)q.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
