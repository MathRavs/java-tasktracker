package cypher.tasktracker.testing.tests.runner.core;

import cypher.tasktracker.runner.core.UserInputManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UserInputManagerTest {

    @Mock
    private Scanner scanner;

    @InjectMocks
    private UserInputManager userInputManager;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void ShouldReturnScannerNextLine() {
        var nextValue = "test";
        when(scanner.nextLine()).thenReturn(nextValue);
        assertEquals(userInputManager.getUserInput(), nextValue);
    }
}
