package DAO;

import Models.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class DAOLogin implements IDAOLogin {
    @PersistenceContext(unitName = "Kwetter")
    private EntityManager em;

    public boolean validate(String username,String password) {
        boolean succes = false;
        Query q = em.createNamedQuery("Account.validateUser");
        q.setParameter("username", username);
        q.setParameter("password",password);
        try {
            User u =(User)q.getSingleResult();
            if(u!=null){
               succes = true;
            }
        } catch (NoResultException ex) {
            succes = false;
        }
        return succes;
    }
}
