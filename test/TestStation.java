
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestStation {
    private Station deventer;
    @BeforeEach
    public void createDeventerStation(){
        deventer = new Station(100, "DEV", "123456", "Deventer", "Deventer", "NL", "station", 1, 1);
    }

    @Test
    public void testConstructorStoresDataCorrectly(){
        Station s = new Station(1, "a", "b", "c", "d", "e", "f", 1, 2);
        assertNotNull(s);
        assertEquals(1, s.getId());
        assertEquals("a", s.getCode());
        assertEquals("b", s.getUic());
        assertEquals("c", s.getNameShort());
        assertEquals("d", s.getNameLong());
        assertEquals("e", s.getCountry());
        assertEquals("f", s.getType());
        assertEquals(1, s.getGeoLat(), 0.00001);
        assertEquals(2, s.getGeoLng(), 0.00001);
    }

    @Test
    public void idCannotBeNegativeWhenCreatingAStation() {
        AssertionError error = assertThrows(AssertionError.class, () ->
                new Station(-1, "a", "b", "c", "d", "e", "f", 1, 2));
        assertTrue(error.getMessage().contains("Please provide a non-negative id"));
    }

    @Test
    public void codeCannotBeNullWhenCreatingAStation() {
        String code = null;
        AssertionError error = assertThrows(AssertionError.class, () ->
                new Station(1, code, "b", "c", "d", "e", "f", 1, 2));
        assertTrue(error.getMessage().contains("Code cannot be null"));
    }

    @Test
    public void codeCannotBeBlankWhenCreatingAStation() {
        String code = "   ";
        AssertionError error = assertThrows(AssertionError.class, () ->
                new Station(1, code, "b", "c", "d", "e", "f", 1, 2));
        assertTrue(error.getMessage().contains("Please provide a valid and a non-blank code"));
    }

    @Test
    public void nameCannotBeNullWhenCreatingAStation() {
        String name = null;
        AssertionError error = assertThrows(AssertionError.class, () ->
                new Station(1, "a", "b", "c", name, "e", "f", 1, 2));
        assertTrue(error.getMessage().contains("Long name cannot be null"));
    }

    @Test
    public void nameCannotBeBlankWhenCreatingAStation() {
        String name = "  ";
        AssertionError error = assertThrows(AssertionError.class, () ->
                new Station(1, "a", "b", "c", name, "e", "f", 1, 2));
        assertTrue(error.getMessage().contains("Please provide a valid and a non-blank long name"));
    }

    @Test
    public void countryCannotBeNullWhenCreatingAStation() {
        String country = null;
        AssertionError error = assertThrows(AssertionError.class, () ->
                new Station(1, "a", "b", "c", "d", country, "f", 1, 2));
        assertTrue(error.getMessage().contains("Country cannot be null"));
    }

    @Test
    public void countryCannotBeBlankWhenCreatingAStation() {
        String country = "";
        AssertionError error = assertThrows(AssertionError.class, () ->
                new Station(1, "a", "b", "c", "d", country, "f", 1, 2));
        assertTrue(error.getMessage().contains("Please provide a valid and a non-blank country"));
    }

    @Test
    public void typeCannotBeNullWhenCreatingAStation() {
        String type = null;
        AssertionError error = assertThrows(AssertionError.class, () ->
                new Station(1, "a", "b", "c", "d", "e", type, 1, 2));
        assertTrue(error.getMessage().contains("Type cannot be null"));
    }

    @Test
    public void typeCannotBeBlankWhenCreatingAStation() {
        String type = " ";
        AssertionError error = assertThrows(AssertionError.class, () ->
                new Station(1, "a", "b", "c", "d", "e", type, 1, 2));
        assertTrue(error.getMessage().contains("Please provide a valid and a non-blank type"));
    }

    @Test
    public void latitudeCannotBeLessThanNegative180WhenCreatingAStation() {
        double latitude = -181;
        AssertionError error = assertThrows(AssertionError.class, () ->
                new Station(1, "a", "b", "c", "d", "e", "f", latitude, 2));
        assertTrue(error.getMessage().contains(latitude + " is an out of range value for latitude"));
    }

    @Test
    public void latitudeCannotBeMoreThan180WhenCreatingAStation() {
        double latitude = 181;
        AssertionError error = assertThrows(AssertionError.class, () ->
                new Station(1, "a", "b", "c", "d", "e", "f", latitude, 2));
        assertTrue(error.getMessage().contains(latitude + " is an out of range value for latitude"));
    }

    @Test
    public void longitudeCannotBeLessThanNegative90WhenCreatingAStation() {
        double longitude = -91;
        AssertionError error = assertThrows(AssertionError.class, () ->
                new Station(1, "a", "b", "c", "d", "e", "f", 1, longitude));
        assertTrue(error.getMessage().contains(longitude + " is an out of range value for longitude"));
    }

    @Test
    public void longitudeCannotBeMoreThan90WhenCreatingAStation() {
        double longitude = 91;
        AssertionError error = assertThrows(AssertionError.class, () ->
                new Station(1, "a", "b", "c", "d", "e", "f", 1, longitude));
        assertTrue(error.getMessage().contains(longitude + " is an out of range value for longitude"));
    }
    
    @Test
    public void dataIsReadCorrectlyFromCSVFileWithStationsCreatedSuccessfully(){
        ArrayList<Station> stations = Station.readStationsFromFile("resources/stations.csv");
        Station firstStation = stations.get(0);
        assertNotNull(firstStation);
        assertEquals(266, firstStation.getId());
        assertEquals("HT", firstStation.getCode());
        assertEquals("8400319", firstStation.getUic());
        assertEquals("\"Den Bosch\"", firstStation.getNameShort());
        assertEquals("'s-Hertogenbosch", firstStation.getNameLong());
        assertEquals("NL", firstStation.getCountry());
        assertEquals("knooppuntIntercitystation", firstStation.getType());
        assertEquals(51.69048, firstStation.getGeoLat(), 0.00001);
        assertEquals(5.29362, firstStation.getGeoLng(), 0.00001);
    }

    @Test
    public void stationDataTriedToBeReadFromNonExistingFileThrowsException() {
        RuntimeException ex = assertThrows(RuntimeException.class, () -> Station.readStationsFromFile("nosuchfile.csv"));
        assertNotNull(ex);
    }

    @Test
    public void stationsAndConnectionArraysGetFilledSuccefullyWhenCreatingTrainManager() {
        TrainManager trainManager = new TrainManager();
        assertNotNull(trainManager.getStations());
        assertNotNull(trainManager.getConnections());
    }
}
