package cypher.tasktracker.runner.core;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class UserInputManager {

    private final Scanner scanner;

    public UserInputManager(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getUserInput() {
        return scanner.nextLine();
    }
}
