package root;

public class CompositeIdClass {

    private String oid;
    private String myId;

    public CompositeIdClass(String oid, String myId) {
        this.oid = oid;
        this.myId = myId;
    }

    public CompositeIdClass() {
    }

    public String oid() {
        return oid;
    }

    public String myId() {
        return myId;
    }

}
