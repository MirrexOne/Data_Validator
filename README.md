<!-- ### Hexlet tests and linter status:
[![Actions Status](https://github.com/MirrexOne/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/MirrexOne/java-project-78/actions) -->

### Maintainability:
[![Project build check](https://github.com/MirrexOne/java-project-78/actions/workflows/main.yml/badge.svg)](https://github.com/MirrexOne/java-project-78/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/358d4c2285b0df0dda67/maintainability)](https://codeclimate.com/github/MirrexOne/java-project-78/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/358d4c2285b0df0dda67/test_coverage)](https://codeclimate.com/github/MirrexOne/java-project-78/test_coverage)

## Introduction to the project: Data Validator
*This project is a library for checking the correctness (validation) of data*

### The following data type objects are currently supported:
*String, Integer, Map and Map object with structure checking support*

### Library usage example:
- Create an object ```` Validator v = new Validator(); ````
- Use one of the three methods to create an object to validate a particular schema   
```var stringValidationObject = v.string()``` - creates an object of class ``StringSchema`` that works with strings  
```var numberValidationObject = v.number()``` - creates an object of class ``NumberSchema`` that works with numbers  
```var mapValidationObject = v.map()``` - creates an object of class ``MapSchema`` that works with objects of type Map 
- For objects of different classes, use different methods as “flags” to validate values (__further below__)   
- ```isValid(your_validation_object)``` - method, the same for objects of all classes, checks the validity of the passed data  

#### Methods(“flags”) for string validation ####
- ```stringValidationObject.required()``` - makes the data mandatory, i.e. an empty string or null cannot be transmitted
- ```stringValidationObject.minLength(Integer your_number)``` - enters the minimum length of the passed string, the string must be equal to or greater than
- ```stringValidationObject.contains(String your_substring)``` - the passed substring must be contained in the final validation object  

#### Methods(“flags”) for validating numbers ####
- ```numberValidationObject.required()``` - makes the data mandatory, i.e., null cannot be passed in
- ```numberValidationObject.positive()``` - imposes a restriction on the sign of the number - the number must be positive only
- ```numberValidationObject.range(Integer lower_boundary, Integer upper_boundary)``` - imposes restrictions on the range of the number - the number can only be within the range, including numbers on its boundaries.

#### Methods(“flags”) for validation of Map type objects ####
- ```mapValidationObject.required()``` - makes the data mandatory, i.e. null cannot be passed in
- ```mapValidationObject.sizeof(Integer your_size)``` - imposes a restriction on the size of an object of Map type - the number of key-value pairs must match the passed value 
- ```mapValidationObject.shape(Map<String, BaseSchema<String>> your_map_with_configured_validation_schemas)``` - used to define properties of an object of Map type and create a schema to validate their values. In the argument of this function is passed an object of type Map, where the values are objects of class ```BaseSchema<String>``` (you can say ``StringSchema``) - with configured additional checks. Then, when passing to the method ```isValid()``` checked objects of type Map, will be compared values by keys for compliance with the previously defined schemes. 
Below is an example of how this method works
```
var v = new Validator();
var schema = v.map();

Map<String, BaseSchema<String>> schemas = new HashMap<>(); // create an object to fill with schemas of checks

schemas.put(“firstName”, v.string().required()); // add checks
schemas.put(“lastName”, v.string().required().minLength(2));

Map<String, String> human1 = new HashMap<>();
human1.put(“firstName”, “John”);
human1.put(“lastName”, “Smith”);
// validate
schema.isValid(human1); // true

Map<String, String> human2 = new HashMap<>();
human3.put(“firstName”, “Anna”);
human3.put(“lastName”, “B”);
// validate
schema.isValid(human2); // false
```

#### “Flags” on objects accumulate ####
