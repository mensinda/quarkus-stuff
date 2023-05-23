package test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.LockModeType;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.Test;
import root.TestEntity;
import root.TestNoVersEntity;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CreateTest {

    private static EntityManagerFactory emf = null;

    private EntityManager persistenceManager() {
        if (emf == null || !emf.isOpen()) {
            // Lazy load der EntityManagerFactory. Siehe Kommentar oben!
            emf = Persistence.createEntityManagerFactory("TupLink");
        }
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        return em;
    }


    private static long counter = 1;

    @Test
    void create() {
        final EntityManager em = persistenceManager();
        try {
            TestEntity orig = new TestEntity(counter++);
            em.persist(orig);
            em.lock(orig, LockModeType.PESSIMISTIC_WRITE);
        } finally {
            em.getTransaction().rollback();
            em.close();
        }
    }

    @Test
    void createLock() {
        final EntityManager em = persistenceManager();
        try {
            TestEntity orig = new TestEntity(counter++);
            em.persist(orig);

            TestEntity found = em.find(TestEntity.class, orig.id());
            assertNotNull(found);
            em.lock(found, LockModeType.PESSIMISTIC_WRITE);
        } finally {
            em.getTransaction().rollback();
            em.close();
        }
    }

    @Test
    void createFindLock() {
        final EntityManager em = persistenceManager();
        try {
            TestEntity orig = new TestEntity(counter++);
            em.persist(orig);

            TestEntity found = em.find(TestEntity.class, orig.id(), LockModeType.PESSIMISTIC_WRITE);
            assertNotNull(found);
        } finally {
            em.getTransaction().rollback();
            em.close();
        }
    }

    @Test
    void createLockNoVers() {
        final EntityManager em = persistenceManager();
        try {
            TestNoVersEntity orig = new TestNoVersEntity(counter++);
            em.persist(orig);

            TestNoVersEntity found = em.find(TestNoVersEntity.class, orig.id());
            assertNotNull(found);
            em.lock(found, LockModeType.PESSIMISTIC_WRITE);
        } finally {
            em.getTransaction().rollback();
            em.close();
        }
    }

    @Test
    void createFindLockNoVers() {
        final EntityManager em = persistenceManager();
        try {
            TestNoVersEntity orig = new TestNoVersEntity(counter++);
            em.persist(orig);

            TestNoVersEntity found = em.find(TestNoVersEntity.class, orig.id(), LockModeType.PESSIMISTIC_WRITE);
            assertNotNull(found);
        } finally {
            em.getTransaction().rollback();
            em.close();
        }
    }

}
