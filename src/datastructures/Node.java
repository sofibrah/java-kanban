package datastructures;

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

/* в этом классе компилятор выдает ошибку:
 Method 'setData(T)' is never used
 но  меня не сильно смутила, подумала что возможно в дальнейшем нам понадобиться этот метод. Или убрать его?
 */