package root;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.time.Instant;

@Entity
public class TestEntityEmbed {

    @Id
    @Embedded
    private TimeScalePkOidDatumEmbed id;

    private String data;

    protected TestEntityEmbed() {
        // For JPA
    }

    public TestEntityEmbed(String oid, String data) {
        this.id = new TimeScalePkOidDatumEmbed(oid, Instant.now());
        this.data = data;
    }

    public Instant getDatum() {
        return id.datum();
    }

    public String getData() {
        return data;
    }

    public void updateData(String data) {
        this.data = data;
    }

    public String getOid() {
        return id.oid();
    }
}
