package root;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Version;

@Entity
public class MainEntity {
    @Id
    private long id;

    @Version
    @Column(columnDefinition = "NUMERIC(9) DEFAULT 0")
    private long tanum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LEHRL")
    private ReferencedEntity referenced;

    protected MainEntity() {
    }

    public MainEntity(long id, ReferencedEntity referenced) {
        this.id = id;
        this.referenced = referenced;
    }
}
