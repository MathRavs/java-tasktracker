package cypher.tasktracker.services.core;

import cypher.tasktracker.data.database.models.TaskModel;
import cypher.tasktracker.validation.dto.AddTaskDTO;
import cypher.tasktracker.validation.dto.UpdateTaskDTO;

import java.util.List;
import java.util.Optional;

public interface ITaskService {
    public List<TaskModel> findAll();

    public List<TaskModel> findAllDone();

    public List<TaskModel> findAllTodo();

    public List<TaskModel> findAllInProgress();

    public void addTask(AddTaskDTO addTaskDTO);

    public TaskModel removeTask(Long id);

    public void updateTask(UpdateTaskDTO updateTaskDTO);

    public Optional<TaskModel> findById(Long id);

    public long countTasks();

    public void deleteById(Long id);
}
