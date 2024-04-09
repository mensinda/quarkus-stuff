package base2.api.sls;

import base1.tv.Transporter;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Stapler extends Transporter {
    protected String staplerTyp;

    protected Stapler(String oid) {
        super(oid);
    }

    protected Stapler() {
    }

    public abstract void beschreibungAendern(String neueBeschreibung);

    public static class TransportQueueImp {
    }
}
