package root;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.time.Instant;

@Entity
@IdClass(TimeScalePkOidDatum.class)
public class TestEntity {

    @Id
    @Column(length = 32, nullable = false)
    private String oid = null;

    @Id
    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Instant datum;

    private String data;

    protected TestEntity() {
        // For JPA
    }

    public TestEntity(String oid, String data) {
        this.oid = oid;
        this.datum = Instant.now();
        this.data = data;
    }

    public Instant getDatum() {
        return datum;
    }

    public String getData() {
        return data;
    }

    public void updateData(String data) {
        this.data = data;
    }

    public String getOid() {
        return oid;
    }
}
