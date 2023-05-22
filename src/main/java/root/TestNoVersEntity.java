package root;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TestNoVersEntity {

    @Id
    private long id;

    protected TestNoVersEntity() {
        // For JPA
    }

    public TestNoVersEntity(long id) {
        this.id = id;
    }

    public long id() {
        return id;
    }
}
