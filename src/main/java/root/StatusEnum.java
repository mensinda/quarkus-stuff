package root;

public enum StatusEnum implements StatusInterface {
    OK(1), WARNING(2), ERROR(3);

    private final int code;

    StatusEnum(int code) {
        this.code = code;
    }

    @Override
    public int code() {
        return code;
    }
}
