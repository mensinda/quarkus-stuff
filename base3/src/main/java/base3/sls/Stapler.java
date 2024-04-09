package base3.sls;

import base1.persistence.TupSpiReference;
import base2.api.Transporter;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Transient;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Stapler extends Transporter {
    @Transient
    protected TransportQueueImp transportQueueImp;

    protected TupSpiReference<StaplerTyp> staplerTyp;

    protected Stapler(String oid) {
        super(oid);
    }

    protected Stapler() {
    }

    public StaplerTyp staplerTyp() {
        return staplerTyp == null ? null : staplerTyp.entity();
    }

    public abstract void beschreibungAendern(String neueBeschreibung);

    public static class TransportQueueImp {
    }
}
