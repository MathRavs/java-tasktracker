package cypher.tasktracker.config;

import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class AppConfig {

    private Scanner scanner;

    @Bean
    public Scanner scanner() {
        scanner = new Scanner(System.in);
        return scanner;
    }

    @PreDestroy // This method will be called on application shutdown
    public void cleanUp() {
        if (scanner != null) {
            scanner.close(); // Close the scanner to release resources
        }
    }
}
