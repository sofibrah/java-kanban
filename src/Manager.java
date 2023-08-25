import managers.HistoryManager;
import managers.InMemoryHistoryManager;
import managers.InMemoryTaskManager;
import managers.TaskManager;
import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

public class Manager  {
    public static TaskManager getDefault() {
        return new InMemoryTaskManager() {
            @Override
            public void createTask(Task task) {

            }

            @Override
            public void createEpic(Epic epic) {

            }

            @Override
            public void createSubtask(Subtask subtask) {

            }

            @Override
            public void updateTask(Task task) {

            }
        };
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}

