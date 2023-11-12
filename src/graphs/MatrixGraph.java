package graphs;

import model.Connection;
import model.Station;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class MatrixGraph<V> extends AbstractGraph<V> {

    private final boolean directed;
    private Integer[][] connections;

    public MatrixGraph(boolean directed, List<V> vertices) {
        super(vertices);
        this.directed = directed;
        this.connections = new Integer[vertices.size()][vertices.size()];
    }

    public boolean isDirected() {
        return directed;
    }

    public void connect(V vertex1, V vertex2, int weight){
        int index1 = getIndex(vertex1);
        int index2 = getIndex(vertex2);
//        assert index1 != -1 && index2 != -1 : "Vertexes do not exist";
        if (index1 != -1 && index2 != -1){
            connections[index1][index2] = weight;
            if (!isDirected()){
                connections[index2][index1] = weight;
            }
        }
    }

    public Integer getWeight(V vertex1, V vertex2) {
        assert getVertices().contains(vertex1) && getVertices().contains(vertex2) : "You can only get the weight of vertices that exist";

        int index1 = getIndex(vertex1);
        int index2 = getIndex(vertex2);
        Integer weight = connections[index1][index2];

        if (weight == null) {
            return 0;
        }

        return weight;
    }

    public List<V> getNeighbors(V vertex) {
        // No assertion as it may interrupt cases of the A* algorithm

        List<V> neighbors = new ArrayList<>();
        int indexOfVertex = getIndex(vertex);

        if (indexOfVertex != -1) {
            for (int i = 0; i < getVertices().size(); i++) {
                if (connections[indexOfVertex][i] != null && connections[indexOfVertex][i] != 0) {
                    neighbors.add(get(i));
                }
            }
        }

        return neighbors;
    }

    public boolean isConnected(V vertex1, V vertex2) {
        assert getVertices().contains(vertex1) && getVertices().contains(vertex2) : "You can only check if vertices connected if they exist";

        int index1 = getIndex(vertex1);
        int index2 = getIndex(vertex2);
        return connections[index1][index2] != null || connections[index2][index1] != null;
    }

    public Set<V> breadthFirst(V startVertex) {
        assert getVertices().contains(startVertex) : "You can only traverse starting from a vertex that exists";

        Set<V> visited = new HashSet<>();
        Queue<V> queue = new LinkedList<>();
        queue.add(startVertex);
        while (!queue.isEmpty()){
            V next = queue.poll();
            if (!(visited.contains(next))){
                System.out.println(next);
                visited.add(next);
                int indexOfNext = getIndex(next);
                for (int i = 0; i < connections[indexOfNext].length; i++) {
                    if (connections[indexOfNext][i] != null && !visited.contains(getVertices().get(i))) {
                        queue.add(getVertices().get(i));
                    }
                }
            }
        }
        return visited;
    }

    public Set<V> depthFirst(V startVertex) {
        assert getVertices().contains(startVertex) : "You can only traverse starting from a vertex that exists";

        Set<V> visited = new HashSet<>();
        Stack<V> stack = new Stack<>();
        stack.push(startVertex);
        while (!stack.isEmpty()){
            V next = stack.pop();
            if (!(visited.contains(next))){
                System.out.println(next);
                visited.add(next);
                int indexOfNext = getIndex(next);
                for (int i = 0; i < connections[indexOfNext].length; i++) {
                    if (connections[indexOfNext][i] != null && !visited.contains(getVertices().get(i))) {
                        stack.add(getVertices().get(i));
                    }
                }
            }
        }
        return visited;
    }

    public boolean isEmpty() {
        return getVertices().isEmpty();
    }

    public static MatrixGraph<String> addConnectionsToMatrixGraph(String fileName) {
        assert fileName != null : "File name cannot be null";

        Scanner scanner = null;
        List<String> codes;
        codes = Station.readStationsFromFile("resources/stations.csv").stream().map(Station::getCode).toList();
        MatrixGraph<String> graph = new MatrixGraph<>(false, codes);
        try {
            scanner = new Scanner(new File(fileName));
            scanner.nextLine();
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] lineParse = line.split(",");

                String station1Code = lineParse[0];
                if (!station1Code.matches("[a-z]*")) break;
                String station2Code = lineParse[1];
                if (!station2Code.matches("[a-z]*")) break;
                int distance = Integer.parseInt(lineParse[2]);
                if (!String.valueOf(distance).matches("[0-9]*")) break;

                graph.connect(station1Code.toUpperCase(), station2Code.toUpperCase(), distance);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return graph;
    }

    public void setEdgeIncluded(V vertex1, V vertex2, boolean included, int weight) {
        assert getVertices().contains(vertex1) && getVertices().contains(vertex2) : "You can only set the edge of vertices that exist";

        int index1 = getIndex(vertex1);
        int index2 = getIndex(vertex2);
        if (index1 != -1 && index2 != -1) {
            if (included) {
                connections[index1][index2] = weight;
                if (!isDirected()) {
                    connections[index2][index1] = weight;
                }
            } else {
                connections[index1][index2] = null;
                if (!isDirected()) {
                    connections[index2][index1] = null;
                }
            }
        }
    }
    public String toWebGraphViz() {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph G {\n");

        for (int i = 0; i < getVertices().size(); i++) {
            V vertex = getVertices().get(i);
            List<V> neighbors = getNeighbors(vertex);

            for (V neighbor : neighbors) {
                sb.append("\"" + vertex + "\" -> \"" + neighbor + "\"\n");
            }
        }

        sb.append("}");

        return sb.toString();
    }
    @Override
    public String toString() {
        return "MatrixGraph{" +
                "directed=" + directed +
                ", connections=" + Arrays.toString(connections) +
                '}';
    }
}
