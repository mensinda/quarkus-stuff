package test;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import root.TestEntity;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
class MTTest {

    @Inject
    EntityManager em;

    @Test
    @Transactional
    void sanity() {
        TestEntity t1 = new TestEntity(1);
        em.persist(t1);

        assertEquals(0, t1.counter());
        assertEquals(1, t1.id());

        t1.increment();
        t1.increment();

        assertEquals(2, t1.counter());
    }

    @Test
    void multithreading() throws InterruptedException {
        long id = setup();

        List<Throwable> exceptions = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();

        // Create the threads
        for (int i = 0; i < 8; i++) {
            final int finalI = i;
            Thread t = new Thread(() -> mtLock(id, finalI));

            // register thrown exceptions
            t.setUncaughtExceptionHandler((t1, e) -> {
                exceptions.add(e);
                e.printStackTrace();
            });

            t.start();
            threads.add(t);
        }

        // Wait for the threads
        for (Thread thread : threads) {
            thread.join();
        }

        // No errors pls.
        assertEquals(0, exceptions.size());

        // Check
        check(id);
    }

    @Transactional
    long setup() {
        TestEntity t1 = new TestEntity(42);
        em.persist(t1);

        assertEquals(0, t1.counter());
        assertEquals(0, t1.version());

        return t1.id();
    }

    @Transactional
    void mtLock(long id, int num) {
        System.out.printf("Thread %d: Fetching...\n", num);
        TestEntity t1 = em.find(TestEntity.class, id);
        assertNotNull(t1);

        System.out.printf("Thread %d: Locking...\n", num);

        // Exception here!
        em.refresh(t1, LockModeType.PESSIMISTIC_WRITE);

        System.out.printf("Thread %d: LOCKED\n", num);

        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        t1.increment();

        System.out.printf("Thread %d: EXIT\n", num);
    }

    @Transactional
    void check(long id) {
        TestEntity t1 = em.find(TestEntity.class, id, LockModeType.PESSIMISTIC_WRITE);
        assertNotNull(t1);
        assertEquals(8, t1.counter());
        assertEquals(8, t1.version());
    }

}
