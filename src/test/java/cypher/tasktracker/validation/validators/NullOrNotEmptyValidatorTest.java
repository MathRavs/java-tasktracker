package cypher.tasktracker.validation.validators;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

record User(@NullOrNotEmpty String username) {
}

public class NullOrNotEmptyValidatorTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test()
    void TestAnnotationsUsernameIsCorrect() {
        User user = new User("test");

        // use the validator to verify that the user instance is correct
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        // with the return value you can verify if the username was the correct length
        assertTrue(violations.isEmpty());

        user = new User(null);

        // use the validator to verify that the user instance is correct
        violations = validator.validate(user);

        // with the return value you can verify if the username was the correct length
        assertTrue(violations.isEmpty());
    }

    @Test()
    void TestAnnotationsUsernameIsIncorrect() {
        User user2 = new User("");

        // use the validator to verify that the user instance is correct
        Set<ConstraintViolation<User>> violations = validator.validate(user2);

        // with the return value you can verify if the username was the correct length
        assertFalse(violations.isEmpty());
    }
}
