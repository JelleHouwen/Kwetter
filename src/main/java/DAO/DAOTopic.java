package DAO;

import Models.Role;
import Models.Topic;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class DAOTopic implements IDAOTopic {
    @PersistenceContext(unitName = "Kwetter")
    private EntityManager em;
    @Override
    public List<Topic> getTrendingTopics() {
        Query query = em.createNamedQuery("Topic.getTrendingTopics");
        Query q = em.createNativeQuery("SELECT t.*,count(t.title) total  from Topic t group by t.title order by total desc",Topic.class);
        query.setMaxResults(5);
        q.setMaxResults(5);
        try {
            return (List<Topic>) q.getResultList();
        } catch (NoResultException ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public void addTopic(Topic topic) {

        em.persist(topic);
    }
}
