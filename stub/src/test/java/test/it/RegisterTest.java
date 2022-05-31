package test.it;

import foo.bar.api.DummyFancyObjAccessor;
import foo.bar.api.FancyService;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import test.objects.MyFancyObject;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
class RegisterTest {

    @Inject
    FancyService service;

    @Test
    void doTest() {
        MyFancyObject obj = new MyFancyObject();

        assertFalse(obj.isRegistered());
        service.registerObj(obj);
        assertTrue(obj.isRegistered());
    }

    @Test
    void doDummyTest() {
        MyFancyObject obj = new MyFancyObject();

        assertFalse(obj.isRegistered());
        DummyFancyObjAccessor.invokeMarkRegistered(obj);
        assertTrue(obj.isRegistered());
    }

}
