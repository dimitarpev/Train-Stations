package utils;

import lists.LinkedList;
import model.Station;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.StationSort;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestStationSort {
    ArrayList<Station> stations;
    @BeforeEach
    public void createListOf4StationsWhichAreNotSorted() {
        stations = new ArrayList<>();
        Station s1 = new Station(1, "a", "b", "c", "b", "e", "f", 1.1, 1.1 );
        Station s2 = new Station(2, "a", "b", "c", "d", "e", "f", 1.1, 1.1 );
        Station s3 = new Station(3, "a", "b", "c", "a", "e", "f", 1.1, 1.1 );
        Station s4 = new Station(4, "a", "b", "c", "c", "e", "f", 1.1, 1.1 );
        stations.add(s1);
        stations.add(s2);
        stations.add(s3);
        stations.add(s4);
    }

    @Test
    public void insertionSortCorrectlySortsStationsDepenndingOnTheirName() {
        ArrayList<Station> result = StationSort.insertionSortStations(stations);
        assertEquals("a", result.get(0).getNameLong());
        assertEquals("b", result.get(1).getNameLong());
        assertEquals("c", result.get(2).getNameLong());
        assertEquals("d", result.get(3).getNameLong());
    }

    @Test
    public void mergeSortCorrectlySortsStationsDependingOnTheirName() {
        ArrayList<Station> result = StationSort.mergeSortStations(stations);
        assertEquals("a", result.get(0).getNameLong());
        assertEquals("b", result.get(1).getNameLong());
        assertEquals("c", result.get(2).getNameLong());
        assertEquals("d", result.get(3).getNameLong());
    }
}
