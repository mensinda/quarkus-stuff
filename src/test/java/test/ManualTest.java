package test;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import root.ResourceImpl;

import javax.inject.Inject;
import javax.transaction.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class ManualTest {

    @Inject
    TransactionManager transactionManager;

    @Test
    void sanity() throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        System.out.println("BEGIN MANUAL SANITY");
        transactionManager.begin();

        // Enlist
        Transaction t = transactionManager.getTransaction();
        t.enlistResource(new ResourceImpl(""));

        transactionManager.commit();
        System.out.println("END MANUAL SANITY");
    }

    @Test
    void suspend() throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException, InvalidTransactionException {
        System.out.println("BEGIN MANUAL SUSPEND");
        transactionManager.begin();

        // Enlist
        Transaction rootT = transactionManager.getTransaction();
        rootT.enlistResource(new ResourceImpl(""));


        // now suspend the root transaction and start and commit a new one
        Transaction suspended = transactionManager.suspend();
        assertEquals(rootT, suspended);

        transactionManager.begin();
        Transaction inner = transactionManager.getTransaction();
        inner.enlistResource(new ResourceImpl("  "));
        transactionManager.commit();


        // Resume and commit
        transactionManager.resume(rootT);
        transactionManager.commit();
        System.out.println("END MANUAL SUSPEND");
    }

}
