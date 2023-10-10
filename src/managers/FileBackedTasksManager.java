package managers;

import status.Status;
import tasks.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FileBackedTasksManager implements TaskManager {
    private final HashMap<Integer, Task> tasks;
    private final HashMap<Integer, Epic> epics;
    private final HashMap<Integer, Subtask> subtasks;
    private final HistoryManager historyManager;
    private final File backupFile;

    public FileBackedTasksManager(File backupFile) {
        this.backupFile = backupFile;
        this.tasks = new HashMap<>();
        this.epics = new HashMap<>();
        this.subtasks = new HashMap<>();
        this.historyManager = new InMemoryHistoryManager();

        try {
            if (!backupFile.exists()) {
                backupFile.createNewFile();
                System.out.println("Файл резервной копии успешно создан.");
            } else {
                System.out.println("Файл резервной копии уже существует.");
            }
            loadTasksFromBackup();
        } catch (IOException e) {
            System.out.println("Ошибка при создании файла резервной копии: " + e.getMessage());
        }
    }


    @Override
    public void createTask(Task task) {
        tasks.put(task.getId(), task);
        historyManager.add(task);
        save();
    }

    @Override
    public void createEpic(Epic epic) {
        epics.put(epic.getId(), epic);
        historyManager.add(epic);
        save();
    }

    @Override
    public void createSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
        historyManager.add(subtask);
        save();
    }

    @Override
    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
        historyManager.add(task);
        save();
    }

    @Override
    public void removeTaskById(int id) {
        Task removedTask = tasks.remove(id);
        if (removedTask != null) {
            historyManager.remove(removedTask);
            save();
        }
    }

    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public List<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public List<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    @Override
    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    @Override
    public Epic getEpicById(int id) {
        return epics.get(id);
    }

    @Override
    public Subtask getSubtaskById(int id) {
        return subtasks.get(id);
    }

    @Override
    public void save() throws ManagerSaveException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(backupFile))) {
            for (Task task : tasks.values()) {
                writer.write(taskToCsvString(task));
                writer.newLine();
            }
            for (Task task : epics.values()) {
                writer.write(taskToCsvString(task));
                writer.newLine();
            }
            for (Task task : subtasks.values()) {
                writer.write(taskToCsvString(task));
                writer.newLine();
            }
            for (Task task : historyManager.getHistory()) {
                writer.write(taskToCsvString(task));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new ManagerSaveException("Failed to save the manager to backup file.");
        }
    }

    private void loadTasksFromBackup() {
        try (BufferedReader reader = new BufferedReader(new FileReader(backupFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseCsvStringToTask(line);
                if (task != null) {

                    if (task instanceof Epic) {
                        epics.put(task.getId(), (Epic) task);
                    } else if (task instanceof Subtask) {
                        subtasks.put(task.getId(), (Subtask) task);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String taskToCsvString(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append(task.getId()).append(",");
        sb.append(task.getName()).append(",");
        sb.append(task.getDescription()).append(",");
        sb.append(task.getPriority()).append(",");
        sb.append(task.getStatus().name());
        return sb.toString();
    }

    private Task parseCsvStringToTask(String line) {
        String[] fields = line.split(",");
        if (fields.length < 5) {
            return null;
        }
        int id = Integer.parseInt(fields[0]);
        String name = fields[1];
        String description = fields[2];
        int priority = Integer.parseInt(fields[3]);
        Status status = Status.valueOf(fields[4]);

        if (fields.length == 5) {
            return new Task(id, name, description, priority, status);
        } else if (fields.length == 6) {
            int epicId = Integer.parseInt(fields[5]);
            return new Subtask(id, name, description, priority, status, epicId);
        }
        return null;
    }
}

