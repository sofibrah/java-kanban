import java.util.ArrayList;

class Epic extends Task {
    private final ArrayList<Subtask> subtasks;

    public Epic(int id, String name, String description, int priority, Status status) {
        super(id, name, description, priority, status);
        subtasks = new ArrayList<>();
    }

    public void addSubtask(Subtask subtask) {
        subtasks.add(subtask);
    }

    public ArrayList<Subtask> getSubtasks() {
        return subtasks;
    }
}

