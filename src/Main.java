import java.util.List;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = Manager.getDefault();
        HistoryManager historyManager = Manager.getDefaultHistory();

        Task task1 = new Task(1, "Task 1", "ДР у Дарьи", 3, Status.NEW);
        taskManager.createTask(task1);
        historyManager.add(task1);

        Epic epic1 = new Epic(1,"Epic 1","Организация вечеринки",3,Status.NEW);
        taskManager.createEpic(epic1);
        historyManager.add(epic1);

        Subtask subtask1 = new Subtask(1,"Subtask 1", "составить список гостей", 1, Status.NEW, epic1.getId());
        taskManager.createSubtask(subtask1);
        historyManager.add(subtask1);
        Subtask subtask2 = new Subtask(2, "Subtask 2", "купить свечи для торта", 1, Status.NEW, epic1.getId());
        taskManager.createSubtask(subtask2);
        historyManager.add(subtask2);
        Subtask subtask3 = new Subtask(3, "Subtask 3", "заказать пиццу", 2, Status.NEW, epic1.getId());
        taskManager.createTask(subtask3);
        historyManager.add(subtask3);

        subtask1.setStatus(Status.IN_PROGRESS);
        taskManager.updateTask(subtask1);

        subtask2.setStatus(Status.DONE);
        taskManager.updateTask(subtask2);

        taskManager.removeTaskById(subtask3.getId());

        List<Task> history = historyManager.getHistory();
            System.out.println("History of Viewed Tasks:");
            for (Task task : history) {
                System.out.println("Task ID: " + task.getId());
                System.out.println("Name: " + task.getName());
                System.out.println("Description: " + task.getDescription());
                System.out.println("Priority: " + task.getPriority());
                System.out.println("Status: " + task.getStatus());

            }
        }
    }

