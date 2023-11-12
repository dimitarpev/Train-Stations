package model;

import graphs.MatrixGraph;
import utils.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TrainManager {

    private ArrayList<Station> stations;
    private ArrayList<Connection> connections;

    public TrainManager() {
        this.stations = Station.readStationsFromFile("resources/stations.csv");
        this.connections = Connection.readConnectionsFromFile("resources/tracks.csv", stations);
    }

    public Station linearSearch(String searchName) {
        return StationSearch.linearSearch(stations, searchName);
    }

    public Station binarySearch(String searchName) {
        return StationSearch.binarySearch(stations, searchName);
    }
    public void insertionSort(){
        stations = StationSort.insertionSortStations(stations);
    }
    public void mergeSort(){
        stations = StationSort.mergeSortStations(stations);
    }

    public void shortestPath(String code1, String code2) {
        List<Station> matchingStations1 = stations.stream()
                .filter(station -> station.getCode().equals(code1))
                .toList();

        List<Station> matchingStations2 = stations.stream()
                .filter(station -> station.getCode().equals(code2))
                .toList();

        if (matchingStations1.isEmpty() || matchingStations2.isEmpty()) {
            System.out.println("Station codes do not exist");
        } else {
            Station station1 = matchingStations1.get(0);
            Station station2 = matchingStations2.get(0);
            List<Coordinates> path = Pathfinding.findPathAStar(station1, station2);
            Pathfinding.printPathResultWithWeight(path);
        }
    }

    public void determineMCST(String code1, String code2) {
        MatrixGraph<String> graph = MatrixGraph.addConnectionsToMatrixGraph("resources/tracks.csv");
        MinimumCostSpanningTree.determineMCSTByRectangle(graph, code1, code2);
    }

    public ArrayList<Station> getStations() {
        return stations;
    }

    public ArrayList<Connection> getConnections() {
        return connections;
    }


}
