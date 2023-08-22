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

/*тут тоже две ошибки выдает
Method 'addSubtask(Subtask)' is never used
и
Method 'getSubtasks()' is never used
не знаю на сколько это критично
 */