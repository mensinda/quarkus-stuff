package base1.sls.std;

import base1.sls.Stapler;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity(name = "StdStapler")
@Table(name = "STDSTAPLER")
@DiscriminatorValue("STA")
public class StdStaplerEntity extends Stapler {

    public StdStaplerEntity(String id, String beschreibung, String staplerTyp) {
        super("STA" + id);
        this.id = id;
        this.beschreibung = beschreibung;
        this.staplerTyp = staplerTyp;
    }

    protected StdStaplerEntity() {
        // JPA
    }

    @Override
    public void beschreibungAendern(String neueBeschreibung) {
        this.beschreibung = neueBeschreibung;
    }

}
