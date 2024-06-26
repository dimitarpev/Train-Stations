package utils;

import model.Station;

import java.util.ArrayList;

public class StationSearch {

    public static Station binarySearch(ArrayList<Station> stations, String searchName){
        int left = 0;
        int right = stations.size() - 1;

        while (left <= right) {
            int middle = left + (right - left) / 2;
            String middleName = stations.get(middle).getNameLong();

            int compareResult = searchName.compareTo(middleName);

            if (compareResult == 0) {
                return stations.get(middle);
            } else if (compareResult < 0) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return null;
    }

    public static Station linearSearch(ArrayList<Station> stations, String searchName){
        for (int i = 0; i < stations.size(); i++) {
            Station station = stations.get(i);
            if (station.getNameLong().equals(searchName)) return station;
        }
        return null;
    }
}
