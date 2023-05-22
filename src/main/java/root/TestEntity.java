package root;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;

@Entity
public class TestEntity {

    @Id
    private long id;

    @Version
    @Column(columnDefinition = "NUMERIC(9) DEFAULT 0")
    private long version;

    protected TestEntity() {
        // For JPA
    }

    public TestEntity(long id) {
        this.id = id;
    }

    public long id() {
        return id;
    }

    public long version() {
        return version;
    }
}
