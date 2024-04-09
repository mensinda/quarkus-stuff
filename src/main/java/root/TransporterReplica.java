package root;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class TransporterReplica extends TupAbstractEntity {

    protected String id;
    protected String beschreibung;

    protected TransporterReplica(String oid) {
        super(oid);
    }

    protected TransporterReplica() {
    }

    public String id() {
        return this.id;
    }

    public String beschreibung() {
        return this.beschreibung;
    }
}
