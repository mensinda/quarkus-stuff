package root;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;

@Entity
public class TestEntity extends GenericBaseEntity {

    @Id
    private long id;

    @Version
    @Column(columnDefinition = "NUMERIC(9) DEFAULT 0")
    private long version;

    private int counter;

    String ppState;

    public String publicState;

    protected TestEntity() {
        // For JPA
    }

    public TestEntity(long id) {
        this.id = id;
        this.counter = 0;
    }

    public void updateStateInEntity(String newVal) {
        this.state = newVal;
    }

    public void increment() {
        counter += 1;
    }

    public long id() {
        return id;
    }

    public int counter() {
        return counter;
    }

    public long version() {
        return version;
    }

    public String getPpState() {
        return ppState;
    }

    public void setPpState(String ppState) {
        this.ppState = ppState;
    }
}
