package tasks;
import status.*;

public class Task {
    private final int id;
    private final String name;
    private final String description;
    private final int priority;
    private Status status;


    public Task(int id, String name, String description, int priority, Status status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {

        this.status = status;
    }

    public void cancel(){
        setStatus(Status.CANCELLED);
    }
}
