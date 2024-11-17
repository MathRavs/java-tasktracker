package cypher.tasktracker.testing.tests.dto;

import cypher.tasktracker.dto.AddTaskDTO;
import cypher.tasktracker.testing.utils.core.StringUtils;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddTaskDTOTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void TestGivenNameIsCorrect() {
        var addTaskDto = new AddTaskDTO("test");

        Set<ConstraintViolation<AddTaskDTO>> violations = validator.validate(addTaskDto);

        assertTrue(violations.isEmpty());
    }

    @Test
    void TestGivenNameIsIncorrect() {
        var addTaskDto = new AddTaskDTO(StringUtils.buildTestString(201));

        Set<ConstraintViolation<AddTaskDTO>> violations = validator.validate(addTaskDto);

        assertFalse(violations.isEmpty());
    }
}