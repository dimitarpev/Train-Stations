package utils;

import model.Station;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Set;

public class TestPathfinding {

    private ArrayList<Station> stations = Station.readStationsFromFile("resources/stations.csv");
    private Station stationDeventer = null;
    private Station stationApeldoorn = null;
    @BeforeEach
    public void getAFewStationsToTest() {
        stationDeventer = stations.stream()
                .filter(station -> station.getCode().equals("DV"))
                .toList()
                .get(0);
        stationApeldoorn = stations.stream()
                .filter(station -> station.getCode().equals("ZP"))
                .toList()
                .get(0);
    }

    @Test
    public void findPathBetweenTwoConnectedStations() {
        Set<Station> path = Pathfinding.findPathAStar(stationDeventer, stationApeldoorn);
        for (Station station : path){
            System.out.println(station.getNameLong());
        }
    }

}
