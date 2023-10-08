package lists;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;

public class MinHeap<T extends Comparable<T>> {
//    private int size;
//    private T[] array;
    private ArrayList<T> items;
    private Comparator<T> comparator;

    public MinHeap () {
//        this.size = 0;
//        this.array = (T[]) Array.newInstance(clazz, initialSize);
        this.items = new ArrayList<>();
        comparator = Comparator.naturalOrder();
    }

    public void add(T data){
        items.add(data);
        if (items.size() > 1){
            percolateUp(items.size());
        }
    }

    private void percolateUp(int size) {
        int parentIndex = (size / 2) - 1;
        if (parentIndex < 0) {
            parentIndex = 0;
        }
        if (comparator.compare(items.get(parentIndex), items.get(size - 1)) > 0){
            T addedElement = items.get(size-1);
            items.set(size-1, items.get(parentIndex));
            items.set(parentIndex, addedElement);
            percolateUp(parentIndex + 1);
        }
    }

    public T peek() {
        if (isEmpty()){
            return null;
        }
        return items.get(0);
    }

    public T remove() {
        if (isEmpty()){
            return null;
        }
        T result = peek();
        if (items.size() == 1) {
            items.remove(result);
            return result;
        }
        items.set(0, items.get(items.size()-1));
        items.remove(items.size()-1);
        percolateDown(0);
        return result;
    }

    private void percolateDown(int i) {
        int leftChildIndex = (2 * i) + 1;
        int rightChildIndex = (2 * i) + 2;
        if (!(leftChildIndex > items.size() - 1)){ // if no left-child dont check anything
            if (!(rightChildIndex > items.size() - 1)) { // if there is a right child as well compare both childs
                if (comparator.compare(items.get(leftChildIndex), items.get(rightChildIndex)) < 0){ // compare childs and continue with the smallest one
                    if (comparator.compare(items.get(i), items.get(leftChildIndex)) > 0) { // if child is smaller than number swap them
                        T movingElement = items.get(i);
                        items.set(i, items.get(leftChildIndex));
                        items.set(leftChildIndex, movingElement);
                        percolateDown(leftChildIndex);
                    }
                } else {
                    if (comparator.compare(items.get(i), items.get(rightChildIndex)) > 0) { // if child is smaller than number swap them
                        T movingElement = items.get(i);
                        items.set(i, items.get(rightChildIndex));
                        items.set(rightChildIndex, movingElement);
                        percolateDown(rightChildIndex);
                    }
                }
            } else {
                if (comparator.compare(items.get(i), items.get(leftChildIndex)) > 0) { // if child is smaller than number swap them
                    T movingElement = items.get(i);
                    items.set(i, items.get(leftChildIndex));
                    items.set(leftChildIndex, movingElement);
                    percolateDown(leftChildIndex);
                }
            }
        }
    }

    public String toWebGraphViz() {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph G {\n");
        for (int i = 0; i < items.size(); i++) {
            int leftChildIndex = (2 * i) + 1;
            int rightChildIndex = (2 * i) + 2;
            if (!(leftChildIndex > items.size() - 1)){
                sb.append("\"" + items.get(i) + "\" -> \"" + items.get(leftChildIndex) + "\"\n");
            }
            if (!(rightChildIndex > items.size() - 1)) {
                sb.append("\"" + items.get(i) + "\" -> \"" + items.get(rightChildIndex) + "\"\n");
            }
        }
        sb.append("}");

        return sb.toString();
    }

    public T get(int index) {
        return items.get(index);
    }

    public boolean contains(T data){
        if (items.contains(data)){
            return true;
        }
        return false;
    }

    public int size() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
//    public void push(T data) {
//        if (size==array.length) {
//            remap();
//        }
//        array[size]=data;
//        percolateUp(size);
//        size++;
//    }
//
//    public T pop() {
//        if (isEmpty()){
//            return null;
//        }
//        // store the root (smallest) value
//        T result = array[0];
//        //Find the last element
//        size--;
//        //Replace the root with the last element
//        array[0] = array[size];
//        // Check if it should move down
//        percolateDown(0);
//        // Return the original root value

//        return result;

//    }

//    public Object[] getObjects() {
//        return new Object[5];
//    }



}
