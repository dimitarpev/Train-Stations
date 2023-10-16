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
        int index1 = getIndex(vertex1);
        int index2 = getIndex(vertex2);
        return connections[index1][index2];
    }

    public List<V> getNeighbors(V vertex) {
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
        int index1 = getIndex(vertex1);
        int index2 = getIndex(vertex2);
        return connections[index1][index2] != null || connections[index2][index1] != null;
    }

    public Set<V> breadthFirst(V startVertex) {
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

    public static MatrixGraph<String> addConnectionsToMatrixGraph(String fileName) {
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
                String station2Code = lineParse[1];
                int distance = Integer.parseInt(lineParse[2]);

                graph.connect(station1Code.toUpperCase(), station2Code.toUpperCase(), distance);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return graph;
    }
}
