package cypher.tasktracker.runner.TaskDeleteExecution;

import cypher.tasktracker.runner.core.AbstractTaskExecutor;
import cypher.tasktracker.services.ui.UserInputService;
import org.springframework.stereotype.Component;

@Component
public class TaskDeleteExecutor extends AbstractTaskExecutor {

    public TaskDeleteExecutor(final UserInputService userInputService) {
        super(userInputService);
    }

    @Override
    public void execute() {

    }
}
