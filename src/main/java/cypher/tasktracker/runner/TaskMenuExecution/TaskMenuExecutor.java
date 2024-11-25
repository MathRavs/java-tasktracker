package cypher.tasktracker.runner.TaskMenuExecution;

import cypher.tasktracker.runner.TaskUpdateExecution.TaskUpdateExecutor;
import cypher.tasktracker.runner.core.AbstractTaskExecutor;
import cypher.tasktracker.runner.core.UserInputManager;
import cypher.tasktracker.runner.taskAdd.AddTaskExecutor;
import cypher.tasktracker.runner.taskDelete.TaskDeleteExecutor;
import cypher.tasktracker.runner.taskList.TaskListExecutionEnum;
import cypher.tasktracker.runner.taskList.TaskListExecutor;
import cypher.tasktracker.runner.utils.DisplayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class TaskMenuExecutor extends AbstractTaskExecutor {
    private static final Logger LOG = LoggerFactory
            .getLogger(TaskMenuExecutor.class);

    private AddTaskExecutor addTaskExecutor;
    private TaskDeleteExecutor taskDeleteExecutor;
    private TaskUpdateExecutor taskUpdateExecutor;
    private TaskListExecutor taskListExecutor;
    private ConfigurableApplicationContext context;
    private final Map<TaskMenuExecutionEnum, TaskMenuMetadata> actionList;

    public TaskMenuExecutor(
            final UserInputManager userInputManager,
            final AddTaskExecutor addTaskExecutor,
            final TaskDeleteExecutor taskDeleteExecutor,
            final TaskUpdateExecutor taskUpdateExecutor,
            final TaskListExecutor taskListExecutor,
            final ConfigurableApplicationContext configurableApplicationContext) {
        super(userInputManager);
        this.addTaskExecutor = addTaskExecutor;
        this.taskDeleteExecutor = taskDeleteExecutor;
        this.taskUpdateExecutor = taskUpdateExecutor;
        this.taskListExecutor = taskListExecutor;
        this.context = configurableApplicationContext;
    }

    {
        actionList = new LinkedHashMap<>() {{
            put(TaskMenuExecutionEnum.LIST_ALL, new TaskMenuMetadata("List all tasks", () -> taskListExecutor.execute()));
            put(TaskMenuExecutionEnum.LIST_DONE, new TaskMenuMetadata("List done tasks", () -> taskListExecutor.execute("list", TaskListExecutionEnum.DONE.getDescription())));
            put(TaskMenuExecutionEnum.LIST_ONGOING, new TaskMenuMetadata("List ongoing tasks", () -> taskListExecutor.execute("list", TaskListExecutionEnum.IN_PROGRESS.getDescription())));
            put(TaskMenuExecutionEnum.LIST_TODO, new TaskMenuMetadata("List todo tasks", () -> taskListExecutor.execute("list", TaskListExecutionEnum.TODO.getDescription())));
            put(TaskMenuExecutionEnum.ADD, new TaskMenuMetadata("Add a task", () -> addTaskExecutor.execute()));
            put(TaskMenuExecutionEnum.DELETE, new TaskMenuMetadata("Delete a task", () -> taskDeleteExecutor.execute()));
            put(TaskMenuExecutionEnum.UPDATE, new TaskMenuMetadata("Update a task", () -> taskUpdateExecutor.execute()));
            put(TaskMenuExecutionEnum.EXIT, new TaskMenuMetadata("Exit", () -> SpringApplication.exit(context, () -> 0)));
        }};
    }

    @Override
    public void execute(String... args) {
        LOG.info("What do you want to do ? ");

        String[] actionLabels = this.actionList.values().stream().map(TaskMenuMetadata::text).toArray(String[]::new);
        DisplayUtils.displayChoices(actionLabels);

        String action = this.userInputManager.getUserInput();

        try {
            TaskMenuExecutionEnum actionId = TaskMenuExecutionEnum.fromValue(Integer.parseInt(action));
            if (actionList.containsKey(actionId)) {
                actionList.get(actionId).runnable().run();
            } else {
                LOG.info("Invalid action ID");
            }
        } catch (NumberFormatException exception) {
            LOG.info("The action ID must be a valid number");
        } catch (IllegalArgumentException illegalArgumentException) {
            LOG.info("Invalid action ID");
        }
    }
}
