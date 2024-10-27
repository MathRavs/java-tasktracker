package cypher.tasktracker.services.data;

import cypher.tasktracker.data.database.models.TaskModel;
import cypher.tasktracker.data.database.repositories.TaskRepository;
import cypher.tasktracker.exceptions.EntityNotFoundException;
import cypher.tasktracker.mappers.TaskMapper;
import cypher.tasktracker.validation.AddTaskDTO;
import cypher.tasktracker.validation.UpdateTaskDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskModel> findAll() {
        return taskRepository.findAll();
    }

    public TaskModel addTask(AddTaskDTO addTaskDTO) {
        return this.taskRepository.save(TaskMapper.map(addTaskDTO));
    }

    public TaskModel removeTask(Long id) {
        return this.taskRepository.removeById(id);
    }

    public TaskModel updateTask(UpdateTaskDTO updateTaskDTO) {
        var id = updateTaskDTO.getId();

        var task = this.taskRepository.findById(id);

        if (task.isEmpty()) {
            throw new EntityNotFoundException("task", id);
        }

        var taskValue = task.get();
        taskValue.setName(updateTaskDTO.getNewName());

        return taskValue;
    }
}
