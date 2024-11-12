package cypher.tasktracker.display;

import cypher.tasktracker.data.database.models.TaskModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.IntStream;

public class TaskDisplay {
    private static Logger LOG = LoggerFactory
            .getLogger(TaskDisplay.class);

    public static void displayTasks(final List<TaskModel> tasks) {
        IntStream.range(0, tasks.size())
                .forEach(i -> LOG.info(i + "." + tasks.get(i)));
    }
}
