package hexlet.code.schemas;

import java.util.Objects;
import java.util.function.Predicate;

public class NumberSchema extends BaseSchema<Integer> {

    @Override
    public <NumberScheme> NumberScheme required() {
        Predicate<Integer> isNotNull = Objects::nonNull;
        predicatesMap.put("required", isNotNull);
        return (NumberScheme) this;
    }

    @Override
    public boolean isValid(Integer type) {
        return predicatesMap.values().stream()
                .allMatch(p -> p.test(type));
    }

    public NumberSchema positive() {
        Predicate<Integer> isPositive = n -> n == null || n > 0;
        predicatesMap.put("positive", isPositive);
        return this;
    }

    public NumberSchema range(int rangeStart, int rangeEnd) {
        Predicate<Integer> isInRange = n -> n >= rangeStart && n <= rangeEnd;
        predicatesMap.put("range", isInRange);
        return this;
    }

}
