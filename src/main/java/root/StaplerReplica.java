package root;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Transient;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class StaplerReplica extends TransporterReplica {
    @Transient
    protected TransportQueueImp transportQueueImp;

    protected TupSpiReference<StaplerTypReplica> staplerTyp;

    protected StaplerReplica(String oid) {
        super(oid);
    }

    protected StaplerReplica() {
    }

    public StaplerTypReplica staplerTyp() {
        return staplerTyp == null ? null : staplerTyp.entity();
    }

    public abstract void beschreibungAendern(String neueBeschreibung);

    public static class TransportQueueImp {
    }
}
