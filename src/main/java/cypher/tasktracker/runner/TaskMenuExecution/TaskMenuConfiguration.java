package cypher.tasktracker.runner.TaskMenuExecution;

import java.util.Scanner;

public record TaskMenuConfiguration(
        Runnable exitCallback,
        Runnable addTaskCallback,
        Runnable updateTaskCallback,
        Runnable deleteTaskCallback,
        Runnable listTaskCallback,
        Scanner scanner
) { }
