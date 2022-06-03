package a.impl2;

import a.api.AApi;
import io.quarkus.arc.Unremovable;

import javax.enterprise.context.ApplicationScoped;

@Unremovable
@ApplicationScoped
public class AImpl2 implements AApi {
    @Override
    public String oracle() {
        return "Hello World!";
    }
}
