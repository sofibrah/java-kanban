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

    public void removeSubtasks(Subtask subtask) {
        subtasks.remove(subtask);
    }

    @Override
    public void cancel() {
        super.cancel();
        List<Subtask> subtasksCopy = new ArrayList<>(subtasks);
        for (Subtask subtask : subtasksCopy) {
            subtask.cancel();
            subtasks.remove(subtask);
        }
    }
}



