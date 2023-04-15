package test;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import root.ActualEnum;
import root.TestEntity;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class MTTest {

    @Inject
    EntityManager em;

    @Test
    void genericEnum() throws InterruptedException {
        long id = setup();

        modify1(id);
        check(id);
    }

    @Transactional
    long setup() {
        TestEntity t1 = new TestEntity(42);
        em.persist(t1);

        assertEquals(0, t1.counter());
        assertEquals(0, t1.version());
        assertNull(t1.getState());

        return t1.id();
    }

    @Transactional
    void modify1(long id) {
        TestEntity t1 = em.find(TestEntity.class, id, LockModeType.PESSIMISTIC_WRITE);
        assertNotNull(t1);

        t1.setState(ActualEnum.BAR);
        assertEquals(ActualEnum.BAR, t1.getState());

        // t1.increment();
    }

    @Transactional
    void check(long id) {
        TestEntity t1 = em.find(TestEntity.class, id, LockModeType.PESSIMISTIC_WRITE);
        assertNotNull(t1);
        assertEquals(ActualEnum.BAR, t1.getState());
    }

}
