import java.util.ArrayList;
import java.util.List;

class InMemoryHistoryManager implements HistoryManager {
    private final List<Task> history;

    public InMemoryHistoryManager() {
        history = new ArrayList<>(10);
    }

    public void add(Task task) {
        if (!history.contains(task)) {
            if (history.size() >= 10) {
                history.remove(0);
            }
            history.add(task);
        }
    }

    public List<Task> getHistory() {
        return new ArrayList<>(history);
    }
}
