package cypher.tasktracker.testing.tests.runner.taskList;

import cypher.tasktracker.data.database.models.TaskModel;
import cypher.tasktracker.runner.core.UserInputManager;
import cypher.tasktracker.runner.taskList.TaskListExecutionEnum;
import cypher.tasktracker.runner.taskList.TaskListExecutor;
import cypher.tasktracker.services.core.ITaskService;
import cypher.tasktracker.testing.utils.mocks.TaskMockBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TaskListExecutionTest {

    @Mock
    private ITaskService taskService;

    @Mock
    private UserInputManager userInputManager;


    @InjectMocks
    private TaskListExecutor taskListExecutor;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void ShouldFindAll() {
        var taskList = List.of(TaskMockBuilder.build());
        when(taskService.findAll()).thenReturn(List.of(TaskMockBuilder.build()));
        taskListExecutor.execute();

        verify(taskService).findAll();
    }

    @Test
    void ShouldFindDone() {
        Map<TaskListExecutionEnum, Supplier<List<TaskModel>>> callMap = Map.of(
                TaskListExecutionEnum.DONE, () -> verify(taskService).findAllDone(),
                TaskListExecutionEnum.TODO, () -> verify(taskService).findAllTodo(),
                TaskListExecutionEnum.IN_PROGRESS, () -> verify(taskService).findAllInProgress(),
                TaskListExecutionEnum.ALL, () -> verify(taskService).findAll()
        );

        callMap.forEach((key, value) -> {
            taskListExecutor.execute("list", key.toString());
            value.get();
        });
    }
}
