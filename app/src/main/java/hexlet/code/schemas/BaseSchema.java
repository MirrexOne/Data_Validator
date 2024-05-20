package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {

    protected Map<String, Predicate<T>> conditions = new HashMap<>();

    public abstract BaseSchema<T> required();

    public boolean isValid(T type) {
        return conditions.values()
                .stream()
                .allMatch(p -> p.test(type));
    }

    protected void addPredicate(String key, Predicate<T> predicate) {
        conditions.put(key, predicate);
    }
}
