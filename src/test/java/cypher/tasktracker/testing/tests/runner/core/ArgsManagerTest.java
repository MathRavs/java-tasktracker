package cypher.tasktracker.testing.tests.runner.core;

import cypher.tasktracker.runner.core.ArgsManager;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ArgsManagerTest {

    @Test
    void ShouldSetArgs() {
        var stringList = new String[]{"1"};
        var argsManager = new ArgsManager(stringList);
        var expectedList = Arrays.stream(stringList).toList();
        assertEquals(argsManager.getArgs(), expectedList);
    }

    @Test
    void ShouldDetectAdditionalKey() {
        var stringList = new String[]{"1", "2"};
        var argsManager = new ArgsManager(stringList);
        assertTrue(argsManager.hasAdditionalActionKey());

        stringList = new String[]{"1"};
        argsManager.setArgs(Arrays.stream(stringList).toList());
        assertFalse(argsManager.hasAdditionalActionKey());
    }
}
