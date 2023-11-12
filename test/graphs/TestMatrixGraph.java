package graphs;

import model.Station;
import model.TrainManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
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

    @Test
    public void addAVertexToAGraph(){
        matrixGraph.add(10);
        assertTrue(matrixGraph.contains(10));
    }

    @Test
    public void removeAVertexFromAGraph(){
        assertTrue(matrixGraph.remove(3));
        matrixGraph.remove(3);
        assertFalse(matrixGraph.contains(3));
    }

    @Test
    public void getSizeOfAGraph() {
        assertEquals(6, matrixGraph.size());
        String toString = matrixGraph.toString();
        assertNotNull(toString);
    }

    @Test
    public void gettingTheWeightOfTwoVertices() {
        matrixGraph.connect(0, 1,1);
        assertEquals(1, matrixGraph.getWeight(0, 1));
    }

    @Test
    public void tryingToGetWeightOfVertixThatDoesNotExistGivesError() {
        matrixGraph.connect(0, 1,1);
        AssertionError error = assertThrows(AssertionError.class, () -> matrixGraph.getWeight(8, 9));
        assertEquals("You can only get the weight of vertices that exist", error.getMessage());
    }


//Test commented out as assertion was removed - but not deleted to show that it has been thought of.
//    @Test
//    public void tryingToGetNeighborsOfVertixThatDoesNotExistGivesError() {
//        matrixGraph.connect(0, 1,1);
//        AssertionError error = assertThrows(AssertionError.class, () -> matrixGraph.getNeighbors(10));
//        assertEquals("You can only get the neighbor of a vertex that exists", error.getMessage());
//    }

    @Test
    public void tryingToAddConnectionsToMatrixGraphUsingANonExistingFileGivesError() {
        assertThrows(RuntimeException.class, () -> MatrixGraph.addConnectionsToMatrixGraph("nofile"));
    }

    @Test
    public void getWebGraphVizInputFromMatrixGraph() {
        matrixGraph.connect(0, 1,1);
        matrixGraph.connect(0, 2, 1);
        matrixGraph.connect(1, 3, 1);
        matrixGraph.connect(2, 3, 1);
        matrixGraph.connect(3, 4, 1);
        matrixGraph.connect(4, 5, 1);

        String webViz = matrixGraph.toWebGraphViz();
        assertEquals("digraph G {\n" +
                "\"0\" -> \"1\"\n" +
                "\"0\" -> \"2\"\n" +
                "\"1\" -> \"0\"\n" +
                "\"1\" -> \"3\"\n" +
                "\"2\" -> \"0\"\n" +
                "\"2\" -> \"3\"\n" +
                "\"3\" -> \"1\"\n" +
                "\"3\" -> \"2\"\n" +
                "\"3\" -> \"4\"\n" +
                "\"4\" -> \"3\"\n" +
                "\"4\" -> \"5\"\n" +
                "\"5\" -> \"4\"\n" +
                "}", webViz);
    }

    @Test
    public void setEdgeIncludedShouldSetWeightAndReverseWeightToNull() {
        matrixGraph.connect(1, 2, 10);
        matrixGraph.connect(2, 1, 10);

        assertTrue(matrixGraph.isConnected(1, 2));
        assertTrue(matrixGraph.isConnected(2, 1));

        matrixGraph.setEdgeIncluded(1, 2, false, 0);

        assertFalse(matrixGraph.isConnected(1, 2));
        assertFalse(matrixGraph.isConnected(2, 1));

        assertEquals(0, matrixGraph.getWeight(1, 2));
        assertEquals(0, matrixGraph.getWeight(2, 1));
    }

}
