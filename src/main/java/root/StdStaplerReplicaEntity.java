package root;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity(name = "StdStaplerReplica")
@Table(name = "STDSTAPLERREPLICA")
@DiscriminatorValue("STA")
public class StdStaplerReplicaEntity extends StaplerReplica {

    public StdStaplerReplicaEntity(String id, String beschreibung, StaplerTypReplica staplerTyp) {
        super("STA" + id);
        this.id = id;
        this.beschreibung = beschreibung;
        this.staplerTyp = TupSpiReference.create(staplerTyp);
    }

    protected StdStaplerReplicaEntity() {
        // JPA
    }

    @Override
    public void beschreibungAendern(String neueBeschreibung) {
        this.beschreibung = neueBeschreibung;
    }

}
