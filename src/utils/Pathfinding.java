package utils;

import graphs.MatrixGraph;
import model.Station;

import java.util.*;

public class Pathfinding {
    private static ArrayList<Station> stations = Station.readStationsFromFile("resources/stations.csv");
    public static Set<Station> findPathAStar(Station startingStation, Station endStation) {
        Coordinates startCoords = new Coordinates(startingStation.getCode(), startingStation.getGeoLat(), startingStation.getGeoLng());
        Coordinates endCoords = new Coordinates(startingStation.getCode(), endStation.getGeoLat(), endStation.getGeoLng());

        MatrixGraph<String> graph = MatrixGraph.addConnectionsToMatrixGraph("resources/tracks.csv");

        List<Coordinates> openSet = new ArrayList<>();
        Set<Coordinates> closedSet = new HashSet<>();
        openSet.add(startCoords);
        while (!openSet.isEmpty()) {
            Coordinates currentCoord = openSet.get(0);
            for (int i = 1; i < openSet.size(); i++) {
                if (openSet.get(i).getFCost() < currentCoord.getFCost() || openSet.get(i).getFCost() == currentCoord.getFCost() && openSet.get(i).getHCost() < currentCoord.getHCost()){
                    currentCoord = openSet.get(i);
                }
            }
//            for (Coordinates coords : openSet) {
//                if (coords.getFCost() < currentCoord.getFCost() || coords.getFCost() == currentCoord.getFCost() && coords.getHCost() < currentCoord.getHCost()){
//                    currentCoord = coords;
//                }
//            }
            openSet.remove(currentCoord);
            closedSet.add(currentCoord);

            if (currentCoord == endCoords) {
                return retracePath(startCoords, endCoords);
            }

            for (String codeOfNeighbor : graph.getNeighbors(currentCoord.getCode())){
                Coordinates neighbor = turnCodeToCoordinatesClass(codeOfNeighbor);
//                for (Coordinates cords : closedSet){
//                    if (cords.getCode().equals(codeOfNeighbor)){
//                        continue;
//                    }
//                }

                if (closedSet.contains(neighbor)){
                    continue;
                }

                int movementCostToNeighboor = currentCoord.getGCost() + graph.getWeight(currentCoord.getCode(), codeOfNeighbor);
                if (movementCostToNeighboor < neighbor.getGCost() || !openSet.contains(neighbor)){
                    neighbor.setGCost(movementCostToNeighboor);
                    // FIXME: using getweight from the neighbor to the end positom may reutnr nothing because they are not connected
                    neighbor.setHCost(graph.getWeight(neighbor.getCode(), endCoords.getCode()));
                    neighbor.setParent(currentCoord);

                    if (!openSet.contains(neighbor)){
                        openSet.add(neighbor);
                    }
                }
            }

        }
        return null;
    }

    private static Set<Station> retracePath(Coordinates startCoords, Coordinates endCoords) {
        List<Coordinates> path = new ArrayList<>();
        Coordinates currentCords = endCoords;

        while (currentCords != startCoords) {
            path.add(currentCords);
            currentCords = currentCords.getParent();
        }
        Collections.reverse(path);
        List<Station> stationPath = stations.stream()
                .filter(station -> station.getCode().equals(path.stream().map(Coordinates::getCode)))
                .toList();
        return new HashSet<>(stationPath);
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
}
