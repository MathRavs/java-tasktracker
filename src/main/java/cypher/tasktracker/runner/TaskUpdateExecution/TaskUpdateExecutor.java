package cypher.tasktracker.runner.TaskUpdateExecution;

import cypher.tasktracker.runner.core.AbstractTaskExecutor;
import cypher.tasktracker.services.UserInputService;
import org.springframework.stereotype.Component;


@Component
public class TaskUpdateExecutor extends AbstractTaskExecutor {

    protected TaskUpdateExecutor(final UserInputService userInputService) {
        super(userInputService);
    }

    @Override
    public void execute() {

    }
}
