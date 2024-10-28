package cypher.tasktracker.validation.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class DeleteTaskDTO {

    @NotNull
    @NotEmpty
    private String id;

    public DeleteTaskDTO(final String id) {
        this.id = id;
    }
}
