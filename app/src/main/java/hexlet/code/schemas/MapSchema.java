package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<?, ?>> {

    /**
     * Adds a constraint to the schema that prevents null from being used as a value.
     * The Map data type is required.
     * @return current object
     */
    @Override
    public MapSchema required() {
        addPredicate("required", map -> map != null && map instanceof Map<?, ?>);
        return this;
    }

    /**
     * Adds a restriction on the size of the map.
     * The number of key-value pairs in the Map object must be equal to the specified number of key-value pairs.
     * @param requiredSize
     * @return current object
     */
    public MapSchema sizeof(int requiredSize) {
        addPredicate("sizeof", map -> map.size() == requiredSize);
        return this;
    }

    /**
     * The shape() method is used to define Map object properties and create a schema to validate their values.
     * Each Map object property is bound to its own set of constraints (its own schema),
     * which allows to control the data more precisely.
     * @param keySchemasPairs Pair of specified keys and schemas.
     * @return current object
     * @param <T> - Parametrized type of object.
     */
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
