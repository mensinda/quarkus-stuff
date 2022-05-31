package foo.bar.api;

import javax.enterprise.inject.spi.CDI;

public interface FancyService {

    static FancyService get() {
        return CDI.current().select(FancyService.class).get();
    }

    void registerObj(AbstractFancyObj obj);

}
