package dummyImpl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.jetbrains.annotations.NotNull;
import tup.std.mid.persistence.api.TupAbstractEntity;
import tup.std.mid.persistence.api.TupInternalApi;

import java.util.List;

@ApplicationScoped
public class TupInternalImpl extends TupAbstractEntity.___$_INTERNAL_FIELD_ACCESSOR_$$$ implements TupInternalApi {
    @Inject
    EntityManager em;

    @Override
    public void persist(@NotNull TupAbstractEntity entity) {
        invoke_onObjectCreated(entity);
        em.persist(entity);
    }

    @Override
    public EntityManager persistenceManager() {
        return em;
    }

    @Override
    public EntityMetadata metadata4(Class<? extends TupAbstractEntity> cls) {
        return null;
    }

    @Override
    public List<EntityMetadata> allMetadata() {
        return List.of();
    }
}
