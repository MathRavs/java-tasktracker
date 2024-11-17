package cypher.tasktracker.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AddTaskDTO {
    @NotNull
    @NotEmpty()
    @Size(max = 200)
    private String name;

    public String getName() {
        return name;
    }

    public AddTaskDTO(final String name) {
        this.name = name;
    }
}
