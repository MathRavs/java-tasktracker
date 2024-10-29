package cypher.tasktracker.runner.TaskMenuExecution;

import cypher.tasktracker.runner.TaskAddExecution.AddTaskExecutor;
import cypher.tasktracker.runner.TaskDeleteExecution.TaskDeleteExecutor;
import cypher.tasktracker.runner.TaskListExecution.TaskListExecutor;
import cypher.tasktracker.runner.TaskUpdateExecution.TaskUpdateExecutor;
import cypher.tasktracker.runner.core.AbstractTaskExecutor;
import cypher.tasktracker.runner.utils.DisplayUtils;
import cypher.tasktracker.services.data.TaskService;
import cypher.tasktracker.services.ui.UserInputService;
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


    private final String[] actionList = {"List tasks", "Add a task", "Delete a task", "Update a task", "Exit"};
    private final TaskService taskService;

    public TaskMenuExecutor(
            final UserInputService userInputService,
            final AddTaskExecutor addTaskExecutor,
            final TaskDeleteExecutor taskDeleteExecutor,
            final TaskUpdateExecutor taskUpdateExecutor,
            final TaskListExecutor taskListExecutor,
            final ConfigurableApplicationContext configurableApplicationContext,
            final TaskService taskService) {
        super(userInputService);
        this.addTaskExecutor = addTaskExecutor;
        this.taskDeleteExecutor = taskDeleteExecutor;
        this.taskUpdateExecutor = taskUpdateExecutor;
        this.taskListExecutor = taskListExecutor;
        this.context = configurableApplicationContext;
        this.taskService = taskService;
    }

    @Override
    public void execute() {
        LOG.info("What do you want to do ? ");

        DisplayUtils.displayChoices(this.actionList);

        String action = this.userInputService.getUserInput();

        HashMap<String, Runnable> actionsMap = new HashMap<>();

        actionsMap.put("1", taskListExecutor::execute);
        actionsMap.put("2", addTaskExecutor::execute);
        actionsMap.put("3", taskDeleteExecutor::execute);
        actionsMap.put("4", taskUpdateExecutor::execute);
        actionsMap.put("5", () -> SpringApplication.exit(context, () -> 0));

        if (actionsMap.containsKey(action)) {
            actionsMap.get(action).run();
        } else {
            LOG.info("Invalid action ID");
        }
    }
}
