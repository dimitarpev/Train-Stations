package lists;

public class LinkedList<T> implements List<T>{

    // FIXME: USE EQUALS EVERYWHERE
    class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }

    private Node<T> head;
    private int currentSize = 0;

    @Override
    public boolean add(T element) {
        assert element != null : "Element cannot be null";

        Node<T> node = new Node<>(element);
        if (currentSize == 0){
            head = node;
        } else {
            Node<T> temp = head;
            while (temp.next != null){
                temp = temp.next;
            }
            temp.next = node;
        }
        currentSize++;
        return false;
    }

    @Override
    public void add(int index, T element) {
        assert element != null : "Element cannot be null";
        assert index >= 0 : "Index cannot be a negative number";
        assert index <= currentSize : "Index must be smaller than the list size";

        Node<T> node = new Node<>(element);
        currentSize++;
        if (index == 0){
            node.next = head;
            head = node;
            return;
        }
        Node<T> temp = head;
        for (int i = 0; i < index-1; i++) {
            temp = temp.next;
        }
        node.next = temp.next;
        temp.next = node;

    }

    @Override
    public void clear() {
//        Node<T> temp = head;
//        while (temp.next != null){
//            temp.data = null;
//        }
        head = null;
        currentSize = 0;
    }

    @Override
    public boolean contains(T element) {
        assert element != null : "Element cannot be null";
        Node<T> temp = head;
        if (currentSize == 1){
            return temp.data.equals(element);
        }else if (temp != null){
            while (temp.next != null){
                if (temp.next.data.equals(element)){
                    return true;
                }
                temp = temp.next;
            }
        }
        return false;
    }

    @Override
    public T get(int index) {
        assert index >= 0 : "Index must be a positive number";
        assert index < currentSize : "Index must be in range of the list's length";
        Node<T> temp = head;
        if (index == 0){
            return temp.data;
        } else {
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            return temp.data;
        }
    }

    @Override
    public int indexOf(T element) {
        Node<T> temp = head;
        if (currentSize == 1){
            if (head.data == element){
                return 0;
            } else return -1;
        }
        for (int i = 0; i < currentSize; i++) {
            if (temp.data == element){
                return i;
            } else {
                temp = temp.next;
            }
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    private Node<T> getNodeOfIndex(int index) {
        assert index < currentSize : "Index must be in range of the list's length";
        Node<T> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public boolean remove(int index) {
        assert index >= 0 : "Index must be a positive number";
        assert index < currentSize : "Index must be in range of the list's length";
        if (index == 0) {
            head = head.next;
        } else {
            Node<T> removedElement = getNodeOfIndex(index);
            Node<T> changedElement = getNodeOfIndex(index - 1);
            changedElement.next = removedElement.next;
            removedElement.next = null;
        }
        currentSize--;
        return true;
    }

    @Override
    public boolean remove(T element) {
        assert element != null : "Element cannot be null";
        int index = indexOf(element);
        if (index == -1) return false;
        return remove(index);
    }

    @Override
    public T set(int index, T element) {
        assert index >= 0 : "Index must be a positive number";
        assert index < currentSize : "Index must be in range of the list's length";
        assert element != null : "Element cannot be null.";
        Node<T> node = getNodeOfIndex(index);
        T dataOfRemovedElement = node.data;
        node.data = element;
        return dataOfRemovedElement;
    }

    @Override
    public int size() {
        return currentSize;
    }
}
