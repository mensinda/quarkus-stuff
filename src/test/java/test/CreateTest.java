package test;

import com.sun.el.ExpressionFactoryImpl;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.el.ExpressionFactory;
import jakarta.validation.Validation;
import org.junit.jupiter.api.Test;

@QuarkusTest
class CreateTest {

    /**
     * This works
     */
    @Test
    void create1() {
        new ExpressionFactoryImpl();
    }

    /**
     * This fails...
     */
    @Test
    void create2() {
        ExpressionFactory.newInstance();
    }

    /**
     * What I actually care about:
     */
    @Test
    void create3() {
        Validation.byDefaultProvider().configure().ignoreXmlConfiguration().buildValidatorFactory();
    }

}
