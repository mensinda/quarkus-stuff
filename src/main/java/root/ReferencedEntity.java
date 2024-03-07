package root;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;

@Entity
public class ReferencedEntity {

    @Id
    private long id;

    @Version
    @Column(columnDefinition = "NUMERIC(9) DEFAULT 0")
    private long tanum;

    private String status;

    protected ReferencedEntity() {
    }

    public ReferencedEntity(long id, String status) {
        this.id = id;
        this.status = status;
    }

    public String status() {
        return status;
    }
}
