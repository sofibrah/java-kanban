import java.util.ArrayList;
import java.util.HashMap;



public class InMemoryTaskManager implements TaskManager {
    private final HashMap<Integer, Task> tasks;
    private final HashMap<Integer, Epic> epics;
    private final HashMap<Integer, Subtask> subtasks;
    private final HistoryManager historyManager;

    public InMemoryTaskManager() {
        tasks = new HashMap<>();
        epics = new HashMap<>();
        subtasks = new HashMap<>();
        historyManager = new InMemoryHistoryManager();
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
        Task task = tasks.getOrDefault(id, null);
        if (task != null) {
            historyManager.add(task);
        }
        return task;
    }

    public Epic getEpicById(int id) {
        Epic epic = epics.getOrDefault(id, null);
        if (epic != null) {
            historyManager.add(epic);
        }
        return epic;
    }

    public Subtask getSubtaskById(int id) {
        Subtask subtask = subtasks.getOrDefault(id, null);
        if (subtask != null) {
            historyManager.add(subtask);
        }
        return subtask;
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
}
