import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private final HashMap<Integer, Task> tasks;
    private final HashMap<Integer, Epic> epics;
    private final HashMap<Integer, Subtask> subtasks;

    public TaskManager() {
        tasks = new HashMap<>();
        epics = new HashMap<>();
        subtasks = new HashMap<>();
    }

    public ArrayList<Task> getAllTasks() {

        return new ArrayList<>(tasks.values());
    }

    public ArrayList<Epic> getAllEpics() {

        return new ArrayList<>(epics.values());
    }

    public ArrayList<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    public Task getTaskById(int id) {
        return tasks.getOrDefault(id, null);
    }

    public Epic getEpicById(int id) {
        return epics.getOrDefault(id, null);
    }

    public Subtask getSubtaskById(int id) {
        return subtasks.getOrDefault(id, null);
    }

    public void createTask(Task task) {
        tasks.put(task.getId(), task);
        System.out.println("Task created: " + task.getId());
    }

    public void createEpic(Epic epic) {
        epics.put(epic.getId(), epic);
        System.out.println("Epic created: " + epic.getId());
    }

    public void createSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
        System.out.println("Subtask created: " + subtask.getId());
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

    protected void removeEpicById(int id) {
        if (epics.containsKey(id)) {
            epics.remove(id);
            System.out.println("Epic removed: " + id);
        } else {
            System.out.println("Epic not found: " + id);
        }
    }

    protected void removeSubtaskById(int id) {
        if (subtasks.containsKey(id)) {
            subtasks.remove(id);
            System.out.println("Subtask removed: " + id);
        } else {
            System.out.println("Subtask not found: " + id);
        }
    }
}
