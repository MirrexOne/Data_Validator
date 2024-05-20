package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

public class Validator {

    /**
     * Creates a StringSchema schema
     * for validating String data
     * @return new StringSchema object
     */
    public StringSchema string() {
        return new StringSchema();
    }

    /**
     * Creates NumberSchema
     * for validating Integer data
     * @return new NumberSchema object
     */
    public NumberSchema number() {
        return new NumberSchema();
    }

    /**
     * Creates MapSchema
     * for validating Map data
     * @return new MapSchema object
     */
    public MapSchema map() {
        return new MapSchema();
    }
}
