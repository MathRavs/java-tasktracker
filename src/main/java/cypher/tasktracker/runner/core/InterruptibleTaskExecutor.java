package cypher.tasktracker.runner.core;

public abstract class InterruptibleTaskExecutor {
    protected Integer maxSteps = null;
    protected Integer currentSteps = 0;

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
