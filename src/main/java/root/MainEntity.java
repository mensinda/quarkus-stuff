package root;

import jakarta.persistence.*;

@Entity
public class MainEntity {
    @Id
    private long id;

    @OneToOne(targetEntity = ReferencedEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "LEHRL")
    private ReferencedEntity referenced;

    protected MainEntity() {
    }

    public MainEntity(long id, ReferencedEntity referenced) {
        this.id = id;
        this.referenced = referenced;
    }

    public long id() {
        return id;
    }

    public ReferencedEntity getReferenced() {
        return referenced;
    }

    public void setReferenced(ReferencedEntity referenced) {
        this.referenced = referenced;
    }
}
