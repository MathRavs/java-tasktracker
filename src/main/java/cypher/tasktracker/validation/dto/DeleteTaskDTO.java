package cypher.tasktracker.validation.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.util.Optional;

public class DeleteTaskDTO {

    @NotEmpty
    @Pattern(regexp = "^[0-9]+$", message = "ID must be a numeric value without decimals")
    private String id;

    public DeleteTaskDTO(final String id) {
        this.id = id;
    }

    public Optional<Long> getId() {
        return id == null ? Optional.empty() : Optional.of(Long.parseLong(id));
    }
}
