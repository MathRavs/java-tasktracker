package cypher.tasktracker.runner.TaskMenuExecution;

public enum TaskMenuExecutionEnum {
    LIST_ALL(1),
    LIST_DONE(2),
    LIST_ONGOING(3),
    LIST_TODO(4),
    ADD(5),
    DELETE(6),
    UPDATE(7),
    EXIT(8);

    private final int value;

    TaskMenuExecutionEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TaskMenuExecutionEnum fromValue(int value) {
        for (TaskMenuExecutionEnum day : TaskMenuExecutionEnum.values()) {
            if (day.getValue() == value) {
                return day;
            }
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }
}
