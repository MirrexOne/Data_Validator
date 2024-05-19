package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema<Map<String, String>> {

    @Override
    public <MapScheme> MapScheme required() {
        Predicate<Map<String, String>> doesInitialized = map -> map != null && !map.isEmpty();
        predicatesMap.put("required", doesInitialized);
        return (MapScheme) this;
    }

    @Override
    public boolean isValid(Map<String, String> type) {
        return predicatesMap.values()
                .stream()
                .allMatch(map -> map.test(type));
    }

    public MapSchema sizeof(int requiredSize) {
        Predicate<Map<String, String>> isSizeSufficient = map -> map.size() == requiredSize;
        predicatesMap.put("sizeof", isSizeSufficient);
        return this;
    }
}
