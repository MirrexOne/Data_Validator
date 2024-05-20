package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<?, ?>> {

    @Override
    public MapSchema required() {
        addPredicate("required", map -> map != null && map instanceof Map<?, ?>);
        return this;
    }

    public MapSchema sizeof(int requiredSize) {
        addPredicate("sizeof", map -> map.size() == requiredSize);
        return this;
    }

    public <T> MapSchema shape(Map<String, BaseSchema<T>> keySchemasPairs) {
        conditions.put("shape", map -> keySchemasPairs.entrySet().stream().allMatch(entry -> {
            String key = entry.getKey();
            BaseSchema<T> schema = entry.getValue();
            T s = (T) map.get(key);
            return s != null && schema.isValid(s);
        }));
        return this;
    }
}
