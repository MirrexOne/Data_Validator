package hexlet.code.schemas;

import java.util.Objects;

public class NumberSchema extends BaseSchema<Integer> {

    @Override
    public NumberSchema required() {
        addPredicate("required", Objects::nonNull);
        return this;
    }

    public NumberSchema positive() {
        addPredicate("positive", n -> n == null || n > 0);
        return this;
    }

    public NumberSchema range(int rangeStart, int rangeEnd) {
        addPredicate("range", n -> n >= rangeStart && n <= rangeEnd);
        return this;
    }
}
