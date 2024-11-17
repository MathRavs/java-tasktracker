package cypher.tasktracker.runner.core;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractTaskExecutor {
    protected final UserInputManager userInputManager;

    @Autowired
    protected AbstractTaskExecutor(final UserInputManager userInputManager) {
        this.userInputManager = userInputManager;
    }

    protected abstract void execute(String... args);
}
