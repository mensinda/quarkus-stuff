package test;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import root.StatusEnum;
import root.StatusStorageEntity;

@QuarkusTest
class DoTest {

    @Inject
    EntityManager em;

    @Test
    @Transactional
    void dummyTest() {
        StatusStorageEntity se = new StatusStorageEntity(0, StatusEnum.OK);
        em.persist(se);
    }

}
