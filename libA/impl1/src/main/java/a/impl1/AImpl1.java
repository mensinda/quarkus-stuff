package a.impl1;

import a.api.AApi;
import io.quarkus.arc.Unremovable;

import javax.enterprise.context.ApplicationScoped;

@Unremovable
@ApplicationScoped
public class AImpl1 implements AApi {
    @Override
    public String oracle() {
        return "42";
    }
}
