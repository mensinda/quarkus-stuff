package test;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import root.CompositeIdClass;
import root.TestEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
class CompositeTest {

    @Inject
    EntityManager em;

    @Test
    @Transactional
    void runTest() {
        TestEntity e1 = new TestEntity("foo", "bar");
        em.persist(e1);

        assertTrue(em.contains(e1));

        CompositeIdClass key = e1.getCompositeId();
        TestEntity e2 = em.find(TestEntity.class, key);

        assertNotNull(e2);
    }

}
