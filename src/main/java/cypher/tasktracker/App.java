package cypher.tasktracker;

import cypher.tasktracker.runner.core.TaskRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class App implements CommandLineRunner {

    private static Logger LOG = LoggerFactory
            .getLogger(App.class);

    private ApplicationContext applicationContext;

    public App(final ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public static void main(String[] args) {
        LOG.info("STARTING THE APPLICATION");
        SpringApplication.run(App.class, args);
        LOG.info("APPLICATION FINISHED");

        System.setProperty("logging.level.org.springframework", "DEBUG");
    }

    @Override
    public void run(final String... args) throws Exception {
        applicationContext.getBean(TaskRunner.class).run(args);
    }
}
