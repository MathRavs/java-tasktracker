package cypher.tasktracker.testing.tests.services.data;

import cypher.tasktracker.data.database.repositories.TaskRepository;
import cypher.tasktracker.services.data.TaskService;
import cypher.tasktracker.testing.utils.mocks.TaskMockBuilder;
import cypher.tasktracker.validation.dto.AddTaskDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void ShouldFindAll() {
        var task = TaskMockBuilder.build();
        when(taskRepository.findAll()).thenReturn(List.of(task));
        assertTrue(taskService.findAll().contains(task));
    }

    @Test
    void ShouldFindAllDone() {
        var task = TaskMockBuilder.build();
        when(taskRepository.findAllByDoneInOrProgressIn(List.of(true), List.of(100.0))).thenReturn(List.of(task));
        assertTrue(taskService.findAllDone().contains(task));
    }

    @Test
    void ShouldFindAllTodo() {
        var task = TaskMockBuilder.build();
        when(taskRepository.findAllByDoneInAndProgressIn(List.of(false), List.of(0.0))).thenReturn(List.of(task));
        assertTrue(taskService.findAllTodo().contains(task));
    }

    @Test
    void ShouldFindAllInProgress() {
        var task = TaskMockBuilder.build();
        when(taskRepository.findAllByDoneInOrProgressNotIn(List.of(false), List.of(100.0))).thenReturn(List.of(task));
        assertTrue(taskService.findAllInProgress().contains(task));
    }

    @Test
    void ShouldAddTask() {
        var dto = new AddTaskDTO("test");
        taskService.addTask(dto);
        verify(taskRepository).save(argThat(arg ->
                arg.getName().equals(dto.getName())
        ));
    }

    @Test
    void ShouldRemoveTask() {
        var task = TaskMockBuilder.build();
        task.setId(100L);
        when(taskRepository.removeById(task.getId())).thenReturn(task);
        var result = taskService.removeTask(task.getId());
        assertEquals(result, task);

        verify(taskRepository).removeById(argThat(arg ->
                arg.equals(task.getId())
        ));
    }

    @Test
    void ShouldUpdateTask() {

    }
}
