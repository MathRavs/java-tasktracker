package cypher.tasktracker.runner.taskList;

public enum TaskListExecutionEnum {
    DONE("done"), TODO("todo"), IN_PROGRESS("in_progress"), ALL("ALL");

    private final String description;

    TaskListExecutionEnum(final String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
