package cypher.tasktracker.testing.tests.runner.taskMenu;

import cypher.tasktracker.runner.TaskMenuExecution.TaskMenuExecutor;
import cypher.tasktracker.runner.TaskUpdateExecution.TaskUpdateExecutor;
import cypher.tasktracker.runner.core.UserInputManager;
import cypher.tasktracker.runner.taskAdd.AddTaskExecutor;
import cypher.tasktracker.runner.taskDelete.TaskDeleteExecutor;
import cypher.tasktracker.runner.taskList.TaskListExecutor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.ConfigurableApplicationContext;

public class TaskMenuExecutionTest {
    @Mock
    private AddTaskExecutor addTaskExecutor;

    @Mock
    private TaskDeleteExecutor taskDeleteExecutor;

    @Mock
    private TaskUpdateExecutor taskUpdateExecutor;

    @Mock
    private TaskListExecutor taskListExecutor;

    @Mock
    private ConfigurableApplicationContext configurableApplicationContext;

    @Mock
    private UserInputManager userInputManager;

    @InjectMocks
    private TaskMenuExecutor taskMenuExecutor;

}
