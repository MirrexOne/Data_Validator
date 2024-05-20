package hexlet.code.schemas;

import java.util.Objects;

public class NumberSchema extends BaseSchema<Integer> {

    /**
     * Adds a constraint to the schema
     * that prevents null from being used as a value.
     * @return current object
     */
    @Override
    public NumberSchema required() {
        addPredicate("required", Objects::nonNull);
        return this;
    }

    /**
     * Adds a restriction on the sign of the number.
     * The number must be positive.
     * @return current object
     */
    public NumberSchema positive() {
        addPredicate("positive", n -> n == null || n > 0);
        return this;
    }

    /**
     * Adds a valid range within which the value of the number must fall, including bounds.
     * @param rangeStart lower bound - inclusive
     * @param rangeEnd upper bound - inclusive
     * @return current object
     */
    public NumberSchema range(int rangeStart, int rangeEnd) {
        addPredicate("range", n -> n >= rangeStart && n <= rangeEnd);
        return this;
    }
}
