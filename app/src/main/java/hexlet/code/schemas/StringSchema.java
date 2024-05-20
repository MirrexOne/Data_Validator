package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {

    /**
     * Adds a minimum length restriction
     * for a string to the scheme.
     * @param minLength set minimum size of string
     * @return current object
     */
    public StringSchema minLength(int minLength) {
        addPredicate("minLength", s -> s.length() >= minLength);
        return this;
    }

    /**
     * Adds a string content constraint to the schema
     * The string must contain a certain substring.
     * @param subString set a specified substring
     * @return current object
     */
    public StringSchema contains(String subString) {
        addPredicate("contains", s -> s.contains(subString));
        return this;
    }

    /**
     * Makes the data mandatory.
     * In other words, it adds a constraint to the schema
     * that does not allow using null or empty string as a value.
     * @return current object
     */
    @Override
    public StringSchema required() {
        addPredicate("required", s -> s != null && !s.isEmpty());
        return this;
    }
}
