package tasks;
import status.*;
import java.util.ArrayList;
import java.util.List;


public class Epic extends Task {
    private final List<Subtask> subtasks;

    public Epic(int id, String name, String description, int priority, Status status) {
        super(id, name, description, priority, status);
        subtasks = new ArrayList<>();
    }

    public void addSubtask(Subtask subtask) {
        subtasks.add(subtask);
    }

    public List<Subtask> getSubtasks() {
        return subtasks;
    }
}



