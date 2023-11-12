package utils;

import java.util.LinkedList;
import java.util.List;


public class DijkstraVertex implements Comparable<DijkstraVertex> {
    private String code;
    private Integer distance = Integer.MAX_VALUE;
    private List<DijkstraVertex> shortestPath = new LinkedList<>();

    public DijkstraVertex(String code) {
        this.code = code;
    }

    @Override
    public int compareTo(DijkstraVertex vertex) {
        return Integer.compare(this.distance, vertex.getDistance());
    }

    public String getCode() {
        return code;
    }

    public Integer getDistance() {
        return distance;
    }

    public List<DijkstraVertex> getShortestPath() {
        return shortestPath;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public void setShortestPath(List<DijkstraVertex> shortestPath) {
        this.shortestPath = shortestPath;
    }


}
