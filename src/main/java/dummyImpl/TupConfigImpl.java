package dummyImpl;

import jakarta.enterprise.context.ApplicationScoped;
import tup.std.mid.config.api.TupConfigApi;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class TupConfigImpl implements TupConfigApi {
    @Override
    public <T> T getValue(String path, Class<T> cls) {
        return null;
    }

    @Override
    public <T> Optional<T> getOptValue(String path, Class<T> cls) {
        return Optional.empty();
    }

    @Override
    public List<String> allProperties(String prefix) {
        return List.of();
    }

    @Override
    public List<String> listProperties(String path) {
        return List.of();
    }

    @Override
    public Map<String, String> mapProperties(String path) {
        return Map.of();
    }

    @Override
    public <T> T readConfig(Class<T> cls) {
        return null;
    }
}
