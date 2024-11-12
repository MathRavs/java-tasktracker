package cypher.tasktracker.services.data;

import cypher.tasktracker.data.database.models.TaskModel;
import cypher.tasktracker.data.database.repositories.TaskRepository;
import cypher.tasktracker.exceptions.EntityNotFoundException;
import cypher.tasktracker.mappers.TaskMapper;
import cypher.tasktracker.validation.DTO.AddTaskDTO;
import cypher.tasktracker.validation.DTO.UpdateTaskDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskModel> findAll() {
        return taskRepository.findAll();
    }

    public List<TaskModel> findAllDone() {
        return taskRepository.findAllByDoneInOrProgressIn(List.of(true), List.of(100.0));
    }

    public List<TaskModel> findAllTodo() {
        return taskRepository.findAllByDoneInAndProgressIn(List.of(false), List.of(0.0));
    }

    public List<TaskModel> findAllInProgress() {
        return taskRepository.findAllByDoneInOrProgressNotIn(List.of(false), List.of(100.0));
    }

    public void addTask(AddTaskDTO addTaskDTO) {
        this.taskRepository.save(TaskMapper.map(addTaskDTO));
    }

    public TaskModel removeTask(Long id) {
        return this.taskRepository.removeById(id);
    }

    public void updateTask(UpdateTaskDTO updateTaskDTO) {
        var id = updateTaskDTO.getId();

        var task = this.taskRepository.findById(id);

        if (task.isEmpty()) {
            throw new EntityNotFoundException("task", id);
        }

        var taskValue = task.get();

        if (updateTaskDTO.getNewName().isPresent()) {
            taskValue.setName(updateTaskDTO.getNewName().get());
        }

        if (updateTaskDTO.getFinished().isPresent()) {
            taskValue.setDone(updateTaskDTO.getFinished().get());
        }

        if (updateTaskDTO.getProgress().isPresent()) {
            taskValue.setProgress(updateTaskDTO.getProgress().get().doubleValue());

            if (taskValue.getProgress() == 100) {
                taskValue.setDone(true);
            }
        }

        this.taskRepository.save(taskValue);

    }

    public Optional<TaskModel> findById(Long id) {
        return this.taskRepository.findById(id);
    }

    public long countTasks() {
        return taskRepository.count();
    }

    public void deleteById(Long id) {
        this.taskRepository.deleteById(id);
    }

}
