package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

public class Validator {
    public static void main(String[] args) {

    }

    StringSchema string() {
        return new StringSchema();
    }

    NumberSchema number() {
        return new NumberSchema();
    }
}
