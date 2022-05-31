package foo.bar.api;

import java.util.UUID;

public abstract class AbstractFancyObj {

    private final String id;
    private boolean registered;

    protected AbstractFancyObj() {
        id = UUID.randomUUID().toString();
        registered = false;
    }


    public String getId() {
        return id;
    }

    public boolean isRegistered() {
        return registered;
    }

    // pancake private can not be changed for reasons...
    void markRegistered() {
        registered = true;
    }

    public abstract void doStuff();
}
