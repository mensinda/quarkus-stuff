package test;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import root.ResourceImpl;

import javax.inject.Inject;
import javax.transaction.*;

@QuarkusTest
class TransactionalTest {

    @Inject
    TransactionManager transactionManager;

    @Transactional
    public void sanityImpl() throws SystemException, RollbackException {
        // Enlist
        Transaction t = transactionManager.getTransaction();
        t.enlistResource(new ResourceImpl(""));
    }

    @Test
    void sanity() throws SystemException, RollbackException {
        System.out.println("BEGIN @Transactional SANITY");

        sanityImpl();

        System.out.println("END @Transactional SANITY");
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void suspendInner() throws SystemException, RollbackException {
        // Enlist
        Transaction t = transactionManager.getTransaction();
        t.enlistResource(new ResourceImpl("  "));
    }

    @Transactional
    public void suspendOuter() throws SystemException, RollbackException {
        // Enlist
        Transaction t = transactionManager.getTransaction();
        t.enlistResource(new ResourceImpl(""));

        suspendInner();
    }

    @Test
    void suspend() throws SystemException, RollbackException {
        System.out.println("BEGIN @Transactional SUSPEND");

        suspendOuter();

        System.out.println("END @Transactional SUSPEND");
    }
}
