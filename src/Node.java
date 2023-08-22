public class Node<T> {
    private T data;
    private Node<T> prev;
    private Node<T> next;

    public Node(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getPrev() {
        return prev;
    }

    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}

/* в этом классе компилятор выдает ошибки, но я не знаю как их исправить:
Found problems related to 'data'
 и
 Found problems related to 'prev'
 еще есть
 Method 'setData(T)' is never used
 но последняя меня не сильно смутила, возможно в дальнейшем нам понадобиться этот метод
 */