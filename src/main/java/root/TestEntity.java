package root;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

@Entity
@IdClass(CompositeIdClass.class)
public class TestEntity extends TupAbstractEntity {

    @Id
    private String myId;

    protected TestEntity() {
        // for JPA
    }

    public TestEntity(String oid, String myId) {
        super(oid);
        this.myId = myId;
    }

    public String myId() {
        return myId;
    }

    public CompositeIdClass getCompositeId() {
        return new CompositeIdClass(getOid(), myId);
    }

}
