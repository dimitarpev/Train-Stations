package graphs;

import maps.HashMap;
import maps.Pair;

import java.util.ArrayList;

public class AdjacencyListGraph<V> {
    private HashMap<V, ArrayList<Pair<V, Integer>>> adjacencyList;

    public AdjacencyListGraph() {

    }

    public void connect(V left, V right) {
        ArrayList<Pair<V, Integer>> leftList = adjacencyList.get(left);
        ArrayList<Pair<V, Integer>> rightList = adjacencyList.get(right);
        if (leftList == null){
            leftList = new ArrayList<>();
            adjacencyList.put(left, leftList);
        }
        if (rightList == null){
            rightList = new ArrayList<>();
            adjacencyList.put(right, rightList);
        }
        leftList.add(adjacencyList.get(right).get(rightList.indexOf(right)));
        rightList.add(adjacencyList.get(left).get(leftList.indexOf(left)));
        Pair<V, Integer> leftPair = new Pair<>(right, 0);  // You might want to adjust the weight (0 in this case).
        Pair<V, Integer> rightPair = new Pair<>(left, 0);

        leftList.add(leftPair);
        rightList.add(rightPair);
    }


}
