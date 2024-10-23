package cypher.tasktracker.runner.TaskAddExecution;

import cypher.tasktracker.runner.core.AbstractTaskExecutor;
import cypher.tasktracker.validation.AddTaskDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;
import java.util.Set;

public class AddTaskExecutor extends AbstractTaskExecutor {

    private static Logger LOG = LoggerFactory
            .getLogger(AddTaskExecutor.class);

    public AddTaskExecutor(final Scanner scanner) {
        super(scanner);
    }

    @Override
    public void execute() {

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        boolean wasLastInputValid = true;

        while (true){
            LOG.info(wasLastInputValid ? "input a name for the task": "Please input a valid name for the task");

            String name = scanner.nextLine();
            AddTaskDTO addTaskDTO = new AddTaskDTO(name.trim());

            Set<ConstraintViolation<AddTaskDTO>> violations = validator.validate(addTaskDTO);

            // Step 5: Process the validation result
            if (!violations.isEmpty()) {
                for (ConstraintViolation<AddTaskDTO> violation : violations) {
                    LOG.info("The " + violation.getPropertyPath() + " " + violation.getMessage());
                }
                wasLastInputValid = false;
            } else {
                LOG.info("Adding the user");
                break;
            }
        }

    }
}
