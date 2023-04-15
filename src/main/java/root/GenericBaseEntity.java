package root;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class GenericBaseEntity<T extends EnumBase> {

    @Enumerated(EnumType.STRING)
    protected T state;

    public T getState() {
        return state;
    }

    public void setState(T state) {
        this.state = state;
    }
}
