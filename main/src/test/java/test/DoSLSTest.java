package test;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import tup.std.app.tv.sls.std.StdStaplerEntity;
import tup.std.app.tv.sls.std.StdStaplerFactory;
import tup.std.app.tv.sls.std.StdStaplerTypEntity;
import tup.std.app.tv.sls.std.StdStaplerTypFactory;
import tup.std.mid.persistence.api.TupAbstractEntity;
import tup.std.mid.persistence.api.TupInternalApi;
import tup.std.mid.persistence.api.TupOidApi;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
class DoSLSTest {

    @Inject
    EntityManager em;

    // Dummy inject
    @Inject
    TupInternalApi tupInternalApi;

    @Inject
    TupOidApi tupOidApi;

    private <T extends TupAbstractEntity> T patchOid(T entity, String oid) {
        try {
            Field oidField = TupAbstractEntity.class.getDeclaredField("oid");
            oidField.setAccessible(true);
            oidField.set(entity, oid);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        return entity;
    }

    @Transactional
    String setup() {
        System.out.println("------------------------------------------------------------");
        System.out.println("---------------- NEW TEST: setup            ----------------");
        System.out.println("------------------------------------------------------------");

        StdStaplerTypEntity staplerTyp = StdStaplerTypFactory.create("Test Testington", "test");
        StdStaplerEntity stapler = StdStaplerFactory.create("001", "Stapler 001", staplerTyp, "foo.bar", null);
        return stapler.getOid();
    }

    @Transactional
    void modify(String oid) {
        System.out.println("------------------------------------------------------------");
        System.out.println("---------------- NEW TEST: modify           ----------------");
        System.out.println("------------------------------------------------------------");

        StdStaplerEntity stapler = em.find(StdStaplerEntity.class, oid, LockModeType.PESSIMISTIC_WRITE);
        assertNotNull(stapler);
        assertEquals("001", stapler.id());
        assertEquals("Stapler 001", stapler.beschreibung());

        stapler.beschreibungAendern("S - 001");
        assertEquals("S - 001", stapler.beschreibung());
    }

    @Transactional
    void verify(String oid) {
        System.out.println("------------------------------------------------------------");
        System.out.println("---------------- NEW TEST: verify           ----------------");
        System.out.println("------------------------------------------------------------");

        StdStaplerEntity stapler = em.find(StdStaplerEntity.class, oid);
        assertNotNull(stapler);
        assertEquals("001", stapler.id());
        assertEquals("S - 001", stapler.beschreibung());
    }

    @Test
    void mainTest() {
        String oid = setup();
        modify(oid);
        verify(oid);
    }
}
