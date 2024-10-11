package root;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * Composite primary key class for oid and datum
 */
public class TimeScalePkOidDatum implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String oid;
    private Instant datum;

    public TimeScalePkOidDatum() {
        // Default constructor
    }

    public TimeScalePkOidDatum(String oid, Instant datum) {
        this.oid = oid;
        this.datum = datum;
    }

    public String oid() {
        return this.oid;
    }

    public Instant datum() {
        return this.datum;
    }

    @Override
    public String
    toString() {
        final StringBuilder sb = new StringBuilder("TimeScalePkOidDatum{");
        sb.append("oid='").append(oid).append('\'');
        sb.append(", datum=").append(datum);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimeScalePkOidDatum that)) return false;
        return Objects.equals(oid, that.oid) && Objects.equals(datum, that.datum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(oid, datum);
    }
}
