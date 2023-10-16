package graphs;

import model.Station;
import model.TrainManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestMatrixGraph {

    private MatrixGraph<Integer> matrixGraph;
    private TrainManager trainManager = new TrainManager();
    @BeforeEach
    public void readStationsTakingTheirCodeAndConnectThem() {
        ArrayList<String> stationCodes = new ArrayList<>();
        for (Station station: trainManager.getStations()){
            stationCodes.add(station.getCode());
        }

        matrixGraph = new MatrixGraph<>(false, new ArrayList<>(List.of(0,1,2,3,4,5)));
    }

    @Test
    public void connectVerticesAndSeeIfTheyAreConnectedAndWeighted() {
        matrixGraph.connect(1, 2,10);
        matrixGraph.connect(2, 3, 20);
        matrixGraph.connect(3, 4, 30);

        assertTrue(matrixGraph.isConnected(1, 2));
        assertTrue(matrixGraph.isConnected(2, 1));

        assertTrue(matrixGraph.isConnected(2, 3));
        assertTrue(matrixGraph.isConnected(3, 2));

        assertTrue(matrixGraph.isConnected(3, 4));
        assertTrue(matrixGraph.isConnected(4, 3));

        assertFalse(matrixGraph.isConnected(1, 3));
        assertFalse(matrixGraph.isConnected(1, 4));
        assertFalse(matrixGraph.isConnected(2, 4));

        assertEquals(10,matrixGraph.getWeight(1, 2));
        assertEquals(20, matrixGraph.getWeight(2, 3));
        assertEquals(30, matrixGraph.getWeight(3, 4));
    }

    @Test
    public void breadthFirstIrritationOfConnectedMatrixGraphIsCorrect() {
        matrixGraph.connect(0, 1,1);
        matrixGraph.connect(0, 2, 1);
        matrixGraph.connect(1, 3, 1);
        matrixGraph.connect(2, 3, 1);
        matrixGraph.connect(3, 4, 1);
        matrixGraph.connect(4, 5, 1);

        Set<Integer> expectedResult = new HashSet<>(List.of(2, 0, 3, 1, 4, 5));
        assertEquals(expectedResult, matrixGraph.breadthFirst(2));
    }

    @Test
    public void depthFirstIrritationOfConnectedMatrixGraphIsCorrect() {
        matrixGraph.connect(0, 1,1);
        matrixGraph.connect(0, 2, 1);
        matrixGraph.connect(1, 3, 1);
        matrixGraph.connect(2, 3, 1);
        matrixGraph.connect(3, 4, 1);
        matrixGraph.connect(4, 5, 1);

        Set<Integer> expectedResult = new HashSet<>(List.of(2, 0, 1, 3, 4, 5));
        assertEquals(expectedResult, matrixGraph.depthFirst(2));
    }


}
