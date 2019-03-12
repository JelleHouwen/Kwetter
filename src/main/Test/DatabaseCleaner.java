import Models.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.metamodel.EntityType;
import java.sql.SQLException;

public  class DatabaseCleaner {
    private static final Class<?>[] ENTITY_TYPES = {
            User.class,
            Role.class,
            Kweet.class,
            Topic.class,
            Permission.class
    };
    private static EntityManagerFactory em = Persistence.createEntityManagerFactory("Kwetter");
    public  DatabaseCleaner() {

    }

    public static void clean() throws SQLException {
        em.createEntityManager().getTransaction().begin();

        for (Class<?> entityType : ENTITY_TYPES) {
            deleteEntities(entityType);
        }
        em.createEntityManager().getTransaction().commit();
        em.close();
    }

    private static void deleteEntities(Class<?> entityType) {
        em.createEntityManager().createQuery("delete from " + getEntityName(entityType)).executeUpdate();
    }

    protected static String getEntityName(Class<?> clazz) {
        EntityType et = em.getMetamodel().entity(clazz);
        return et.getName();
    }

}