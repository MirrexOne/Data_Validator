package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ValidatorTest {

    @Test
    void testStringValidator() {
        Validator stringValidator = new Validator();
        StringSchema stringSchema = stringValidator.string();

        assertThat(stringSchema.isValid("")).isTrue();
        assertThat(stringSchema.isValid(null)).isTrue();

        stringSchema.required();

        assertThat(stringSchema.isValid(null)).isFalse();
        assertThat(stringSchema.isValid("")).isFalse();
        assertThat(stringSchema.isValid("what does the fox say")).isTrue();
        assertThat(stringSchema.isValid("hexlet")).isTrue();

        assertThat(stringSchema.contains("wh").isValid("what does the fox say")).isTrue(); // true
        assertThat(stringSchema.contains("what").isValid("what does the fox say")).isTrue(); // true
        assertThat(stringSchema.contains("whatthe").isValid("what does the fox say")).isFalse(); // false

        assertThat(stringSchema.isValid("what does the fox say")).isFalse();

        StringSchema string = stringValidator.string();
        assertThat(string.minLength(10).minLength(4).isValid("Hexlet")).isTrue();
    }

    @Test
    void testNumberValidator() {
        Validator numberValidator = new Validator();
        NumberSchema numberSchema = numberValidator.number();

        assertThat(numberSchema.isValid(5)).isTrue();
        assertThat(numberSchema.isValid(null)).isTrue();

        numberSchema.positive();

        assertThat(numberSchema.isValid(null)).isTrue();

        numberSchema.required();

        assertThat(numberSchema.isValid(null)).isFalse();
        assertThat(numberSchema.isValid(10)).isTrue();
        assertThat(numberSchema.isValid(-10)).isFalse();
        assertThat(numberSchema.isValid(0)).isFalse();

        numberSchema.range(5, 10);

        assertThat(numberSchema.isValid(5)).isTrue();
        assertThat(numberSchema.isValid(10)).isTrue();
        assertThat(numberSchema.isValid(4)).isFalse();
        assertThat(numberSchema.isValid(11)).isFalse();
    }

    @Test
    void testMapValidator() {
        Validator mapValidator = new Validator();
        MapSchema mapSchema = mapValidator.map();

        assertThat(mapSchema.isValid(null)).isTrue();

        mapSchema.required();

        assertThat(mapSchema.isValid(null)).isFalse();
        assertThat(mapSchema.isValid(new HashMap<>())).isFalse();

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");

        mapSchema.sizeof(2);

        assertThat(mapSchema.isValid(data)).isFalse();
        data.put("key2", "value2");
        assertThat(mapSchema.isValid(data)).isTrue();
    }

    @Test
    void testNestedMapValidator() {
        Validator validator = new Validator();
        MapSchema mapSchema = validator.map();

        Map<String, BaseSchema<String>> schemas = new HashMap<>();

        schemas.put("firstName", validator.string().required());
        schemas.put("lastName", validator.string().required().minLength(2));

        mapSchema.shape();

    }
}
