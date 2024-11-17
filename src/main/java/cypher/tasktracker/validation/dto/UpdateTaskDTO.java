package cypher.tasktracker.validation.dto;

import cypher.tasktracker.validation.validators.NullOrNotEmpty;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;

import java.util.Optional;

public class UpdateTaskDTO {

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[0-9]+$", message = "ID must be a numeric value without decimals")
    private String id;

    @NullOrNotEmpty
    private String newName;

    @Nullable
    private Boolean isFinished;

    @Positive
    @Max(100)
    @Nullable
    private Number progress;

    public UpdateTaskDTO(final String id) {
        this.id = id;
    }

    public Long getId() {
        return id != null ? Long.parseLong(id) : null;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Optional<String> getNewName() {
        return newName == null ? Optional.empty() : Optional.of(newName);
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public Optional<Boolean> getFinished() {
        return isFinished == null ? Optional.empty() : Optional.of(isFinished);
    }

    public void setFinished(Boolean finished) {
        isFinished = finished;
    }

    public Optional<Number> getProgress() {
        return progress == null ? Optional.empty() : Optional.of(progress);
    }

    public void setProgress(String progress) {
        this.progress = Long.parseLong(progress);
    }


    public void reset() {
        this.isFinished = null;
        this.progress = null;
        this.newName = null;
    }
}
