class Subtask extends Task {
    private final int epicId;

    public Subtask(int id, String name, String description, int priority, String status, int epicId) {
        super(id, name, description, priority, status);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }
}
