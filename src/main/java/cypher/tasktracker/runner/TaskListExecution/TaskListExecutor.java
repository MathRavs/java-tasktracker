package cypher.tasktracker.runner.TaskListExecution;

import cypher.tasktracker.data.database.models.TaskModel;
import cypher.tasktracker.display.TaskDisplay;
import cypher.tasktracker.runner.core.AbstractTaskExecutor;
import cypher.tasktracker.runner.core.ArgsManager;
import cypher.tasktracker.runner.core.UserInputManager;
import cypher.tasktracker.services.data.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;


@Component
public class TaskListExecutor extends AbstractTaskExecutor {

    private static Logger LOG = LoggerFactory
            .getLogger(TaskListExecutor.class);

    private final TaskService taskService;

    protected TaskListExecutor(final UserInputManager userInputManager, final TaskService taskService) {
        super(userInputManager);
        this.taskService = taskService;
    }

    @Override
    public void execute(String... args) {
        var argsManager = new ArgsManager(args);

        List<TaskModel> tasks = null;
        tasks = this.getTasksDependingOnArgs(argsManager);

        if (tasks.isEmpty()) {
            LOG.info("No tasks");
        } else {
            TaskDisplay.displayTasks(tasks);
        }
    }

    private List<TaskModel> getTasksDependingOnArgs(ArgsManager argsManager) {
        List<TaskModel> tasks = null;

        Map<TaskListExecutionEnum, Supplier<List<TaskModel>>> taskOptionMap = Map.of(
                TaskListExecutionEnum.DONE, taskService::findAllDone,
                TaskListExecutionEnum.TODO, taskService::findAllTodo,
                TaskListExecutionEnum.IN_PROGRESS, taskService::findAllInProgress,
                TaskListExecutionEnum.ALL, taskService::findAll
        );

        boolean hasTargetActionKey = argsManager.hasAdditionalActionKey() && taskOptionMap.containsKey(
                TaskListExecutionEnum.valueOf(argsManager.getArgs().get(1).toUpperCase())
        );

        if (hasTargetActionKey) {
            tasks = taskOptionMap.get(TaskListExecutionEnum.valueOf(argsManager.getArgs().get(1).toUpperCase())).get();
        } else {
            tasks = taskService.findAll();
        }
        return tasks;
    }
}
