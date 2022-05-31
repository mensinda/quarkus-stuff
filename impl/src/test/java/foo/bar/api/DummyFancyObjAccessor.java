package foo.bar.api;

public final class DummyFancyObjAccessor {
    private DummyFancyObjAccessor() {}

    public static void invokeMarkRegistered(AbstractFancyObj obj) {
        obj.markRegistered();
    }
}
