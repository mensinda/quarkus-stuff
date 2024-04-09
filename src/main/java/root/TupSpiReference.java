package root;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

public record TupSpiReference<T extends TupAbstractEntity>(String oid) implements Serializable {
    private static final long serialVersionUID = 1L;

    @Contract("null -> null; !null -> !null")
    public static <U extends TupAbstractEntity> @Nullable TupSpiReference<U> create(@Nullable U entity) {
        return entity == null ? null : new TupSpiReference<>(entity.getOid());
    }

    @Contract("null -> null; !null -> !null")
    public static <U extends TupAbstractEntity> @Nullable TupSpiReference<U> create(@Nullable String oid) {
        return oid == null || oid.isBlank() ? null : new TupSpiReference<>(oid);
    }

    public T entity() {
        return null;
    }

    /**
     * Implementation detail
     * <p>
     * Do not move this class to the {@code tupstdmid-persistence-impl} since this will
     * break the liquibase diff generator.
     */
    @Converter(autoApply = true)
    public static class TupEntitySpiConverter implements AttributeConverter<TupSpiReference<TupAbstractEntity>, String> {
        @Override
        public String convertToDatabaseColumn(final TupSpiReference<TupAbstractEntity> entity) {
            if (entity != null) {
                return entity.oid();
            }
            return null;
        }

        @Override
        public TupSpiReference<TupAbstractEntity> convertToEntityAttribute(final String oid) {
            if (oid != null && !oid.isEmpty()) {
                return TupSpiReference.create(oid);
            }
            return null;
        }
    }
}
