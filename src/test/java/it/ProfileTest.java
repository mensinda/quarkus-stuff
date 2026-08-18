package it;

import data.GlobalSingleton;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@QuarkusTest
@TestProfile(ProfileTest.Profile.class)
public class ProfileTest {

    public static class Profile implements QuarkusTestProfile {
    }

    @BeforeEach
    public void setup() {
        GlobalSingleton.get().doStuff();
    }

    @Test
    void t1() {
        assertNotNull(new Object());
    }

    @Test
    void t2() {
        assertNull(null);
    }
}
