package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {

    public StringSchema minLength(int minLength) {
        addPredicate("minLength", s -> s.length() >= minLength);
        return this;
    }

    public StringSchema contains(String subString) {
        addPredicate("contains", s -> s.contains(subString));
        return this;
    }

    @Override
    public StringSchema required() {
        addPredicate("required", s -> s != null && !s.isEmpty());
        return this;
    }
}
