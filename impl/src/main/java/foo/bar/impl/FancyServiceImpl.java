package foo.bar.impl;

import foo.bar.api.AbstractFancyObj;
import foo.bar.api.FancyObjAccessor;
import foo.bar.api.FancyService;
import io.quarkus.arc.Unremovable;

import javax.enterprise.context.ApplicationScoped;

@Unremovable
@ApplicationScoped
public class FancyServiceImpl implements FancyService {
    @Override
    public void registerObj(AbstractFancyObj obj) {
        // ... do stuff

        // now we want to mark the object as registered
        // obj.markRegistered(); -- is package private
        FancyObjAccessor.invokeMarkRegistered(obj);
    }
}
