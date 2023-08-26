package managers;

import tasks.Task;
import datastructures.CustomLinkedList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private final CustomLinkedList<Task> history;
    private final Map<Integer, CustomLinkedList.Node<Task>> taskNodes;
    private static final int MAX_HISTORY_SIZE = 10;

    public InMemoryHistoryManager() {
        history = new CustomLinkedList<>();
        taskNodes = new HashMap<>();
    }

    @Override
    public void add(Task task) {
        CustomLinkedList.Node<Task> node = taskNodes.get(task.getId());
        if (node != null) {
            history.remove(node);
        }
        CustomLinkedList.Node<Task> newNode = new CustomLinkedList.Node<>(task);
        history.add(newNode);
        taskNodes.put(task.getId(), newNode);

        while (history.getSize() > MAX_HISTORY_SIZE) {
            CustomLinkedList.Node<Task> headNode = history.getHead();
            history.remove(headNode);
            taskNodes.remove(headNode.getData().getId());
        }
    }

    @Override
    public void remove(Task task) {
        CustomLinkedList.Node<Task> node = taskNodes.get(task.getId());
        if (node != null) {
            history.remove(node);
            taskNodes.remove(task.getId());
        }
    }

    @Override
    public List<Task> getHistory() {
        List<Task> taskHistory = new ArrayList<>();
        CustomLinkedList.Node<Task> node = history.getTail();
        while (node != null) {
            taskHistory.add(node.getData());
            node = node.prev;
        }
        return taskHistory;
    }
}