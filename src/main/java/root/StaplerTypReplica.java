package root;

import jakarta.persistence.Entity;

@Entity
public class StaplerTypReplica extends TupAbstractEntity {
    public StaplerTypReplica(String oid) {
        super(oid);
    }

    public StaplerTypReplica() {
    }
}
