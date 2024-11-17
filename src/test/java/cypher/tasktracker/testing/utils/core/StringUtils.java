package cypher.tasktracker.testing.utils.core;

public class StringUtils {
    public static String buildTestString(final int length) {
        char[] chars = new char[length];
        java.util.Arrays.fill(chars, 'a'); // Replace 'a' with the desired character
        return new String(chars);
    }
}
