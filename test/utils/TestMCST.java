package utils;

import graphs.MatrixGraph;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestMCST {

    MatrixGraph<String> graph = MatrixGraph.addConnectionsToMatrixGraph("resources/tracks.csv");
    @Test
    public void createAMinimumCostSpanningTreeForAllStationsWithPrimAlgorithm() {
        Set<String> minimumSpanningTree = MinimumCostSpanningTree.primAlgorithm(graph);
        assertFalse(minimumSpanningTree.isEmpty());
        //uncomment line below to print result
        //MinimumCostSpanningTree.printMinimumSpanningTree(graph, minimumSpanningTree);
    }

    @Test
    public void testMCSTUsingPrimWithSmallGraph() {
        List<String> vertices = Arrays.asList("A", "B", "C", "D");

        MatrixGraph<String> graph = new MatrixGraph<>(false, vertices);

        graph.connect("A", "B", 1);
        graph.connect("A", "C", 3);
        graph.connect("B", "C", 2);
        graph.connect("B", "D", 5);
        graph.connect("C", "D", 4);

        graph.connect("B", "A", 1);
        graph.connect("C", "A", 3);
        graph.connect("C", "B", 2);
        graph.connect("D", "B", 5);
        graph.connect("D", "C", 4);

        Set<String> minimumSpanningTree = MinimumCostSpanningTree.primAlgorithm(graph);
        assertFalse(minimumSpanningTree.isEmpty());
        assertTrue(minimumSpanningTree.contains("A") && minimumSpanningTree.contains("D"));
        //uncomment line below to print result
        //MinimumCostSpanningTree.printMinimumSpanningTree(graph, minimumSpanningTree);
    }

    @Test
    public void connectMatrixAndTestSheetsExample() {
        List<String> vertices = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I");
        MatrixGraph<String> graph = new MatrixGraph<>(false, vertices);

        graph.connect("A", "B", 4);
        graph.connect("A", "H", 8);
        graph.connect("B", "H", 11);
        graph.connect("B", "C", 8);
        graph.connect("H", "I", 7);
        graph.connect("H", "G", 1);

        graph.connect("C", "I", 2);
        graph.connect("C", "D", 7);
        graph.connect("C", "F", 4);
        graph.connect("I", "G", 6);
        graph.connect("G", "F", 2);
        graph.connect("D", "F", 14);
        graph.connect("D", "E", 9);
        graph.connect("F", "E", 10);
        Set<String> minimumSpanningTree = MinimumCostSpanningTree.primAlgorithm(graph);
        assertFalse(minimumSpanningTree.isEmpty());
        assertTrue(minimumSpanningTree.contains("A") && minimumSpanningTree.contains("I"));
        MinimumCostSpanningTree.printMinimumSpanningTree(graph, minimumSpanningTree);
    }

    @Test
    public void determineMCSTByRectangleUsingStationApeldoornAndDeventer(){
        Set<String> result = MinimumCostSpanningTree.determineMCSTByRectangle(graph, "APD", "DV");
        assertTrue(result.contains("DV") && result.contains("APDO") && result.contains("APD") && result.contains("TWL"));
        assertFalse(result.contains("DVC"));
    }

    @Test
    public void tryingToDetermineMCSTByRectangleUsingNonExistentStationsGivesEmptyResult() {
        Set<String> result = MinimumCostSpanningTree.determineMCSTByRectangle(graph, "aaaaa", "bbbbb");
        assertTrue(result.isEmpty());
    }

}
