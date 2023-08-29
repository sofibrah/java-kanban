import managers.HistoryManager;
import managers.Manager;
import managers.TaskManager;
import tasks.*;
import status.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = Manager.getDefault();
        HistoryManager historyManager = Manager.getDefaultHistory();

        Task task1 = new Task(1, "Task 1", "ДР у Дарьи", 3, Status.NEW);
        taskManager.createTask(task1);
        historyManager.add(task1);


        Epic epic1 = new Epic(1, "Epic 1", "Организация вечеринки", 3, Status.NEW);
        taskManager.createEpic(epic1);
        historyManager.add(epic1);


        Subtask subtask1 = new Subtask(1, "Subtask 1", "составить список гостей", 1, Status.NEW, epic1.getId());
        taskManager.createSubtask(subtask1);
        historyManager.add(subtask1);
        epic1.addSubtask(subtask1);

        Subtask subtask2 = new Subtask(2, "Subtask 2", "купить свечи для торта", 1, Status.NEW, epic1.getId());
        taskManager.createSubtask(subtask2);
        historyManager.add(subtask2);
        epic1.addSubtask(subtask2);

        Subtask subtask3 = new Subtask(3, "Subtask 3", "заказать пиццу", 2, Status.NEW, epic1.getId());
        taskManager.createSubtask(subtask3);
        historyManager.add(subtask3);

        subtask1.setStatus(Status.IN_PROGRESS);
        taskManager.updateTask(subtask1);

        subtask2.setStatus(Status.DONE);
        taskManager.updateTask(subtask2);

        taskManager.removeTaskById(subtask3.getId());
        System.out.println("\nHistory after removing subtask3");
        printHistory(historyManager.getHistory(), taskManager);


        Task task2 = new Task(2, "Task 2", "Переезд", 2, Status.IN_PROGRESS);
        taskManager.createTask(task2);
        historyManager.add(task2);
        Epic epic4 = new Epic(4, "Epic 4", "отправка вещей через сдэк", 4, Status.NEW);
        taskManager.createEpic(epic4);
        historyManager.add(epic4);

        Subtask subtask4 = new Subtask(4, "Subtask 4", "попросить коробки в каком-нибудь магазине", 2, Status.NEW, epic4.getId());
        taskManager.createTask(subtask4);
        historyManager.add(subtask4);
        epic4.addSubtask(subtask4);
        epic4.cancel();
        taskManager.removeTaskById(epic4.getId());
        System.out.println("\nHistory after removing epic 4:");
        printHistory(historyManager.getHistory(),taskManager);
    }


    private static void printHistory(List<Task> history, TaskManager taskManager) {
        for (Task task : history) {
            if (task.getStatus() != Status.CANCELLED) {
                if (task instanceof Subtask) {
                    Subtask subtask = (Subtask) task;
                    int epicId = subtask.getEpicId();
                    if (epicId != 0) {
                        Task parentTask = taskManager.getTaskById(epicId);
                        if (parentTask != null && (parentTask.getStatus() == Status.DONE || parentTask.getStatus() == Status.CANCELLED)) {
                            continue;
                        }
                    }
                } else if (task instanceof Epic) {
                    Epic epic = (Epic) task;
                    boolean allSubtasksCancelled = true;
                    for (Subtask subtask : epic.getSubtasks()) {
                        if (subtask.getStatus() != Status.CANCELLED) {
                            allSubtasksCancelled = false;
                            break;
                        }
                    }
                    if (allSubtasksCancelled) {
                        continue;
                    }
                }

                System.out.println("Task ID: " + task.getId());
                System.out.println("Name: " + task.getName());
                System.out.println("Description: " + task.getDescription());
                System.out.println("Priority: " + task.getPriority());
                System.out.println("Status: " + task.getStatus());
                System.out.println();
            }
        }
    }
}

