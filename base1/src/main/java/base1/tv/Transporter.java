package base1.tv;

import base1.persistence.TupAbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Transporter extends TupAbstractEntity {

    protected String id;
    protected String beschreibung;

    protected Transporter(String oid) {
        super(oid);
    }

    protected Transporter() {
    }

    public String id() {
        return this.id;
    }

    public String beschreibung() {
        return this.beschreibung;
    }
}
