package cypher.tasktracker.testing.utils.mocks;

import cypher.tasktracker.data.database.models.TaskModel;

public class TaskMockBuilder {
    public static TaskModel build() {
        return new TaskModel("test");
    }
}
