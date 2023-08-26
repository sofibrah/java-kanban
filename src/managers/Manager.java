package managers;

import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

public class Manager {
    public static TaskManager getDefault() {
        return new CustomInMemoryTaskManager();
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}

class CustomInMemoryTaskManager extends InMemoryTaskManager{
    @Override
    public void createTask(Task task) {
        super.createTask(task);
    }
    @Override
    public void createEpic(Epic epic) {
        super.createEpic(epic);
    }
    @Override
    public void createSubtask(Subtask subtask) {
        super.createSubtask(subtask);
    }

    @Override
    public void updateTask(Task task) {
        super.updateTask(task);
    }
}

