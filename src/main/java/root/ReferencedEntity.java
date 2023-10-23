package root;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ReferencedEntity {

    @Id
    private long id;

    private String status;

    private ReferencedEntity() {
    }

    public ReferencedEntity(long id, String status) {
        this.id = id;
        this.status = status;
    }

    public String status() {
        return status;
    }
}
