import lists.LinkedList;

import java.util.ArrayList;
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
}
