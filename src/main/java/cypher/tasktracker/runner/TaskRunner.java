package cypher.tasktracker.runner;

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

            TaskListExecutor taskListExecutor = new TaskListExecutor(() -> SpringApplication.exit(context, () -> 0), scanner);

            while (running.get()) {
                taskListExecutor.execute();
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

    @PreDestroy
    public void onShutdown() {
        System.out.println("Shuting down ...");
        running.set(false);
    }
}
