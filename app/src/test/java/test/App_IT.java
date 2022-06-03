package test;

import app.App;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class App_IT {

    @Inject
    App app;

    @Test
    void doAppTest() {
        assertEquals("42 -- oracle=42", app.doStuff());
    }

}
