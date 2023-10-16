package model;

import java.util.ArrayList;

public class TrainManager {

    private ArrayList<Station> stations;
    private ArrayList<Connection> connections;

    public TrainManager() {
        this.stations = Station.readStationsFromFile("resources/stations.csv");
        this.connections = Connection.readConnectionsFromFile("resources/tracks.csv", stations);
    }

    public ArrayList<Station> getStations() {
        return stations;
    }

    public ArrayList<Connection> getConnections() {
        return connections;
    }
}
