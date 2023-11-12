package utils;

import model.Station;

import java.util.ArrayList;

public class StationSort {
    public static ArrayList<Station> insertionSortStations(ArrayList<Station> stations) {
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

    public static ArrayList<Station> mergeSortStations(ArrayList<Station> stations) {
        if (stations.size() <= 1) {
            return stations;
        }

        int middle = stations.size() / 2;

        ArrayList<Station> left = new ArrayList<>(stations.subList(0, middle));
        ArrayList<Station> right = new ArrayList<>(stations.subList(middle, stations.size()));

        left = mergeSortStations(left);
        right = mergeSortStations(right);

        merge(left, right, stations);

        return stations;
    }

    private static void merge(ArrayList<Station> left, ArrayList<Station> right, ArrayList<Station> stations) {
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
        while (leftIndex < left.size()) {
            stations.set(stationsIndex++, left.get(leftIndex++));
        }
        while (rightIndex < right.size()) {
            stations.set(stationsIndex++, right.get(rightIndex++));
        }
    }
}

