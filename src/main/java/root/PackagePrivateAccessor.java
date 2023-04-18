package root;

public final class PackagePrivateAccessor {
    private PackagePrivateAccessor() {
        // all static
    }

    public static void setState4(TestEntity entity, String ppState) {
        entity.ppState = ppState;
    }
}
