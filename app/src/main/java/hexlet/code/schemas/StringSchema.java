package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class StringSchema {

    private final Map<String, Predicate<String>> predicatesMap = new HashMap<>();

    public StringSchema required() {
        Predicate<String> isNotNull = Objects::nonNull;
        Predicate<String> isNotEmpty = s -> !s.isEmpty();
        Predicate<String> notNullAndEmpty = isNotNull.and(isNotEmpty);
        predicatesMap.put("required", notNullAndEmpty);
        return this;
    }

    public StringSchema minLength(int minLength) {
        Predicate<String> isLessThan = s -> s.length() >= minLength;
        predicatesMap.put("minLength", isLessThan);
        return this;
    }

    public StringSchema contains(String subString) {
        Predicate<String> hasSubstring = s -> s.contains(subString);
        predicatesMap.put("contains", hasSubstring);
        return this;
    }

    public boolean isValid(String s) {
        return predicatesMap.values().stream()
                .allMatch(p -> p.test(s));

    }
}
