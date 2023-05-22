package test;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import root.TestEntity;
import root.TestNoVersEntity;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
class CreateTest {

    @Inject
    EntityManager em;

    private static long counter = 1;

    @Test
    @Transactional
    void create() {
        TestEntity orig = new TestEntity(counter++);
        em.persist(orig);
        em.lock(orig, LockModeType.PESSIMISTIC_WRITE);
    }

    @Test
    @Transactional
    void createLock() {
        TestEntity orig = new TestEntity(counter++);
        em.persist(orig);

        TestEntity found = em.find(TestEntity.class, orig.id());
        assertNotNull(found);
        em.lock(found, LockModeType.PESSIMISTIC_WRITE);
    }

    @Test
    @Transactional
    void createFindLock() {
        TestEntity orig = new TestEntity(counter++);
        em.persist(orig);

        TestEntity found = em.find(TestEntity.class, orig.id(), LockModeType.PESSIMISTIC_WRITE);
        assertNotNull(found);
    }

    @Test
    @Transactional
    void createLockNoVers() {
        TestNoVersEntity orig = new TestNoVersEntity(counter++);
        em.persist(orig);

        TestNoVersEntity found = em.find(TestNoVersEntity.class, orig.id());
        assertNotNull(found);
        em.lock(found, LockModeType.PESSIMISTIC_WRITE);
    }

    @Test
    @Transactional
    void createFindLockNoVers() {
        TestNoVersEntity orig = new TestNoVersEntity(counter++);
        em.persist(orig);

        TestNoVersEntity found = em.find(TestNoVersEntity.class, orig.id(), LockModeType.PESSIMISTIC_WRITE);
        assertNotNull(found);
    }

}
