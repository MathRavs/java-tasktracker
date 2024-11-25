package cypher.tasktracker.testing.tests.mappers;

import cypher.tasktracker.mappers.TaskMapper;
import cypher.tasktracker.validation.dto.AddTaskDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskMapperTest {

    @Test
    void ShouldMapAddTaskDTO() {
        var addTaskDTO = new AddTaskDTO("test");
        var task = TaskMapper.map(addTaskDTO);
        assertEquals(task.getName(), addTaskDTO.getName());
    }

}
