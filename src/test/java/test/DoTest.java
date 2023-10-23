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

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        assertNotNull(m);
        ReferencedEntity e1 = m.getReferenced();
        assertNotNull(e1);
        assertEquals("ok", e1.status());
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
        assertNotNull(m);
        ReferencedEntity e1 = m.getReferenced();
        assertNotNull(e1);
        em.lock(e1, LockModeType.PESSIMISTIC_WRITE);
        assertEquals("ok", e1.status());
    }

    /**
     * Does <b>NOT</b> work as expected! No `select for update`!
     */
    @Test
    @Transactional
    void refresh() {
        System.out.println("------------------------------------------------------------");
        System.out.println("---------------- NEW TEST: refresh          ----------------");
        System.out.println("------------------------------------------------------------");

        MainEntity m = em.find(MainEntity.class, 0);
        assertNotNull(m);
        ReferencedEntity e1 = m.getReferenced();
        assertNotNull(e1);

        // First refresh, then access
        em.refresh(e1, LockModeType.PESSIMISTIC_WRITE);
        assertEquals("ok", e1.status());
    }

    /**
     * Works as expected
     */
    @Test
    @Transactional
    void refreshAfter() {
        System.out.println("------------------------------------------------------------");
        System.out.println("---------------- NEW TEST: refreshAfterRead ----------------");
        System.out.println("------------------------------------------------------------");

        MainEntity m = em.find(MainEntity.class, 0);
        assertNotNull(m);
        ReferencedEntity e1 = m.getReferenced();
        assertNotNull(e1);

        // First access, then refresh
        assertEquals("ok", e1.status());
        em.refresh(e1, LockModeType.PESSIMISTIC_WRITE);
    }

    /**
     * Works as expected
     */
    @Test
    @Transactional
    void directLoading() {
        System.out.println("------------------------------------------------------------");
        System.out.println("---------------- NEW TEST: directLoading    ----------------");
        System.out.println("------------------------------------------------------------");

        ReferencedEntity e2 = em.find(ReferencedEntity.class, 1);
        assertNotNull(e2);
        em.refresh(e2, LockModeType.PESSIMISTIC_WRITE);
        assertEquals("warn", e2.status());
    }

}
