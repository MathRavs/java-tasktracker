package cypher.tasktracker.runner;


import java.util.Scanner;

public class AddTaskExecutor implements ITaskExecutor {
    private final Scanner scanner;

    public AddTaskExecutor(final Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void execute() {

    }
}
