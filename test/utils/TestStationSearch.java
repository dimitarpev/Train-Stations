package utils;

import model.Station;
import model.TrainManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.StationSearch;

import static org.junit.jupiter.api.Assertions.*;

public class TestStationSearch {
    TrainManager tm;
    @BeforeEach
    public void creatingTrainManagerWithLoadedStations() {
        tm = new TrainManager();
    }

    @Test
    public void searchingForStationDeventerUsingBinarySearchReturnsItsIndexInTheArrayDifferentThanNegativeOneWhichMeansNotFound() {
        int i = StationSearch.binarySearch(tm.getStations(), "Deventer");
        assertNotEquals(-1, i);
    }

    @Test
    public void searchingForStationThatDoesNotExistUsingBinarySearchReturnsNegative1() {
        int i = StationSearch.binarySearch(tm.getStations(), "oisfhihos");
        assertEquals(-1, i);
    }

    @Test
    public void searchingForStationDeventerUsingLinearSearchReturnsStation() {
        Station deventer = StationSearch.linearSearch(tm.getStations(), "Deventer");
        assertEquals("Deventer", deventer.getNameLong());
    }

    @Test
    public void searchingForStationThatDoesNotExistUsingLinearSearchReturnsNull() {
        Station nonExistent = StationSearch.linearSearch(tm.getStations(), "oisfhihos");
        assertNull(nonExistent);
    }
}
