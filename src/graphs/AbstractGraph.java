package graphs;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGraph<V> {

    private List<V> vertices;

    public AbstractGraph(List<V> vertices) {
        this.vertices = vertices;
    }

    public V get(int index) {
        return vertices.get(index);
    }

    protected int getIndex(V data){
        return vertices.indexOf(data);
    }
    public boolean contains(V data) {
        return vertices.contains(data);
    }

    public List<V> getVertices() {
        return vertices;
    }
}
