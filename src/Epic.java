import java.util.ArrayList;


class Epic extends Task {
    private final ArrayList<Task> subtasks;

    public Epic(String name, String description, int id, String status) {
        super(name, description, id, status);
        subtasks = new ArrayList<>();
    }

    public void addSubtask(Task task) {

        subtasks.add(task);
    }

    public ArrayList<Task> getSubtasks() {
        return subtasks;
    }

    public boolean isCompleted() {
        for (Task task : subtasks) {
            if (!task.getStatus().equals("DONE")) {
                return false;
            }
        }
        return true;
    }
}