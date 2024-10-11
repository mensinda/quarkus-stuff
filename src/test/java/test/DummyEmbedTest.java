package test;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import root.TestEntityEmbed;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class DummyEmbedTest {

    @Inject
    EntityManager em;

    @Test
    @Transactional
    void doTest() {
        TestEntityEmbed e = new TestEntityEmbed("yyy", "foo");
        em.persist(e);
        em.flush();
        assertTrue(em.contains(e));
        assertEquals(e.getData(), "foo");

        e.updateData("bar");
        em.flush();
        assertEquals(e.getData(), "bar");
    }

    @Test
    void okTest() {
        update(create());
    }

    @Transactional
    String create() {
        TestEntityEmbed e = new TestEntityEmbed("xxx", "foo");
        em.persist(e);
        assertTrue(em.contains(e));
        assertEquals(e.getData(), "foo");
        return e.getOid();
    }

    @Transactional
    void update(String oid) {
        TestEntityEmbed e = (TestEntityEmbed) em.createQuery("SELECT e FROM TestEntityEmbed e WHERE e.id.oid = ?1").setParameter(1, oid).getSingleResult();
        assertNotNull(e);
        em.lock(e, LockModeType.PESSIMISTIC_WRITE);
        e.updateData("bar");
        em.flush();
        assertEquals(e.getData(), "bar");

        e.updateData("zar");
        em.flush();
        assertEquals(e.getData(), "zar");
    }

}
