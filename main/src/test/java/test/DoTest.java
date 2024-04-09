package test;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import base1.sls.std.StdStaplerEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
class DoTest {

    @Inject
    EntityManager em;

    @Transactional
    void setup() {
        System.out.println("------------------------------------------------------------");
        System.out.println("---------------- NEW TEST: setup            ----------------");
        System.out.println("------------------------------------------------------------");

        final StdStaplerEntity stapler = new StdStaplerEntity("001", "Stapler 001", "generic");
        em.persist(stapler);
    }

    @Transactional
    void modify() {
        System.out.println("------------------------------------------------------------");
        System.out.println("---------------- NEW TEST: modify           ----------------");
        System.out.println("------------------------------------------------------------");

        StdStaplerEntity stapler = em.find(StdStaplerEntity.class, "STA001", LockModeType.PESSIMISTIC_WRITE);
        assertNotNull(stapler);
        assertEquals("001", stapler.id());
        assertEquals("Stapler 001", stapler.beschreibung());

        stapler.beschreibungAendern("S - 001");
        assertEquals("S - 001", stapler.beschreibung());
    }

    @Transactional
    void verify() {
        System.out.println("------------------------------------------------------------");
        System.out.println("---------------- NEW TEST: verify           ----------------");
        System.out.println("------------------------------------------------------------");

        StdStaplerEntity stapler = em.find(StdStaplerEntity.class, "STA001");
        assertNotNull(stapler);
        assertEquals("001", stapler.id());
        assertEquals("S - 001", stapler.beschreibung());
    }

    @Test
    void mainTest() {
        setup();
        modify();
        verify();
    }
}
