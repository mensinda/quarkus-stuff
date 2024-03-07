package test;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import root.MainEntity;
import root.ReferencedEntity;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
class DoTest {

    @Inject
    EntityManager em;

    @BeforeAll
    @Transactional
    static void setup() {
        final EntityManager em = CDI.current().select(EntityManager.class).get();
        final ReferencedEntity e1 = new ReferencedEntity(0, "ok");
        final ReferencedEntity e2 = new ReferencedEntity(1, "warn");
        em.persist(e1);
        em.persist(e2);
        final MainEntity e3 = new MainEntity(0, e1);
        em.persist(e3);
    }

    /**
     * Works as expected
     */
    @Test
    @Transactional
    void justLoading() {
        System.out.println("------------------------------------------------------------");
        System.out.println("---------------- NEW TEST: justLoading      ----------------");
        System.out.println("------------------------------------------------------------");

        MainEntity m = em.find(MainEntity.class, 0);
        ReferencedEntity e1 = em.find(ReferencedEntity.class, 0);
        assertNotNull(m);
        assertNotNull(e1);
    }

    /**
     * Works as expected
     */
    @Test
    @Transactional
    void lock() {
        System.out.println("------------------------------------------------------------");
        System.out.println("---------------- NEW TEST: lock             ----------------");
        System.out.println("------------------------------------------------------------");

        MainEntity m = em.find(MainEntity.class, 0);
        ReferencedEntity e1 = em.find(ReferencedEntity.class, 0);
        assertNotNull(m);
        assertNotNull(e1);
        em.lock(m, LockModeType.PESSIMISTIC_WRITE);
        em.lock(e1, LockModeType.PESSIMISTIC_WRITE);
    }

    /**
     * Works as expected
     */
    @Test
    @Transactional
    void lockBetween() {
        System.out.println("------------------------------------------------------------");
        System.out.println("---------------- NEW TEST: lockBetween      ----------------");
        System.out.println("------------------------------------------------------------");

        MainEntity m = em.find(MainEntity.class, 0);
        assertNotNull(m);
        em.lock(m, LockModeType.PESSIMISTIC_WRITE);
        ReferencedEntity e1 = em.find(ReferencedEntity.class, 0);
        assertNotNull(e1);
        em.lock(e1, LockModeType.PESSIMISTIC_WRITE);
    }

    /**
     * FAILS!
     */
    @Test
    @Transactional
    void findAndLock() {
        System.out.println("------------------------------------------------------------");
        System.out.println("---------------- NEW TEST: findAndLock      ----------------");
        System.out.println("------------------------------------------------------------");

        MainEntity m = em.find(MainEntity.class, 0, LockModeType.PESSIMISTIC_WRITE);
        ReferencedEntity e1 = em.find(ReferencedEntity.class, 0, LockModeType.PESSIMISTIC_WRITE);
        assertNotNull(m);
        assertNotNull(e1);
    }

    /**
     * Works as expected
     */
    @Test
    @Transactional
    void findAndLockLock() {
        System.out.println("------------------------------------------------------------");
        System.out.println("---------------- NEW TEST: findAndLockLock  ----------------");
        System.out.println("------------------------------------------------------------");

        MainEntity m = em.find(MainEntity.class, 0, LockModeType.PESSIMISTIC_WRITE);
        ReferencedEntity e1 = em.find(ReferencedEntity.class, 0);
        assertNotNull(m);
        assertNotNull(e1);
        em.lock(e1, LockModeType.PESSIMISTIC_WRITE);
    }

    /**
     * FAILS!
     */
    @Test
    @Transactional
    void lockFindAndLock() {
        System.out.println("------------------------------------------------------------");
        System.out.println("---------------- NEW TEST: lockFindAndLock  ----------------");
        System.out.println("------------------------------------------------------------");

        MainEntity m = em.find(MainEntity.class, 0);
        assertNotNull(m);
        em.lock(m, LockModeType.PESSIMISTIC_WRITE);
        ReferencedEntity e1 = em.find(ReferencedEntity.class, 0, LockModeType.PESSIMISTIC_WRITE);
        assertNotNull(e1);

    }

}
