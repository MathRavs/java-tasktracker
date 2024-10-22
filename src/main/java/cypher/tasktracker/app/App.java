package cypher.tasktracker.app;

import cypher.tasktracker.runner.TaskRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class App {

    private static Logger LOG = LoggerFactory
            .getLogger(App.class);

    public static void main(String[] args){
        LOG.info("STARTING THE APPLICATION");
        SpringApplication.run(TaskRunner.class, args);
        LOG.info("APPLICATION FINISHED");
    }
}
