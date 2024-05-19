package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {

    protected Map<String, Predicate<T>> predicatesMap = new HashMap<>();

    public abstract BaseSchema<T> required();

    public abstract boolean isValid(T type);
}
