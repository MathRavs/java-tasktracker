package cypher.tasktracker.runner.TaskDeleteExecution;

import cypher.tasktracker.runner.TaskListExecution.TaskListExecutor;
import cypher.tasktracker.runner.core.AbstractTaskExecutor;
import cypher.tasktracker.runner.utils.DisplayUtils;
import cypher.tasktracker.services.data.TaskService;
import cypher.tasktracker.services.ui.UserInputService;
import cypher.tasktracker.testing.validation.dto.DeleteTaskDTO;
import jakarta.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TaskDeleteExecutor extends AbstractTaskExecutor {

    private final Validator validator;
    private final TaskListExecutor taskListExecutor;
    private final TaskService taskService;

    private static Logger LOG = LoggerFactory
            .getLogger(TaskDeleteExecutor.class);

    public TaskDeleteExecutor(
            final UserInputService userInputService,
            final TaskListExecutor taskListExecutor,
            final TaskService taskService,
            final Validator validator
    ) {
        super(userInputService);
        this.taskListExecutor = taskListExecutor;
        this.validator = validator;
        this.taskService = taskService;
    }

    @Override
    public void execute(String... args) {
        if (taskService.countTasks() == 0) {
            LOG.info("There are no tasks to delete");
            return;
        }

        while (true) {
            LOG.info("Which task do you want to delete?");

            this.taskListExecutor.execute();

            var id = userInputService.getUserInput();

            var deleteTaskDTO = new DeleteTaskDTO(id);

            var isValid = DisplayUtils.displayValidationViolations(validator, deleteTaskDTO);

            if (isValid) {
                var task = taskService.findById(deleteTaskDTO.getId());
                if (task.isPresent()) {
                    taskService.deleteById(deleteTaskDTO.getId());
                    break;
                } else {
                    LOG.error("the task identified by " + id + " does not exist");
                }
            }
        }
    }
}
