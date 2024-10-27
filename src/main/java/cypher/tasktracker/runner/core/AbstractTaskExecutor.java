package cypher.tasktracker.runner.core;

import cypher.tasktracker.services.ui.UserInputService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractTaskExecutor {
    protected final UserInputService userInputService;

    @Autowired
    protected AbstractTaskExecutor(final UserInputService userInputService) {
        this.userInputService = userInputService;
    }

    protected abstract void execute();
}
