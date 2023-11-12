package graphs;

import java.util.List;

public abstract class AbstractGraph<V> {

    private List<V> vertices;

    public AbstractGraph(List<V> vertices) {
        this.vertices = vertices;
    }

    public V get(int index) {
        assert index >= 0 : "Index must be a positive number";
        assert index < vertices.size() : "Index must be in range of the list's length";

        return vertices.get(index);
    }

    public int getIndex(V data){
        return vertices.indexOf(data);
    }
    public boolean contains(V data) {
        return vertices.contains(data);
    }
    public int size() {
        return vertices.size();
    }
    public void add(V data) {
        assert data != null : "Element cannot be null";
        vertices.add(data);
    }
    public boolean remove(V data){
       return vertices.remove(data);
    }


    public List<V> getVertices() {
        return vertices;
    }
}
