package DAO;

import Models.Role;
import Models.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class DAORoles implements IDAORoles {
    @PersistenceContext(unitName = "Kwetter")
    private EntityManager em;

    @Override
    public Role getRoleByName(String name) {
        Query q = em.createNamedQuery("Role.findByRoleName");
        q.setParameter("roleName", name);
        try {
            return (Role) q.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<Role> getAllRoles() {
        Query query = em.createNamedQuery("Role.findAll");
        return (List<Role>) query.getResultList();
    }

}

