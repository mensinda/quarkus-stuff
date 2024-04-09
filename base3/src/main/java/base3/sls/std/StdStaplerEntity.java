package base3.sls.std;

import base1.persistence.TupSpiReference;
import base3.sls.Stapler;
import base3.sls.StaplerTyp;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity(name = "StdStapler")
@Table(name = "STDSTAPLER")
@DiscriminatorValue("STA")
public class StdStaplerEntity extends Stapler {

    public StdStaplerEntity(String id, String beschreibung, StaplerTyp staplerTyp) {
        super("STA" + id);
        this.id = id;
        this.beschreibung = beschreibung;
        this.staplerTyp = TupSpiReference.create(staplerTyp);
    }

    protected StdStaplerEntity() {
        // JPA
    }

    @Override
    public void beschreibungAendern(String neueBeschreibung) {
        this.beschreibung = neueBeschreibung;
    }

}
