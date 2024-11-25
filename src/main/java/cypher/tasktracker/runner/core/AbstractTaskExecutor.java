package cypher.tasktracker.runner.core;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractTaskExecutor {
    protected final UserInputManager userInputManager;

    protected Integer maxSteps = null;
    protected Integer currentSteps = 0;

    @Autowired
    protected AbstractTaskExecutor(final UserInputManager userInputManager) {
        this.userInputManager = userInputManager;
    }

    protected abstract void execute(String... args) throws InterruptedException;

    protected boolean shouldKeepRunning() {
        if (this.maxSteps == null) {
            return true;
        }
        return this.currentSteps < this.maxSteps;
    }

    public void setMaxSteps(int maxSteps) {
        this.maxSteps = maxSteps;
    }

    public void setCurrentSteps(Integer currentSteps) {
        this.currentSteps = currentSteps;
    }

    protected void incrementCurrentStep() {
        this.currentSteps += 1;
    }

    public void clearMaxSteps() {
        this.maxSteps = null;
    }
}
