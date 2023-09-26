package lists;

import java.util.Iterator;

public class RecList<T> implements Iterable<T> {
    private T data;
    private RecList<T> rest;

    private RecList(T data, RecList<T> rest) {
        assert data != null : "Cannot store null data in a recursive list.";
        assert rest != null : "Both data and rest must be filled.";
        this.data = data;
        this.rest = rest;
    }

    public RecList() {}

    public void add(T newData) {
        if (newData == null) {
            throw new IllegalArgumentException("Recursive list cannot store null elements.");
        } else if (isEmpty()) {
            this.data = newData;
            this.rest = new RecList<>();
        } else {
            RecList<T> newRest = new RecList<>(getData(), this.rest);
            this.data = newData;
            this.rest = newRest;
        }
    }

    public T peekHead() {
        return getData();
    }

    public T peekTail() {
        if (isEmpty()) {
            return null;
        } else if (rest.isEmpty()) {
            return getData();
        } else {
            return rest.peekTail();
        }
    }

    public boolean isEmpty() {
        assert (data==null) == (rest==null) : "Data and rest should be both filled or both empty";
        return data == null;
    }

    public int getCount() {
        if (isEmpty()) {
            return 0;
        } else {
            return 1 + rest.getCount();
        }
    }

    public boolean contains(T data) {
        // Homework :)
        if (data == null) {
            return false;
        }
        // this.data.equals(data);
        return false;
    }

    @Override
    public String toString() {
        if (isEmpty()){
            return "[]";
        } else {
            assert rest != null : "Rest cannot be null when data is present";
            return "[" + getData().toString()+", " + rest.toString()+"]";
        }
    }

    private T getData() {
        return data;
    }

    @Override
    public Iterator<T> iterator() {
//        return MyIterator(this);
        return null;
    }

    class MyIterator implements Iterator<T> {
        private RecList<T> current;

        public MyIterator(RecList<T> current) {
            this.current = current;
        }

        @Override
        public boolean hasNext() {
            return current != null && !current.isEmpty();
        }

        @Override
        public T next() {
            return null;
        }
    }
}
