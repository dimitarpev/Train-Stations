import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TrainManager {

    private ArrayList<Station> stations;
    private ArrayList<Connection> connections;

    public TrainManager() {
        this.stations = Station.readStationsFromFile("resources/stations.csv");
        this.connections = Connection.readConnectionsFromFile("resources/tracks.csv");
    }

    public ArrayList<Station> getStations() {
        return stations;
    }

    public ArrayList<Connection> getConnections() {
        return connections;
    }
}
