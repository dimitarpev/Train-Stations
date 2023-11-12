package utils;

import graphs.MatrixGraph;
import model.Station;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Pathfinding {
    private static ArrayList<Station> stations = Station.readStationsFromFile("resources/stations.csv");
    public static List<Coordinates> findPathAStar(Station startingStation, Station endStation) {
        Coordinates startCoords = new Coordinates(startingStation.getCode(), startingStation.getGeoLat(), startingStation.getGeoLng());
        Coordinates endCoords = new Coordinates(endStation.getCode(), endStation.getGeoLat(), endStation.getGeoLng());

        MatrixGraph<String> graph = MatrixGraph.addConnectionsToMatrixGraph("resources/tracks.csv");

        PriorityQueue<Coordinates> openSet = new PriorityQueue<>(Comparator.comparingDouble(Coordinates::getFCost));
        Set<Coordinates> closedSet = new HashSet<>();
        openSet.add(startCoords);
        while (!openSet.isEmpty()) {
            Coordinates currentCoord = openSet.poll();

            openSet.remove(currentCoord);
            closedSet.add(currentCoord);

            if (currentCoord.equals(endCoords)) {
                return retracePath(startCoords, currentCoord);
            }

            for (String codeOfNeighbor : graph.getNeighbors(currentCoord.getCode())){
                Coordinates neighbor = turnCodeToCoordinatesClass(codeOfNeighbor);

                if (closedSet.contains(neighbor)){
                    continue;
                }

                int movementCostToNeighbor;
                if (graph.getWeight(currentCoord.getCode(), codeOfNeighbor) != null) {
                    movementCostToNeighbor = currentCoord.getGCost() + graph.getWeight(currentCoord.getCode(), codeOfNeighbor);
                } else {
                    continue; // Skip to the next neighbor
                }
                if (movementCostToNeighbor < neighbor.getGCost() || !openSet.contains(neighbor)){
                    neighbor.setGCost(movementCostToNeighbor);
                    // pitagorus
                    neighbor.setHCost(calculateDistanceFromTwoPoints(neighbor, endCoords));
                    neighbor.setParent(currentCoord);

                    if (!openSet.contains(neighbor)){
                        openSet.add(neighbor);
                    }
                }
            }

        }
        return null;
    }

    public static Set<DijkstraVertex> findPathDijkstra(Station startingStation) {
        MatrixGraph<String> graph = MatrixGraph.addConnectionsToMatrixGraph("resources/tracks.csv");

        DijkstraVertex startingVertex = new DijkstraVertex(startingStation.getCode());
        startingVertex.setDistance(0);

        Set<DijkstraVertex> closedVertices = new HashSet<>();
        PriorityQueue<DijkstraVertex> openVertices = new PriorityQueue<>();
        openVertices.add(startingVertex);

        while (!openVertices.isEmpty()) {
            DijkstraVertex currentVertex = openVertices.poll();

            if (closedVertices.stream().map(DijkstraVertex::getCode).collect(Collectors.toSet()).contains(currentVertex.getCode())) {
                continue;
            }

            closedVertices.add(currentVertex);

            for (String neighbor : graph.getNeighbors(currentVertex.getCode())) {
                int newDistance = currentVertex.getDistance() + graph.getWeight(currentVertex.getCode(), neighbor);

                DijkstraVertex neighborVertex = new DijkstraVertex(neighbor);

                if (newDistance < neighborVertex.getDistance()) {
                    neighborVertex.setDistance(newDistance);
                    List<DijkstraVertex> newPath = new ArrayList<>(currentVertex.getShortestPath());
                    newPath.add(currentVertex);
                    neighborVertex.setShortestPath(newPath);

                    openVertices.add(neighborVertex);
                }
            }
        }

        return closedVertices;
    }

    private static int calculateDistanceFromTwoPoints(Coordinates neighbor, Coordinates endCoords) {
        final int R = 6371; // Radius of the Earth in kilometers

        double lat1 = Math.toRadians(neighbor.getLat());
        double lon1 = Math.toRadians(neighbor.getLng());
        double lat2 = Math.toRadians(endCoords.getLat());
        double lon2 = Math.toRadians(endCoords.getLng());

        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;

        double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return (int) Math.round(R * c);

    }

    private static List<Coordinates> retracePath(Coordinates startCoords, Coordinates endCoords) {
        List<Coordinates> path = new ArrayList<>();
        Coordinates currentCords = endCoords;

        while (!currentCords.equals(startCoords)) {
            path.add(currentCords);
            currentCords = currentCords.getParent();
        }
        if (currentCords.equals(startCoords)){
            path.add(currentCords);
        }
        Collections.reverse(path);

        List<String> stationCodes = path.stream()
                .map(Coordinates::getCode)
                .collect(Collectors.toList());

        Set<Station> stationPath = stations.stream()
                .filter(station -> stationCodes.contains(station.getCode()))
                .collect(Collectors.toSet());

        return path;
    }
    private static Coordinates turnCodeToCoordinatesClass(String code) {
        Station codeToStation = null;
        for (Station station : stations){
            if (station.getCode().equals(code)){
                codeToStation = station;
            }
        }
        assert codeToStation != null : "Code to parse to coordinates class does not exist for a station";
        return new Coordinates(codeToStation.getCode(), codeToStation.getGeoLat(), codeToStation.getGeoLng());
    }

    public static void printPathResultWithWeight(List<Coordinates> list) {
        assert !list.isEmpty() : "Cannot print path of an empty list";
        StringBuilder sb = new StringBuilder();
        sb.append("Shortest path of trip: ");

        for (Coordinates cord : list){
            sb.append(cord.getCode()).append(", ");
        }
        int totalWeight = list.get(list.size() - 1).getGCost();
        sb.append("\n Total weight: ").append(totalWeight);
        System.out.println(sb);
    }

    public static void printDijkstraResult(Set<DijkstraVertex> result) {
        System.out.println("Dijkstra's Shortest Paths:");
        for (DijkstraVertex vertex : result) {
            System.out.println("Code: " + vertex.getCode());
            System.out.println("Distance: " + vertex.getDistance());
            System.out.print("Shortest Path: ");
            for (DijkstraVertex pathVertex : vertex.getShortestPath()) {
                System.out.print(pathVertex.getCode() + " -> ");
            }
            System.out.println(vertex.getCode());
            System.out.println();
        }
    }
}
