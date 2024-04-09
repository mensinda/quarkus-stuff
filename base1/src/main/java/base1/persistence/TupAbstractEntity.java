package base1.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Version;

import java.time.Instant;

@MappedSuperclass
public abstract class TupAbstractEntity {
    @Id
    @Column(name = "OID", length = 32, nullable = false)
    private String oid = null;

    @Version
    @Column(columnDefinition = "NUMERIC(9) DEFAULT 0")
    private long tanum;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Instant created = Instant.now();

    protected TupAbstractEntity(String oid) {
        this.oid = oid;
    }

    protected TupAbstractEntity() {
    }

    public String getOid() {
        return oid;
    }

    public long getTanum() {
        return tanum;
    }

    public Instant getCreated() {
        return created;
    }
}
