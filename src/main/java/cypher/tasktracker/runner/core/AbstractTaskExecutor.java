package cypher.tasktracker.runner.core;

import java.util.Scanner;

public abstract class AbstractTaskExecutor {
    protected Scanner scanner;

    protected AbstractTaskExecutor(final Scanner scanner){
        this.scanner = scanner;
    }

    protected abstract void execute();
}
