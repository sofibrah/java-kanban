import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Task task1 = new Task("Task 1", "Description 1", 1, "NEW");
        Task task2 = new Task("Task 2", "Description 2", 2, "NEW");

        Epic epic1 = new Epic("Epic 1", "Epic Description 1", 3, "NEW");
        Epic epic2 = new Epic("Epic 2", "Epic Description 2", 4, "NEW");

        Subtask subtask11 = new Subtask("Subtask 11", "Subtask Description 11", 5, "NEW", epic1.getId());
        Subtask subtask12 = new Subtask("Subtask 12", "Subtask Description 12", 6, "NEW", epic1.getId());
        Subtask subtask21 = new Subtask("Subtask 21", "Subtask Description 21", 7, "NEW", epic2.getId());

        epic1.addSubtask(subtask11);
        epic1.addSubtask(subtask12);
        epic2.addSubtask(subtask21);

        TaskManager taskManager = new TaskManager();
        taskManager.createTask(task1);
        taskManager.createTask(task2);
        taskManager.createTask(epic1);
        taskManager.createTask(epic2);

        System.out.println("All tasks:");
        ArrayList<Task> allTasks = taskManager.getAllTasks();
        for (Task task : allTasks) {
            System.out.println(task.getId() + ": " + task.getName() + " - " + task.getDescription() + " - " + task.getStatus());
        }

        System.out.println("Subtasks of Epic 1:");
        ArrayList<Task> subtasks = taskManager.getSubtasksByEpic(epic1);
        for (Task subtask : subtasks) {
            System.out.println(subtask.getId() + ": " + subtask.getName() + " - " + subtask.getDescription() + " - " + subtask.getStatus());
        }

        task1.setStatus("IN_PROGRESS");
        task2.setStatus("DONE");
        subtask11.setStatus("DONE");
        subtask12.setStatus("DONE");

        System.out.println("Updated tasks:");
        allTasks = taskManager.getAllTasks();
        for (Task task : allTasks) {
            System.out.println(task.getId() + ": " + task.getName() + " - " + task.getDescription() + " - " + task.getStatus());
        }

        taskManager.removeTaskById(6);
        taskManager.removeTaskById(4);

        System.out.println("Remaining tasks:");
        allTasks = taskManager.getAllTasks();
        for (Task task : allTasks) {
            System.out.println(task.getId() + ": " + task.getName() + " - " + task.getDescription() + " - " + task.getStatus());
        }
    }
}

