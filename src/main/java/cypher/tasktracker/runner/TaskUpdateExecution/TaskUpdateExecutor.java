package cypher.tasktracker.runner.TaskUpdateExecution;

import cypher.tasktracker.runner.core.AbstractTaskExecutor;
import cypher.tasktracker.runner.core.UserInputManager;
import cypher.tasktracker.runner.taskList.TaskListExecutor;
import cypher.tasktracker.runner.utils.DisplayUtils;
import cypher.tasktracker.services.core.ITaskService;
import cypher.tasktracker.validation.dto.UpdateTaskDTO;
import jakarta.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;


@Component
public class TaskUpdateExecutor extends AbstractTaskExecutor {

    private static Logger LOG = LoggerFactory
            .getLogger(TaskUpdateExecutor.class);

    private final ITaskService taskService;
    private final Validator validator;
    private final TaskListExecutor taskListExecutor;

    private final String[] actionList = {"Update the status", "Update the name", "Update progression"};


    protected TaskUpdateExecutor(
            final UserInputManager userInputManager,
            final ITaskService taskService,
            final Validator validator,
            final TaskListExecutor taskListExecutor
    ) {
        super(userInputManager);
        this.taskService = taskService;
        this.validator = validator;
        this.taskListExecutor = taskListExecutor;
    }

    @Override
    public void execute(String... args) {
        if (taskService.countTasks() == 0) {
            LOG.error("There is actually not tasks to update ");
            return;
        }

        LOG.info("Which Task do you want to update ?");
        taskListExecutor.execute();

        String taskId = this.userInputManager.getUserInput();
        UpdateTaskDTO updateTaskDTO = new UpdateTaskDTO(taskId);

        var isValid = DisplayUtils.displayValidationViolations(validator, updateTaskDTO);

        if (!isValid) {
            LOG.info("invalid ID");
            return;
        }

        LOG.info("What do you want to update ? ");

        HashMap<String, Runnable> actionsMap = new HashMap<>();

        actionsMap.put("1", () -> updateStatus(updateTaskDTO));
        actionsMap.put("2", () -> updateName(updateTaskDTO));
        actionsMap.put("3", () -> updateProgression(updateTaskDTO));

        DisplayUtils.displayChoices(actionList);

        String choice = userInputManager.getUserInput();

        if (actionsMap.containsKey(choice)) {
            actionsMap.get(choice).run();
        } else {
            LOG.info("invalid action key");
        }
    }

    protected void updateStatus(final UpdateTaskDTO updateTaskDTO) {
        var display = new String[]{"Finished", "Not Finished", "Cancel"};

        while (true) {
            DisplayUtils.displayChoices(display);
            String choice = userInputManager.getUserInput();

            if (choice.equals("1")) {
                updateTaskDTO.setFinished(true);
                taskService.updateTask(updateTaskDTO);
                LOG.info(taskService.findById(updateTaskDTO.getId()).get().toString());
                break;
            } else if (choice.equals("2")) {
                updateTaskDTO.setFinished(false);
                taskService.updateTask(updateTaskDTO);
                LOG.info(taskService.findById(updateTaskDTO.getId()).get().toString());
                break;
            } else if (choice.equals("3")) {
                break;
            } else {
                LOG.info("invalid input");
            }
        }
    }

    protected void updateName(final UpdateTaskDTO updateTaskDTO) {
        while (true) {
            LOG.info("Enter the new name");
            String newName = userInputManager.getUserInput();
            updateTaskDTO.setNewName(newName);

            var isValid = DisplayUtils.displayValidationViolations(validator, updateTaskDTO);

            if (isValid) {
                taskService.updateTask(updateTaskDTO);
                LOG.info(taskService.findById(updateTaskDTO.getId()).get().toString());
                break;
            } else {
                LOG.error("the name is invalid , it should be a non empty string");
            }
        }
    }

    protected void updateProgression(final UpdateTaskDTO updateTaskDTO) {
        while (true) {
            LOG.info("Enter the new progression");
            String progress = userInputManager.getUserInput();

            try {
                updateTaskDTO.setProgress(progress);
            } catch (NumberFormatException numberFormatException) {
                LOG.error("invalid number");
                continue;
            }

            var isValid = DisplayUtils.displayValidationViolations(validator, updateTaskDTO);

            if (isValid) {
                taskService.updateTask(updateTaskDTO);
                LOG.info(taskService.findById(updateTaskDTO.getId()).get().toString());
                break;
            } else {
                LOG.error("the name is invalid , it should be a non empty string");
            }
        }
    }
}
