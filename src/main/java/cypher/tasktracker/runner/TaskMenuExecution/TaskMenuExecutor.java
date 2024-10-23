package cypher.tasktracker.runner.TaskMenuExecution;

import cypher.tasktracker.runner.core.AbstractTaskExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class TaskMenuExecutor extends AbstractTaskExecutor {

    private static Logger LOG = LoggerFactory
            .getLogger(TaskMenuExecutor.class);

    private final TaskMenuConfiguration configuration;
    private final String[] actionList = {"List tasks", "Add a task", "Delete a task", "Update a task", "Exit"};

    public TaskMenuExecutor(final TaskMenuConfiguration configuration) {
        super(configuration.scanner());
        this.configuration = configuration;
    }

    @Override
    public void execute() {
        LOG.info("What do you want to do ? ");
        this.displayChoices(this.actionList);

        String action = this.configuration.scanner().nextLine();

        HashMap<String, Runnable> actionsMap = new HashMap<>();

        actionsMap.put("1", this.configuration.listTaskCallback());
        actionsMap.put("2", this.configuration.addTaskCallback());
        actionsMap.put("3", this.configuration.deleteTaskCallback());
        actionsMap.put("4", this.configuration.updateTaskCallback());
        actionsMap.put("5", this.configuration.exitCallback());

        if(actionsMap.containsKey(action)){
            actionsMap.get(action).run();
        }
        else {
            LOG.info("Invalid action ID");
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
