package stub.mock;

import foo.bar.api.AbstractFancyObj;
import foo.bar.api.FancyObjAccessor;
import foo.bar.api.FancyService;
import io.quarkus.arc.Unremovable;

import javax.enterprise.context.ApplicationScoped;

@Unremovable
@ApplicationScoped
public class MockWorker implements FancyService {

    @Override
    public void registerObj(AbstractFancyObj obj) {
        FancyObjAccessor.invokeMarkRegistered(obj);
    }
}
