package cypher.tasktracker.runner.TaskListExecution;

import cypher.tasktracker.runner.core.AbstractTaskExecutor;
import cypher.tasktracker.services.data.TaskService;
import cypher.tasktracker.services.ui.UserInputService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;


@Component
public class TaskListExecutor extends AbstractTaskExecutor {

    private static Logger LOG = LoggerFactory
            .getLogger(TaskListExecutor.class);

    private final TaskService taskService;

    protected TaskListExecutor(final UserInputService userInputService, final TaskService taskService) {
        super(userInputService);
        this.taskService = taskService;
    }

    @Override
    public void execute() {
        var tasks = taskService.findAll();

        if (tasks.size() == 0) {
            LOG.info("No tasks");
        } else {
            IntStream.range(0, tasks.size())
                    .forEach(i -> LOG.info(i + "." + tasks.get(i)));
        }
    }
}
