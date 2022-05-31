package foo.bar.api;

public final class FancyObjAccessor {
    private FancyObjAccessor() {}

    public static void invokeMarkRegistered(AbstractFancyObj obj) {
        obj.markRegistered();
    }
}
