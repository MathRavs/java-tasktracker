package cypher.tasktracker.testing.tests.validation.dto;

import cypher.tasktracker.testing.utils.core.StringUtils;
import cypher.tasktracker.validation.dto.UpdateTaskDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UpdateTaskDTOTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void TestGivenIdIsCorrect() {
        var updateTaskDTOTest = new UpdateTaskDTO("1");

        Set<ConstraintViolation<UpdateTaskDTO>> violations = validator.validate(updateTaskDTOTest);

        assertTrue(violations.isEmpty());

        assertNotNull(updateTaskDTOTest.getId());
    }

    @Test
    void TestGivenNameIsIncorrect() {
        var updateTaskDTOTest = new UpdateTaskDTO("1");
        updateTaskDTOTest.setNewName(StringUtils.buildTestString(201));
        Set<ConstraintViolation<UpdateTaskDTO>> violations = validator.validate(updateTaskDTOTest);
        assertFalse(violations.isEmpty());

        updateTaskDTOTest.setNewName("");
        violations = validator.validate(updateTaskDTOTest);
        assertFalse(violations.isEmpty());
    }

    @Test
    void TestGivenNameIsCorrect() {
        var updateTaskDTOTest = new UpdateTaskDTO("1");
        updateTaskDTOTest.setNewName(null);
        Set<ConstraintViolation<UpdateTaskDTO>> violations = validator.validate(updateTaskDTOTest);
        assertTrue(violations.isEmpty());
    }

    @Test
    void TestIsFinishedIsCorrect() {
        var updateTaskDTOTest = new UpdateTaskDTO("1");
        updateTaskDTOTest.setFinished(true);
        Set<ConstraintViolation<UpdateTaskDTO>> violations = validator.validate(updateTaskDTOTest);
        assertTrue(violations.isEmpty());

        updateTaskDTOTest.setFinished(null);
        violations = validator.validate(updateTaskDTOTest);
        assertTrue(violations.isEmpty());
    }

    @Test
    void TestProgressIsIncorrect() {
        var updateTaskDTOTest = new UpdateTaskDTO("1");
        assertThrows(NumberFormatException.class, () -> {
            updateTaskDTOTest.setProgress("test");
        });

        updateTaskDTOTest.setProgress("101");
        Set<ConstraintViolation<UpdateTaskDTO>> violations = validator.validate(updateTaskDTOTest);
        assertFalse(violations.isEmpty());
    }

    @Test
    void TestProgressIsCorrect() {
        var updateTaskDTOTest = new UpdateTaskDTO("1");
        assertDoesNotThrow(() -> {
            updateTaskDTOTest.setProgress("100");
        });
        Set<ConstraintViolation<UpdateTaskDTO>> violations = validator.validate(updateTaskDTOTest);
        assertTrue(violations.isEmpty());
    }
}
