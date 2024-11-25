package cypher.tasktracker.testing.tests.runner.taskDelete;

import cypher.tasktracker.runner.TaskListExecution.TaskListExecutor;
import cypher.tasktracker.runner.core.UserInputManager;
import cypher.tasktracker.runner.taskDelete.TaskDeleteExecutor;
import cypher.tasktracker.services.core.ITaskService;
import cypher.tasktracker.testing.utils.mocks.TaskMockBuilder;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class TaskDeleteExecutionTest {
    @Mock
    private UserInputManager userInputManager;

    @Mock
    private TaskListExecutor taskListExecutor;

    @Mock
    private ITaskService taskService;

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private TaskDeleteExecutor taskDeleteExecutor;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        taskDeleteExecutor = new TaskDeleteExecutor(
                userInputManager,
                taskListExecutor,
                taskService,
                validator
        );
    }


    @Test
    void ShouldSkipIfThereIsNoTasks() {
        var id = "1";
        when(taskService.countTasks()).thenReturn(0L);
        when(userInputManager.getUserInput()).thenReturn(id);
        taskDeleteExecutor.setMaxSteps(1);
        taskDeleteExecutor.execute();
        verify(taskService, never()).deleteById(any());
    }

    @Test
    void ShouldManageInvalidInput() {
        var id = "";
        when(userInputManager.getUserInput()).thenReturn(id);
        taskDeleteExecutor.setMaxSteps(1);
        taskDeleteExecutor.execute();
        verify(taskService, never()).deleteById(any());
    }

    @Test
    void ShouldDeleteByIdAndDisplayListWhenTheTaskExists() {
        var id = "1";
        when(taskService.countTasks()).thenReturn(1L);
        when(taskService.findById(Long.parseLong(id))).thenReturn(Optional.of(TaskMockBuilder.build()));
        when(userInputManager.getUserInput()).thenReturn(id);
        taskDeleteExecutor.setMaxSteps(1);
        taskDeleteExecutor.execute();
        verify(taskService).deleteById(Long.parseLong(id));
    }
}
