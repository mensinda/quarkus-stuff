package base3.sls;

import base1.persistence.TupAbstractEntity;
import jakarta.persistence.Entity;

@Entity
public class StaplerTypReplica extends TupAbstractEntity {
    public StaplerTypReplica(String oid) {
        super(oid);
    }

    public StaplerTypReplica() {
    }
}
