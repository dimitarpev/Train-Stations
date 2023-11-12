package utils;

import model.Station;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestPathfinding {

    private ArrayList<Station> stations = Station.readStationsFromFile("resources/stations.csv");
    private Station stationDeventer = null;
    private Station stationZutphen = null;
    private Station stationApeldoorn = null;
    private Station stationNewAmsterdam = null;
    private Station stationZwolle = null;
    private Station stationNijmigen = null;
    private Station stationGroningen = null;
    private Station stationRotterdam = null;
    private Station stationUtrecht = null;
    private Station stationOmmen = null;
    @BeforeEach
    public void getAFewStationsToTest() {
        stationDeventer = stations.stream()
                .filter(station -> station.getCode().equals("DV"))
                .toList()
                .get(0);
        stationZutphen = stations.stream()
                .filter(station -> station.getCode().equals("ZP"))
                .toList()
                .get(0);
        stationApeldoorn = stations.stream()
                .filter(station -> station.getCode().equals("APD"))
                .toList()
                .get(0);
        stationNewAmsterdam = stations.stream()
                .filter(station -> station.getCode().equals("NA"))
                .toList()
                .get(0);
        stationZwolle = stations.stream()
                .filter(station -> station.getCode().equals("ZL"))
                .toList()
                .get(0);
        stationNijmigen = stations.stream()
                .filter(station -> station.getCode().equals("NM"))
                .toList()
                .get(0);
        stationGroningen = stations.stream()
                .filter(station -> station.getCode().equals("GN"))
                .toList()
                .get(0);
        stationRotterdam = stations.stream()
                .filter(station -> station.getCode().equals("RTA"))
                .toList()
                .get(0);
        stationUtrecht = stations.stream()
                .filter(station -> station.getCode().equals("UT"))
                .toList()
                .get(0);
        stationOmmen = stations.stream()
                .filter(station -> station.getCode().equals("OMN"))
                .toList()
                .get(0);
    }

    @Test
    public void findPathBetweenTwoConnectedStations() {
        List<Coordinates> path = Pathfinding.findPathAStar(stationDeventer, stationZutphen);
        assertNotNull(path);
        String firstCoord = path.get(0).toString();
        String lastCoord = path.get(path.size()-1).toString();
        assertTrue(firstCoord.contains("DV") && lastCoord.contains("ZP") && lastCoord.contains("16"));
        Pathfinding.printPathResultWithWeight(path);
    }

    @Test
    public void findPathBetweenTwoDistancedStationsButNotALot() {
        List<Coordinates> path = Pathfinding.findPathAStar(stationDeventer, stationApeldoorn);
        assertNotNull(path);
        String firstCoord = path.get(0).toString();
        String lastCoord = path.get(path.size()-1).toString();
        assertTrue(firstCoord.contains("DV") && lastCoord.contains("APD") && lastCoord.contains("15"));
        //uncomment line below to print result
        //Pathfinding.printPathResultWithWeight(path);
    }

    @Test
    public void findPathBetweenTwoFarStations() {
        List<Coordinates> path = Pathfinding.findPathAStar(stationOmmen, stationUtrecht);
        assertNotNull(path);
        String firstCoord = path.get(0).toString();
        String lastCoord = path.get(path.size()-1).toString();
        assertTrue(firstCoord.contains("OMN") && lastCoord.contains("UT") && lastCoord.contains("110"));
        //uncomment line below to print result
        //Pathfinding.printPathResultWithWeight(path);
    }

    //Test commented out as assertion was removed - but not deleted to show that it has been thought of.
    @Test
    public void tryingToFindPathBetweenTwoStationsThatAreNotConnectedReturnsNull() {
        Station s1 = new Station(1, "aaaa", "b", "c", "d", "e", "f", 1, 2);
        List<Coordinates> path = Pathfinding.findPathAStar(s1, stationDeventer);
        assertNull(path);
    }

    @Test
    public void dijkstraAlgorithmFromStationDeventer() {
        Set<DijkstraVertex> shortPathDeventerStations = Pathfinding.findPathDijkstra(stationDeventer);
        assertFalse(shortPathDeventerStations.isEmpty());
        Pathfinding.printDijkstraResult(shortPathDeventerStations);
    }

}
