package test;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import root.TestEntity;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class MTTest {

    @Inject
    EntityManager em;

    private static final AtomicInteger counter = new AtomicInteger();

    /**
     * Passes
     */
    @Test
    void genericEnum1() {
        long id = setup();

        modify1(id);
        check(id);
    }

    /**
     * Passes
     */
    @Test
    void genericEnum2() {
        long id = setup();

        modify2(id);
        check(id);
    }

    /**
     * Fails
     */
    @Test
    void genericEnum3() {
        long id = setup();

        modify3(id);
        check(id);
    }

    /**
     * Fails
     */
    @Test
    void genericEnum4() {
        long id = setup();

        modify3(id);
        check(id);
    }

    @Transactional
    long setup() {
        TestEntity t1 = new TestEntity(counter.incrementAndGet());
        em.persist(t1);

        assertEquals(0, t1.counter());
        assertEquals(0, t1.version());
        assertNull(t1.getState());

        return t1.id();
    }

    /**
     * ONLY change the state of the mapped super class
     */
    @Transactional
    void modify1(long id) {
        TestEntity t1 = em.find(TestEntity.class, id, LockModeType.PESSIMISTIC_WRITE);
        assertNotNull(t1);

        t1.setState("BAR");
        // t1.increment();

        assertEquals("BAR", t1.getState());
    }

    /**
     * Also call increment to modify something else
     */
    @Transactional
    void modify2(long id) {
        TestEntity t1 = em.find(TestEntity.class, id, LockModeType.PESSIMISTIC_WRITE);
        assertNotNull(t1);

        t1.setState("BAR");
        t1.increment();

        assertEquals("BAR", t1.getState());
    }

    /**
     * ONLY change the state of the mapped super class -- but via a method in the child class
     */
    @Transactional
    void modify3(long id) {
        TestEntity t1 = em.find(TestEntity.class, id, LockModeType.PESSIMISTIC_WRITE);
        assertNotNull(t1);

        t1.updateStateInEntity("BAR");
        // t1.increment();

        assertEquals("BAR", t1.getState());
    }

    /**
     * ONLY change the state of the mapped super class -- but via a method in the child class -- also increment
     */
    @Transactional
    void modify4(long id) {
        TestEntity t1 = em.find(TestEntity.class, id, LockModeType.PESSIMISTIC_WRITE);
        assertNotNull(t1);

        t1.updateStateInEntity("BAR");
        t1.increment();

        assertEquals("BAR", t1.getState());
    }

    @Transactional
    void check(long id) {
        TestEntity t1 = em.find(TestEntity.class, id, LockModeType.PESSIMISTIC_WRITE);
        assertNotNull(t1);
        assertEquals("BAR", t1.getState());
    }

}
