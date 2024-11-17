package cypher.tasktracker.validation.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteTaskDTOTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void TestGivenIdIsCorrect() {
        var deleteTaskDTO = new DeleteTaskDTO("1");

        Set<ConstraintViolation<DeleteTaskDTO>> violations = validator.validate(deleteTaskDTO);

        assertTrue(violations.isEmpty());
    }

    @Test
    void TestGivenIdIsIncorrect() {
        var deleteTaskDTO = new DeleteTaskDTO("at");

        Set<ConstraintViolation<DeleteTaskDTO>> violations = validator.validate(deleteTaskDTO);

        assertFalse(violations.isEmpty());
    }

}
