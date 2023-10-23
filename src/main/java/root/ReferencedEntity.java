package root;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ReferencedEntity {

    @Id
    private long id;

    private String status;

    private ReferencedEntity() {
        // For JPQ
    }

    public ReferencedEntity(long id, String status) {
        this.id = id;
        this.status = status;
    }

    public long id() {
        return id;
    }

    public String status() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
