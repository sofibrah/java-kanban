package managers;

import tasks.Task;
import datastructures.Node;
import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private Node<Task> head;
    private Node<Task> tail;


    @Override
    public void add(Task task) {
        Node<Task> node = getNodeByTask(task);
        if (node != null) {
            remove(node);
        }
        Node<Task> newNode = new Node<>(task);
        add(newNode);
    }

    @Override
    public void remove(Task task) {
        Node<Task> node = getNodeByTask(task);
        if (node != null) {
            remove(node);
        }
    }

    @Override
    public List<Task> getHistory() {
        List<Task> taskHistory = new ArrayList<>();
        Node<Task> current = tail;
        while (current != null) {
            taskHistory.add(current.getData());
            current = current.prev;
        }
        return taskHistory;
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