package cypher.tasktracker.data.database.models;

import jakarta.persistence.*;

@Entity(name = "Task")
public class TaskModel {
    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", done=" + done +
                ", progress=" + progress +
                '}';
    }

    protected TaskModel() {
    }

    public TaskModel(
            final String name
    ) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean done = false;

    @Column(nullable = false)
    private double progress = 0.0;


    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(final boolean done) {
        this.done = done;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(final double progress) {
        this.progress = progress;
    }
}
