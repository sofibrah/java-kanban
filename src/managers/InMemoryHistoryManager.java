package managers;

import tasks.Task;
import datastructures.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryHistoryManager implements HistoryManager {
    private Node<Task> head;
    private Node<Task> tail;
    private final Map<Integer, Task> viewedTasks;

    public InMemoryHistoryManager() {
        this.head = null;
        this.tail = null;
        this.viewedTasks = new HashMap<>();
    }

    @Override
    public void add(Task task) {
        Node<Task> node = getNodeByTask(task);
        if (node != null) {
            remove(node);
        }
        Node<Task> newNode = new Node<>(task);
        add(newNode);
        viewedTasks.put(task.getId(), task);
    }

    @Override
    public void remove(Task task) {
        Node<Task> node = getNodeByTask(task);
        if (node != null) {
            remove(node);
        }
        viewedTasks.remove(task.getId());
    }

    @Override
    public List<Task> getHistory() {
        return new ArrayList<>(viewedTasks.values());
    }

    private void add(Node<Task> node) {
        if (head == null) {
            head = node;
        } else {
            tail.next = node;
            node.prev = tail;
        }
        tail = node;

    }

    private void remove(Node<Task> node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }
    }

    private Node<Task> getNodeByTask(Task task) {
        Node<Task> current = head;
        while (current != null) {
            if (current.getData().equals(task)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }
}