package foo.bar.api;

import foo.bar.api.AbstractFancyObj;

public final class DummyFancyObjAccessor {
    private DummyFancyObjAccessor() {}

    public static void invokeMarkRegistered(AbstractFancyObj obj) {
        obj.markRegistered();
    }
}
