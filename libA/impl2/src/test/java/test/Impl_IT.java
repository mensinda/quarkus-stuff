package test;

import a.api.AApi;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class Impl_IT {

    @Inject
    AApi aApi;

    @Test
    void doTest() {
        assertEquals("Hello World!", aApi.oracle());
    }

}
