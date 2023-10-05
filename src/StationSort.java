import lists.LinkedList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StationSort {
    public static LinkedList<Station> insertionSortStations(LinkedList<Station> stations) {
        int index = 1;
        int newIndex;
        Station element;
        while (index < stations.size()){
            element = stations.get(index);
            newIndex = index;
            while (newIndex > 0 && stations.get(newIndex - 1).getNameLong().compareTo(element.getNameLong()) > 0){
                stations.set(newIndex, stations.get(newIndex - 1));
                newIndex--;
            }
            stations.set(newIndex, element);
            index++;
        }
        return stations;
    }

    public static LinkedList<Station> mergeSortStations(LinkedList<Station> stations) {
        if (stations.size() <= 1) {
            return stations;
        }

        int middle = stations.size() / 2;
        LinkedList<Station> left = stations.subList(0, middle);
        LinkedList<Station> right = stations.subList(middle, stations.size());

        left = mergeSortStations(left);
        right = mergeSortStations(right);

        merge(left, right, stations);

        return stations;
    }

    private static void merge(LinkedList<Station> left, LinkedList<Station> right, LinkedList<Station> stations) {
        int leftIndex = 0;
        int rightIndex = 0;
        int stationsIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (left.get(leftIndex).getNameLong().compareTo(right.get(rightIndex).getNameLong()) <= 0) {
                stations.set(stationsIndex++, left.get(leftIndex++));
            } else {
                stations.set(stationsIndex++, right.get(rightIndex++));
            }
        }

        // Copy remaining elements from left, if any
        while (leftIndex < left.size()) {
            stations.set(stationsIndex++, left.get(leftIndex++));
        }

        // Copy remaining elements from right, if any
        while (rightIndex < right.size()) {
            stations.set(stationsIndex++, right.get(rightIndex++));
        }
    }
}

