package root;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class TupAbstractEntity {
    @Id
    private String oid = null;


    @SuppressWarnings("this-escape")
    protected TupAbstractEntity() {
    }

    protected TupAbstractEntity(String oid) {
        this.oid = oid;
    }

    public String getOid() {
        return oid;
    }

}
