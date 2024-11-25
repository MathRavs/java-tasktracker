package cypher.tasktracker.runner.TaskMenuExecution;

import cypher.tasktracker.runner.TaskUpdateExecution.TaskUpdateExecutor;
import cypher.tasktracker.runner.core.AbstractTaskExecutor;
import cypher.tasktracker.runner.core.UserInputManager;
import cypher.tasktracker.runner.taskAdd.AddTaskExecutor;
import cypher.tasktracker.runner.taskDelete.TaskDeleteExecutor;
import cypher.tasktracker.runner.taskList.TaskListExecutionEnum;
import cypher.tasktracker.runner.taskList.TaskListExecutor;
import cypher.tasktracker.runner.utils.DisplayUtils;
import cypher.tasktracker.services.core.ITaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class TaskMenuExecutor extends AbstractTaskExecutor {

    private static Logger LOG = LoggerFactory
            .getLogger(TaskMenuExecutor.class);

    private final AddTaskExecutor addTaskExecutor;
    private final TaskDeleteExecutor taskDeleteExecutor;
    private final TaskUpdateExecutor taskUpdateExecutor;
    private final TaskListExecutor taskListExecutor;
    private final ConfigurableApplicationContext context;


    private final String[] actionList = {
            "List all tasks",
            "List done tasks",
            "List ongoing tasks",
            "List todo tasks",
            "Add a task",
            "Delete a task",
            "Update a task",
            "Exit"
    };
    private final ITaskService taskService;

    public TaskMenuExecutor(
            final UserInputManager userInputManager,
            final AddTaskExecutor addTaskExecutor,
            final TaskDeleteExecutor taskDeleteExecutor,
            final TaskUpdateExecutor taskUpdateExecutor,
            final TaskListExecutor taskListExecutor,
            final ConfigurableApplicationContext configurableApplicationContext,
            final ITaskService taskService) {
        super(userInputManager);
        this.addTaskExecutor = addTaskExecutor;
        this.taskDeleteExecutor = taskDeleteExecutor;
        this.taskUpdateExecutor = taskUpdateExecutor;
        this.taskListExecutor = taskListExecutor;
        this.context = configurableApplicationContext;
        this.taskService = taskService;
    }

    @Override
    public void execute(String... args) {
        LOG.info("What do you want to do ? ");

        DisplayUtils.displayChoices(this.actionList);

        String action = this.userInputManager.getUserInput();

        HashMap<String, Runnable> actionsMap = new HashMap<>();

        actionsMap.put("1", taskListExecutor::execute);
        actionsMap.put("2", () -> taskListExecutor.execute("list", TaskListExecutionEnum.DONE.getDescription()));
        actionsMap.put("3", () -> taskListExecutor.execute("list", TaskListExecutionEnum.IN_PROGRESS.getDescription()));
        actionsMap.put("4", () -> taskListExecutor.execute("list", TaskListExecutionEnum.TODO.getDescription()));
        actionsMap.put("5", () -> {
            try {
                addTaskExecutor.execute("list");
            } catch (Exception ignored) {
            }
        });
        actionsMap.put("6", taskDeleteExecutor::execute);
        actionsMap.put("7", taskUpdateExecutor::execute);
        actionsMap.put("8", () -> SpringApplication.exit(context, () -> 0));

        if (actionsMap.containsKey(action)) {
            actionsMap.get(action).run();
        } else {
            LOG.info("Invalid action ID");
        }
    }
}
