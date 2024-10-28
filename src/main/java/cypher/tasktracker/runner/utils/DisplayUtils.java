package cypher.tasktracker.runner.utils;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DisplayUtils {

    private static Logger LOG = LoggerFactory
            .getLogger(DisplayUtils.class);

    public static void displayChoices(String... choices) {
        for (int i = 0; i < choices.length; i++) {
            StringBuilder stringBuilder = new StringBuilder()
                    .append(i + 1)
                    .append(". ")
                    .append(choices[i]);

            LOG.info(stringBuilder.toString());
        }
    }

    public static <T> boolean displayValidationViolations(final Validator validator, T objectToValidate) {
        var violations = validator.validate(objectToValidate);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<T> violation : violations) {
                LOG.info("The " + violation.getPropertyPath() + " " + violation.getMessage());
            }
            return false;
        }
        return true;
    }
}
