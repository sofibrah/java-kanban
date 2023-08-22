import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private Node<Task> head;
    private Node<Task> tail;
    private final HashMap<Integer, Node<Task>> taskMap;

    public InMemoryHistoryManager() {
        taskMap = new HashMap<>();
    }

    public void add(Task task) {
        int taskId = task.getId();
        Node<Task> node = taskMap.get(taskId);

        if (node != null) {
            remove(node);
        }

        linkLast(task);
    }

    public void remove(Task task) {
        Node<Task> node = taskMap.get(task.getId());

        if (node != null) {
            remove(node);
        }
    }

    public List<Task> getHistory() {
        List<Task> historyList = new ArrayList<>();

        Node<Task> currNode = head;
        while (currNode != null) {
            historyList.add(currNode.getData());
            currNode = currNode.getNext();
        }

        return historyList;
    }

    private void linkLast(Task task) {
        Node<Task> newNode = new Node<>(task);
        newNode.setPrev(tail);

        if (head == null) {
            head = newNode;
        } else {
            tail.setNext(newNode);
        }

        tail = newNode;
        taskMap.put(task.getId(), newNode);
    }

    private void remove(Node<Task> node) {
        if (node == head) {
            head = head.getNext();
        } else if (node == tail) {
            tail = tail.getPrev();
        }

        if (node.getPrev() != null) {
            node.getPrev().setNext(node.getNext());
        }

        if (node.getNext() != null) {
            node.getNext().setPrev(node.getPrev());
        }

        taskMap.remove(node.getData().getId());
    }
}
