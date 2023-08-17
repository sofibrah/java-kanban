import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Task task1 = new Task(1, "Task 1", "Description 1", 1, "NEW");
        Task task2 = new Task(2, "Task 2", "Description 2", 2, "NEW");

        Epic epic1 = new Epic(3, "Epic 1", "Epic Description 1", 3, "NEW");
        Epic epic2 = new Epic(4, "Epic 2", "Epic Description 2", 4, "NEW");

        Subtask subtask11 = new Subtask(5, "Subtask 11", "Subtask Description 11", 5, "NEW", epic1.getId());
        Subtask subtask12 = new Subtask(6, "Subtask 12", "Subtask Description 12", 6, "NEW", epic1.getId());
        Subtask subtask21 = new Subtask(7, "Subtask 21", "Subtask Description 21", 7, "NEW", epic2.getId());

        epic1.addSubtask(subtask11);
        epic1.addSubtask(subtask12);
        epic2.addSubtask(subtask21);

        TaskManager taskManager = new TaskManager();
        taskManager.createTask(task1);
        taskManager.createTask(task2);
        taskManager.createEpic(epic1);
        taskManager.createEpic(epic2);
        taskManager.createSubtask(subtask11);
        taskManager.createSubtask(subtask12);
        taskManager.createSubtask(subtask21);

        System.out.println("All tasks:");
        ArrayList<Task> allTasks = taskManager.getAllTasks();
        for (Task task : allTasks) {
            System.out.println(task.getId() + ": " + task.getName() + " - " + task.getDescription() + " - " + task.getStatus());
        }

        System.out.println("All epics:");
        ArrayList<Epic> allEpics = taskManager.getAllEpics();
        for (Epic epic : allEpics) {
            System.out.println(epic.getId() + ": " + epic.getName() + " - " + epic.getDescription() + " - " + epic.getStatus());
        }

        System.out.println("All subtasks:");
        ArrayList<Subtask> allSubtasks = taskManager.getAllSubtasks();
        for (Subtask subtask : allSubtasks) {
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

