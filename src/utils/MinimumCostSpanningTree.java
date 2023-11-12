package utils;

import graphs.MatrixGraph;
import model.Station;

import java.util.*;

public class MinimumCostSpanningTree {
    private static ArrayList<Station> stations = Station.readStationsFromFile("resources/stations.csv");

    public static Set<String> primAlgorithm(MatrixGraph<String> graph) {

        Set<String> visited = new HashSet<>();
        Set<String> minimumSpanningTree = new HashSet<>();

        if (!graph.isEmpty()) {
            visited.add(graph.get(0));
            minimumSpanningTree.add(graph.get(0));
        }
        while (visited.size() < graph.getVertices().size() &&
                graph.getVertices().stream()
                        .anyMatch(v -> !visited.contains(v) && graph.getNeighbors(v).stream().anyMatch(visited::contains))) {
            graph.getVertices().stream().filter(visited::contains)
                    .map(graph::getNeighbors)
                    .flatMap(Collection::stream)
                    .filter(neighbor -> !visited.contains(neighbor))
                    .min(Comparator.comparingInt(n -> graph.getWeight(findMinConnection(graph, visited), n)))
                    .ifPresent(c -> {
                        visited.add(c);
                        minimumSpanningTree.add(c);
                        graph.setEdgeIncluded(graph.get(0), c, true, graph.getWeight(graph.get(0), c));
                    });
        }


        return minimumSpanningTree;
    }

    public static void printMinimumSpanningTree(MatrixGraph<String> graph, Set<String> minimumSpanningTree) {
        int totalCost = 0;
        Set<String> visitedEdges = new HashSet<>();

        System.out.println("Minimum Spanning Tree:");
        for (String vertex : minimumSpanningTree) {
            System.out.print(vertex + " -> ");

            for (String neighbor : graph.getNeighbors(vertex)) {
                String edge1 = vertex + "-" + neighbor;
                String edge2 = neighbor + "-" + vertex;

                // Check if either orientation of the edge is in the minimum spanning tree and has not been visited
                if ((minimumSpanningTree.contains(neighbor) && !visitedEdges.contains(edge1))
                        || (minimumSpanningTree.contains(vertex) && !visitedEdges.contains(edge2))) {
                    int cost = graph.getWeight(vertex, neighbor);
                    totalCost += cost;

                    // Mark both orientations of the edge as visited
                    visitedEdges.add(edge1);
                    visitedEdges.add(edge2);

                    System.out.print(neighbor + "(" + cost + ") ");
                }
            }

            System.out.println();
        }

        // Divide the total cost by 2 because each edge is counted twice
        totalCost /= 2;

        System.out.println("Total Minimum Connection Cost: " + totalCost);
    }

    private static String findMinConnection(MatrixGraph<String> graph, Set<String> visited) {
        String minVertex = null;
        int minWeight = Integer.MAX_VALUE;

        for (String visitedVertex : visited) {
            for (String neighbor : graph.getNeighbors(visitedVertex)) {
                if (!visited.contains(neighbor)) {
                    int weight = graph.getWeight(visitedVertex, neighbor);
                    if (weight < minWeight) {
                        minVertex = neighbor;
                        minWeight = weight;
                    }
                }
            }
        }

        return minVertex;
    }

    public static Set<String> determineMCSTByRectangle(MatrixGraph<String> graph, String code1, String code2) {
        Set<String> stationsWithinRectangle = identifyStationsWithinRectangle(graph, code1, code2);
        MatrixGraph<String> subgraph = createSubgraph(graph, stationsWithinRectangle);
        Set<String> minimumSpanningTree = primAlgorithm(subgraph);
        printMinimumSpanningTree(subgraph, minimumSpanningTree);

        return minimumSpanningTree;
    }

    public static Set<String> identifyStationsWithinRectangle(MatrixGraph<String> graph, String stationCode1, String stationCode2) {
        Set<String> stationsWithinRectangle = new HashSet<>();
        double lat1 = getGeoLat(stationCode1);
        double lng1 = getGeoLng(stationCode1);
        double lat2 = getGeoLat(stationCode2);
        double lng2 = getGeoLng(stationCode2);

        for (String vertex : graph.getVertices()) {
            double vertexLat = getGeoLat(vertex);
            double vertexLng = getGeoLng(vertex);

            if (vertexLat >= Math.min(lat1, lat2) && vertexLat <= Math.max(lat1, lat2) &&
                    vertexLng >= Math.min(lng1, lng2) && vertexLng <= Math.max(lng1, lng2)) {
                stationsWithinRectangle.add(vertex);
            }
        }

        return stationsWithinRectangle;
    }

    private static MatrixGraph<String> createSubgraph(MatrixGraph<String> graph, Set<String> vertices) {
        MatrixGraph<String> subgraph = new MatrixGraph<>(graph.isDirected(), new ArrayList<>(vertices));

        for (String vertex1 : vertices) {
            for (String vertex2 : vertices) {
                if (graph.isConnected(vertex1, vertex2)) {
                    int weight = graph.getWeight(vertex1, vertex2);
                    subgraph.connect(vertex1, vertex2, weight);
                }
            }
        }

        return subgraph;
    }

    private static double getGeoLat(String stationCode) {
        for (Station station : stations) {
            if (station.getCode().equals(stationCode)) {
                return station.getGeoLat();
            }
        }
        return 0;
    }

    private static double getGeoLng(String stationCode) {
        for (Station station : stations) {
            if (station.getCode().equals(stationCode)) {
                return station.getGeoLng();
            }
        }
        return 0;
    }
}
