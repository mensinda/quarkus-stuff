package base3.sls;

import base1.persistence.TupAbstractEntity;
import jakarta.persistence.Entity;

@Entity
public class StaplerTyp extends TupAbstractEntity {
    public StaplerTyp(String oid) {
        super(oid);
    }

    public StaplerTyp() {
    }
}
