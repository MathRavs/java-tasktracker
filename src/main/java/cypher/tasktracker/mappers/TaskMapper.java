package cypher.tasktracker.mappers;

import cypher.tasktracker.data.database.models.TaskModel;
import cypher.tasktracker.validation.AddTaskDTO;


public class TaskMapper {
    public static TaskModel map(AddTaskDTO addTaskDTO) {
        return new TaskModel(addTaskDTO.getName());
    }
}
