package cypher.tasktracker.runner.core;

import cypher.tasktracker.runner.TaskDeleteExecution.TaskDeleteExecutor;
import cypher.tasktracker.runner.TaskListExecution.TaskListExecutor;
import cypher.tasktracker.runner.TaskMenuExecution.TaskMenuExecutor;
import cypher.tasktracker.runner.TaskUpdateExecution.TaskUpdateExecutor;
import cypher.tasktracker.runner.taskAdd.AddTaskExecutor;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class TaskRunner {

    private final AtomicBoolean running = new AtomicBoolean(true);
    private final TaskMenuExecutor taskMenuExecutor;
    private final TaskListExecutor taskListExecutor;
    private final AddTaskExecutor addTaskExecutor;
    private final TaskDeleteExecutor taskDeleteExecutor;
    private final TaskUpdateExecutor taskUpdateExecutor;

    private static Logger LOG = LoggerFactory
            .getLogger(TaskRunner.class);


    public TaskRunner(
            final ConfigurableApplicationContext context,
            final TaskMenuExecutor taskMenuExecutor,
            final TaskDeleteExecutor taskDeleteExecutor,
            final TaskListExecutor taskListExecutor,
            final TaskUpdateExecutor taskUpdateExecutor,
            final AddTaskExecutor addTaskExecutor
    ) {
        this.taskMenuExecutor = taskMenuExecutor;
        this.taskDeleteExecutor = taskDeleteExecutor;
        this.taskListExecutor = taskListExecutor;
        this.addTaskExecutor = addTaskExecutor;
        this.taskUpdateExecutor = taskUpdateExecutor;
    }

    public void run(String... args) throws Exception {
        try {

            AbstractTaskExecutor defaultExecutor = this.taskMenuExecutor;

            Map<String, AbstractTaskExecutor> actions = Map.of(
                    "list", taskListExecutor,
                    "add", addTaskExecutor,
                    "delete", taskDeleteExecutor,
                    "update", taskUpdateExecutor
            );


            if (args.length != 0 && actions.containsKey(args[0])) {
                defaultExecutor = actions.get(args[0]);
            }

            while (running.get()) {
                defaultExecutor.execute();
                if (defaultExecutor != taskMenuExecutor) {
                    break;
                }
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
