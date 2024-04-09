package base1;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Version;

import java.time.Instant;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Transporter {

    protected String id;
    protected String beschreibung;

    @Id
    @Column(name = "OID", length = 32, nullable = false)
    private String oid;

    protected Transporter(String oid) {
        this.oid = oid;
    }

    protected Transporter() {
    }

    public String id() {
        return this.id;
    }

    public String beschreibung() {
        return this.beschreibung;
    }

    public void transporterBeschreibungAendern(String neueBeschreibung) {
        this.beschreibung = neueBeschreibung;
    }
}
