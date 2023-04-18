package root;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class GenericBaseEntity {

    protected String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
