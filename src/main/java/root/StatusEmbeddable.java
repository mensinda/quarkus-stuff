package root;

import jakarta.persistence.Embeddable;

@Embeddable
public class StatusEmbeddable<T extends StatusInterface> {

    public int code;
    public String name;

    public static <T extends StatusInterface> StatusEmbeddable<T> create(T status) {
        StatusEmbeddable<T> res = new StatusEmbeddable<>();
        res.code = status.code();
        res.name = status.name();
        return res;
    }
}
