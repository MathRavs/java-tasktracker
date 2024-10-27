package cypher.tasktracker.config;

import cypher.tasktracker.data.database.repositories.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataConfig {

    private static final Logger log = LoggerFactory.getLogger(DataConfig.class);

    @Bean
    public CommandLineRunner dataInitialization(TaskRepository taskRepository) {
        return (args) -> {
            taskRepository.findAll().forEach(customer -> {
                log.info(customer.toString());
            });
        };
    }
}
