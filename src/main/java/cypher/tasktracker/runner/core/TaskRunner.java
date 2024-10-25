package cypher.tasktracker.runner.core;

import cypher.tasktracker.runner.TaskMenuExecution.TaskMenuExecutor;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class TaskRunner {

    private final AtomicBoolean running = new AtomicBoolean(true);
    private final TaskMenuExecutor taskMenuExecutor;

    private static Logger LOG = LoggerFactory
            .getLogger(TaskRunner.class);


    public TaskRunner(
            final ConfigurableApplicationContext context,
            final TaskMenuExecutor taskMenuExecutor
    ) {
        this.taskMenuExecutor = taskMenuExecutor;
    }

    public void run(String... args) throws Exception {
        try {
            while (running.get()) {
                this.taskMenuExecutor.execute();
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw e;
        }
    }

    @PreDestroy
    public void onShutdown() {
        System.out.println("Shuting down ...");
        running.set(false);
    }
}
