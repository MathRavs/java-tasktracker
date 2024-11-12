package cypher.tasktracker.data.database.repositories;

import cypher.tasktracker.data.database.models.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface TaskRepository extends JpaRepository<TaskModel, Long> {

    List<TaskModel> findAllByDoneInOrProgressIn(final Collection<Boolean> done, final Collection<Double> progress);

    List<TaskModel> findAllByDoneInAndProgressIn(final Collection<Boolean> done, final Collection<Double> progress);

    List<TaskModel> findAllByDoneInOrProgressNotIn(final Collection<Boolean> done, final Collection<Double> progress);

    TaskModel removeById(Long id);
}
