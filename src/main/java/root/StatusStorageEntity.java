package root;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class StatusStorageEntity {

    @Id
    long id;

    @Embedded
    private StatusEmbeddable<StatusEnum> embeddedStatus;

    private StatusStorageEntity() {
        // For JPQ
    }

    public StatusStorageEntity(long id, StatusEnum status) {
        this.id = id;
        this.embeddedStatus = StatusEmbeddable.create(status);
    }

}
