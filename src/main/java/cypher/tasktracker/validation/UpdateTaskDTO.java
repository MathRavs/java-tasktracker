package cypher.tasktracker.validation;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class UpdateTaskDTO {

    @NotNull
    @NotEmpty
    private String id;

    @NotEmpty
    private String newName;

    private Boolean isFinished;

    @Positive
    @Max(100)
    private Number progress;


    public UpdateTaskDTO(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public @NotEmpty String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public Boolean getFinished() {
        return isFinished;
    }

    public void setFinished(Boolean finished) {
        isFinished = finished;
    }

    public @Positive @Max(100) Number getProgress() {
        return progress;
    }

    public void setProgress(@Positive @Max(100) Number progress) {
        this.progress = progress;
    }
}
