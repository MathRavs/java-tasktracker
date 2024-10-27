package cypher.tasktracker.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(final String entityName, final Long id) {
        super(new StringBuilder()
                .append("The entity ")
                .append(entityName)
                .append("identitied by ")
                .append(id)
                .append(" does not exist")
                .toString()
        );
    }
}
