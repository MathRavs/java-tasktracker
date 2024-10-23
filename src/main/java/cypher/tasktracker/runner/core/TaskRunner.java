package cypher.tasktracker.runner.core;

import cypher.tasktracker.runner.TaskAddExecution.AddTaskExecutor;
import cypher.tasktracker.runner.TaskDeleteExecution.TaskDeleteExecutor;
import cypher.tasktracker.runner.TaskListExecution.TaskListExecutor;
import cypher.tasktracker.runner.TaskMenuExecution.TaskMenuConfiguration;
import cypher.tasktracker.runner.TaskMenuExecution.TaskMenuExecutor;
import cypher.tasktracker.runner.TaskUpdateExecution.TaskUpdateExecutor;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;


import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

@Component()
public class TaskRunner implements CommandLineRunner {

    private final ConfigurableApplicationContext context;
    private final AtomicBoolean running = new AtomicBoolean(true);

    private static Logger LOG = LoggerFactory
            .getLogger(TaskRunner.class);

    public TaskRunner(ConfigurableApplicationContext context) {
        this.context = context;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = null;
        try{
            scanner = new Scanner(System.in);

            TaskMenuExecutor taskMenuExecutor = getTaskMenuExecutor(scanner);

            while (running.get()) {
                taskMenuExecutor.execute();
            }
        }
        catch(Exception e){
            LOG.error(e.getMessage());
            throw e;
        }
        finally {
            if(scanner != null){
                scanner.close();
            }
        }

    }

    private TaskMenuExecutor getTaskMenuExecutor(Scanner scanner) {
        AddTaskExecutor addTaskExecutor = new AddTaskExecutor(scanner);
        TaskListExecutor taskListExecutor = new TaskListExecutor(scanner);
        TaskUpdateExecutor taskUpdateExecutor = new TaskUpdateExecutor(scanner);
        TaskDeleteExecutor taskDeleteExecutor = new TaskDeleteExecutor(scanner);

        return new TaskMenuExecutor(new TaskMenuConfiguration(
                () -> SpringApplication.exit(context, () -> 0),
                addTaskExecutor::execute,
                taskUpdateExecutor::execute,
                taskDeleteExecutor::execute,
                taskListExecutor::execute,
                scanner
        ));
    }

    @PreDestroy
    public void onShutdown() {
        System.out.println("Shuting down ...");
        running.set(false);
    }
}
