package tasks;

import status.Status;

public class Subtask extends Task {
    private final int epicId;

    public Subtask(int id, String name, String description, int priority, Status status, int epicId) {
        super(id, name, description, priority, status);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }
}



