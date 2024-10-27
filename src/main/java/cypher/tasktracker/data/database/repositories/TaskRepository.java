package cypher.tasktracker.data.database.repositories;

import cypher.tasktracker.data.database.models.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskModel, Long> {

    TaskModel removeById(Long id);

}
