package it;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import root.foo.Foo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class DummyTest {

    private final Foo foo;

    @Inject
    public DummyTest(Foo foo) {
        this.foo = foo;
    }

    @Test
    void doStuff() {
        assertNotNull(foo);
    }
}
