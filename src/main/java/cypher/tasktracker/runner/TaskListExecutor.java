package cypher.tasktracker.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class TaskListExecutor implements ITaskExecutor{

    private static Logger LOG = LoggerFactory
            .getLogger(TaskListExecutor.class);

    private final AddTaskExecutor addTaskExecutor;
    private final Runnable exitCallback;
    private final Scanner scanner;

    private final String[] actionList = {"List tasks", "Add a task", "Delete a task", "Update a task", "Exit"};

    public TaskListExecutor(final Runnable exitCallback,final Scanner scanner) {
        this.exitCallback = exitCallback;
        this.addTaskExecutor = new AddTaskExecutor(scanner);
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        LOG.info("What do you want to do ? ");
        this.displayChoices(this.actionList);

        String action = scanner.nextLine();


        actionCase: switch (action) {
            case "1":
                LOG.info("The task list");
                break actionCase;
            case "2":
                LOG.info("The task name : ");
                break actionCase;
            case "3":
                LOG.info("give the id of the task you would like to delete");

                break actionCase;
            case "4":
                LOG.info("give the id of the task you would like to update");

                break actionCase;
            case "5":
                this.exitCallback.run();
                break;
            default:
                LOG.info("Invalid action ID , please choose one of these actions");
                break actionCase;
        }
    }

    private void displayChoices(String...choices){
        for(int i = 0; i < choices.length; i++){
            StringBuilder stringBuilder = new StringBuilder()
                    .append(i + 1)
                    .append(". ")
                    .append(choices[i]);

            LOG.info(stringBuilder.toString());
        }
    }
}
