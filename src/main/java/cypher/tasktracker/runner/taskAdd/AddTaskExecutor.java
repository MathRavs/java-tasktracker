package cypher.tasktracker.runner.taskAdd;

import cypher.tasktracker.runner.TaskListExecution.TaskListExecutor;
import cypher.tasktracker.runner.core.AbstractTaskExecutor;
import cypher.tasktracker.runner.core.UserInputManager;
import cypher.tasktracker.services.core.ITaskService;
import cypher.tasktracker.validation.dto.AddTaskDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Set;


@Component
public class AddTaskExecutor extends AbstractTaskExecutor {
    private final Object lock = new Object();
    private boolean wasLastInputValid = true;

    private static Logger LOG = LoggerFactory
            .getLogger(AddTaskExecutor.class);

    private final ITaskService taskService;
    private final TaskListExecutor taskListExecutor;

    private final Validator validator;

    public AddTaskExecutor(
            final UserInputManager userInputManager,
            final Validator validator,
            final ITaskService taskService,
            final TaskListExecutor taskListExecutor
    ) {
        super(userInputManager);
        this.validator = validator;
        this.taskService = taskService;
        this.taskListExecutor = taskListExecutor;
    }

    @Override
    public void execute(String... args) {
        wasLastInputValid = true;

        while (this.shouldKeepRunning()) {
            LOG.info(wasLastInputValid ? "input a name for the task" : "Please input a valid name for the task");

            String name = this.userInputManager.getUserInput();
            AddTaskDTO addTaskDTO = new AddTaskDTO(name.trim());

            Set<ConstraintViolation<AddTaskDTO>> violations = validator.validate(addTaskDTO);

            if (!violations.isEmpty()) {
                for (ConstraintViolation<AddTaskDTO> violation : violations) {
                    LOG.info("The " + violation.getPropertyPath() + " " + violation.getMessage());
                }
                wasLastInputValid = false;
                this.incrementCurrentStep();
            } else {
                LOG.info("Adding the task");
                taskService.addTask(addTaskDTO);
                taskListExecutor.execute();
                break;
            }
        }
    }


    public boolean wasLastInputValid() {
        return wasLastInputValid;
    }
}
