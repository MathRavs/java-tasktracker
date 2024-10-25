package cypher.tasktracker.runner.TaskListExecution;

import cypher.tasktracker.runner.core.AbstractTaskExecutor;
import cypher.tasktracker.services.UserInputService;
import org.springframework.stereotype.Component;


@Component
public class TaskListExecutor extends AbstractTaskExecutor {

    protected TaskListExecutor(final UserInputService userInputService) {
        super(userInputService);
    }

    @Override
    public void execute() {

    }
}
