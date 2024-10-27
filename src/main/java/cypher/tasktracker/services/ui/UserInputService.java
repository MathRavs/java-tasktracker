package cypher.tasktracker.services.ui;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class UserInputService {

    private final Scanner scanner;

    public UserInputService(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getUserInput() {
        return scanner.nextLine();
    }
}
