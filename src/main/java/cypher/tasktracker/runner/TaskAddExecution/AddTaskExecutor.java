package cypher.tasktracker.runner.TaskAddExecution;

import cypher.tasktracker.runner.TaskListExecution.TaskListExecutor;
import cypher.tasktracker.runner.core.AbstractTaskExecutor;
import cypher.tasktracker.services.data.TaskService;
import cypher.tasktracker.services.ui.UserInputService;
import cypher.tasktracker.testing.validation.dto.AddTaskDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Set;


@Component
public class AddTaskExecutor extends AbstractTaskExecutor {


    private static Logger LOG = LoggerFactory
            .getLogger(AddTaskExecutor.class);

    private final TaskService taskService;
    private final TaskListExecutor taskListExecutor;

    private final Validator validator;

    public AddTaskExecutor(
            final UserInputService userInputService,
            final Validator validator,
            final TaskService taskService,
            final TaskListExecutor taskListExecutor
    ) {
        super(userInputService);
        this.validator = validator;
        this.taskService = taskService;
        this.taskListExecutor = taskListExecutor;
    }

    @Override
    public void execute(String... args) {

        boolean wasLastInputValid = true;


        while (true) {
            LOG.info(wasLastInputValid ? "input a name for the task" : "Please input a valid name for the task");

            String name = this.userInputService.getUserInput();
            AddTaskDTO addTaskDTO = new AddTaskDTO(name.trim());

            Set<ConstraintViolation<AddTaskDTO>> violations = validator.validate(addTaskDTO);

            // Step 5: Process the validation result
            if (!violations.isEmpty()) {
                for (ConstraintViolation<AddTaskDTO> violation : violations) {
                    LOG.info("The " + violation.getPropertyPath() + " " + violation.getMessage());
                }
                wasLastInputValid = false;
            } else {
                LOG.info("Adding the task");
                taskService.addTask(addTaskDTO);
                taskListExecutor.execute();
                break;
            }
        }

    }
}
