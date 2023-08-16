import java.util.ArrayList;
import java.util.HashMap;

class TaskManager {
    private HashMap<Integer, Task> tasks;

    public TaskManager() {
        tasks = new HashMap<>();
    }

    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public void removeTasks() {
        tasks.clear();
        System.out.println("All tasks have been removed.");
    }

    public Task getTaskById(int id) {
        return tasks.getOrDefault(id, null);
    }

    public void createTask(Task task) {
        tasks.put(task.getId(), task);
        System.out.println("Task created: " + task.getId());
    }

    public void updateTask(Task task) {
        if (tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
            System.out.println("Task updated: " + task.getId());
        } else {
            System.out.println("Task not found: " + task.getId());
        }
    }

    public void removeTaskById(int id) {
        if (tasks.containsKey(id)) {
            tasks.remove(id);
            System.out.println("Task removed: " + id);
        } else {
            System.out.println("Task not found: " + id);
        }
    }

    public ArrayList<Task> getSubtasksByEpic(Epic epic) {
        ArrayList<Task> subtasks = new ArrayList<>();
        for (Task task : tasks.values()) {
            if (task instanceof Subtask) {
                Subtask subtask = (Subtask) task;
                if (subtask.getEpicId() == epic.getId()) {
                    subtasks.add(subtask);
                }
            }
        }
        return subtasks;
    }
}
