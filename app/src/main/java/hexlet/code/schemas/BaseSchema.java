package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {

    protected Map<String, Predicate<T>> conditions = new HashMap<>();

    public abstract BaseSchema<T> required();

    /**
     * After confirming that the schema has been configured,
     * you need to call the isValid() method on the configured schema and pass it the data to be validated.
     * The result will be a logical value true, if the data conforms to all the rules defined in the scheme,
     * or false, if it does not conform.
     * @param type - parametrized type of object
     * @return true if object is valid, false otherwise
     */
    public boolean isValid(T type) {
        return conditions.values()
                .stream()
                .allMatch(p -> p.test(type));
    }

    /**
     * Add Predicate to the conditions pool variable.
     * @param key Keyword that indicates the name of the configuration.
     * @param predicate Represents a predicate (boolean-valued function) of one argument.
     */
    protected void addPredicate(String key, Predicate<T> predicate) {
        conditions.put(key, predicate);
    }
}
