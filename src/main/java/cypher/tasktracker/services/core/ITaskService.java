package cypher.tasktracker.services.core;

import cypher.tasktracker.data.database.models.TaskModel;
import cypher.tasktracker.validation.dto.AddTaskDTO;
import cypher.tasktracker.validation.dto.UpdateTaskDTO;

import java.util.List;
import java.util.Optional;

public interface ITaskService {
    List<TaskModel> findAll();

    List<TaskModel> findAllDone();

    List<TaskModel> findAllTodo();

    List<TaskModel> findAllInProgress();

    void addTask(AddTaskDTO addTaskDTO);

    TaskModel removeTask(Long id);

    void updateTask(UpdateTaskDTO updateTaskDTO);

    Optional<TaskModel> findById(Long id);

    long countTasks();

    void deleteById(Long id);
}
