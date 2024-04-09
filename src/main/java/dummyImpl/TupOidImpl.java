package dummyImpl;

import jakarta.enterprise.context.ApplicationScoped;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tup.std.mid.persistence.api.TupAbstractEntity;
import tup.std.mid.persistence.api.TupOidApi;

import java.util.concurrent.atomic.AtomicInteger;

@ApplicationScoped
public class TupOidImpl implements TupOidApi {

    private final AtomicInteger counter = new AtomicInteger();

    @Override
    public String nextOid(@NotNull Class<? extends TupAbstractEntity> entityClass, @Nullable String staticSuffix) {
        return "XXX" + counter.getAndIncrement();
    }

    @Override
    public String oidPrefixOf(@NotNull Class<? extends TupAbstractEntity> entityClass) {
        return "XXX";
    }
}
