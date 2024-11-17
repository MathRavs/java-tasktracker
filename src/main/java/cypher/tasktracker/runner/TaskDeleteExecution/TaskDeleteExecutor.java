package cypher.tasktracker.runner.TaskDeleteExecution;

import cypher.tasktracker.runner.TaskListExecution.TaskListExecutor;
import cypher.tasktracker.runner.core.AbstractTaskExecutor;
import cypher.tasktracker.runner.core.UserInputManager;
import cypher.tasktracker.runner.utils.DisplayUtils;
import cypher.tasktracker.services.core.ITaskService;
import cypher.tasktracker.validation.dto.DeleteTaskDTO;
import jakarta.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TaskDeleteExecutor extends AbstractTaskExecutor {

    private final Validator validator;
    private final TaskListExecutor taskListExecutor;
    private final ITaskService taskService;

    private static Logger LOG = LoggerFactory
            .getLogger(TaskDeleteExecutor.class);

    public TaskDeleteExecutor(
            final UserInputManager userInputManager,
            final TaskListExecutor taskListExecutor,
            final ITaskService taskService,
            final Validator validator
    ) {
        super(userInputManager);
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

            var id = userInputManager.getUserInput();

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
