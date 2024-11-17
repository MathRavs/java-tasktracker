package cypher.tasktracker.testing.tests.core;

import cypher.tasktracker.testing.utils.core.StringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringUtilsTest {

    @Test
    void TestInternalStringTestBuilder() {
        assertEquals(5, StringUtils.buildTestString(5).length());
    }
}
