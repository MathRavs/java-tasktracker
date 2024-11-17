package cypher.tasktracker.data.core;

import cypher.tasktracker.data.database.models.TaskModel;

import java.util.Collection;
import java.util.List;

public interface ITaskDataAccess {
    List<TaskModel> findAllByDoneInOrProgressIn(final Collection<Boolean> done, final Collection<Double> progress);

    List<TaskModel> findAllByDoneInAndProgressIn(final Collection<Boolean> done, final Collection<Double> progress);

    List<TaskModel> findAllByDoneInOrProgressNotIn(final Collection<Boolean> done, final Collection<Double> progress);

    TaskModel removeById(Long id);
}
