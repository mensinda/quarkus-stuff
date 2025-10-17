package test;

import io.quarkus.test.junit.QuarkusTest;
import libtest.BaseClass;
import libtest.ClassHelper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static io.smallrye.common.constraint.Assert.assertNotNull;

@QuarkusTest
public class LoadingTest {

    public static Class<?> loadInTest(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This works just fine
     */
    @Test
    void loadingNormally() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> cls = loadInTest("test.TestImpl");

        BaseClass foo = (BaseClass) cls.getConstructor().newInstance();
        assertNotNull(foo);
    }

    /**
     * This FAILS!
     */
    @Test
    @Disabled
    void loadingNormallyFromLib() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> cls = ClassHelper.loadClass("test.TestImpl");

        BaseClass foo = (BaseClass) cls.getConstructor().newInstance();
        assertNotNull(foo);
    }

    /**
     * This works
     */
    @Test
    void loadingNormallyFromLibInLib() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> cls = ClassHelper.loadClass("libtest.LibImpl");

        BaseClass foo = (BaseClass) cls.getConstructor().newInstance();
        assertNotNull(foo);
    }

    /**
     * This works...
     */
    @Test
    void loadingCurrentThreadFromLib() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> cls = ClassHelper.loadClassFromThread("test.TestImpl");

        BaseClass foo = (BaseClass) cls.getConstructor().newInstance();
        assertNotNull(foo);
    }


    private void inThreadImplNoraml() {
        Class<?> cls = ClassHelper.loadClass("test.TestImpl");

        BaseClass foo = null;
        try {
            foo = (BaseClass) cls.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        assertNotNull(foo);
    }

    /**
     * This FAILS!
     */
    @Test
    @Disabled
    void loadingInThreadNormallyInLib() throws ExecutionException, InterruptedException {
        CompletableFuture.runAsync(this::inThreadImplNoraml).get();
    }


    private void inThreadImplCurrentThread() {
        Class<?> cls = ClassHelper.loadClassFromThread("test.TestImpl");

        BaseClass foo = null;
        try {
            foo = (BaseClass) cls.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        assertNotNull(foo);
    }

    /**
     * This FAILS <b>ONLY</b> on JDK 25!
     */
    @Test
    void loadingInThreadWithCurrentThreadInLib() throws ExecutionException, InterruptedException {
        CompletableFuture.runAsync(this::inThreadImplCurrentThread).get();
    }

}
