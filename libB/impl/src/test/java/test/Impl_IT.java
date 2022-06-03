package test;

import a.api.AApi;
import b.api.BApi;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class Impl_IT {

    @Inject
    BApi aApi;

    @Test
    void doTest() {
        assertEquals("oracle=Hello World!", aApi.someStrGenerator());
    }

}
