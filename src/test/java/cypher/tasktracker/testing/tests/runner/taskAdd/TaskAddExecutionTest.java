package cypher.tasktracker.testing.tests.runner.taskAdd;

import cypher.tasktracker.runner.TaskListExecution.TaskListExecutor;
import cypher.tasktracker.runner.core.UserInputManager;
import cypher.tasktracker.runner.taskAdd.AddTaskExecutor;
import cypher.tasktracker.services.core.ITaskService;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TaskAddExecutionTest {

    @Mock
    private ITaskService taskService;

    @Mock
    private UserInputManager userInputManager;

    @Mock
    private TaskListExecutor taskListExecutor;

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private AddTaskExecutor taskAddExecutor;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        this.taskAddExecutor = new AddTaskExecutor(
                userInputManager,
                validator,
                taskService,
                taskListExecutor
        );
    }

    @Test
    void ShouldManageInvalidInputs() {

        taskAddExecutor.setMaxSteps(1);

        when(userInputManager.getUserInput()).thenReturn("");

        taskAddExecutor.execute();

        assertFalse(taskAddExecutor.wasLastInputValid());
    }

    @Test
    void ShouldManageValidInputs() {
        taskAddExecutor.setMaxSteps(1);
        var name = "test";

        when(userInputManager.getUserInput()).thenReturn(name);

        taskAddExecutor.execute();

        assertTrue(taskAddExecutor.wasLastInputValid());

        verify(taskListExecutor).execute();
        verify(taskService).addTask(argThat(arg -> arg.getName().equals(name)));
    }
}
