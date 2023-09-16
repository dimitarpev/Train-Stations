import java.util.ArrayList;

public class Main {



    public static void main(String[] args) {
        TrainManager trainManager = new TrainManager();

        for (Station station : trainManager.getStations()){
            System.out.println(station.getNameLong());
        }
        for (Connection connection : trainManager.getConnections()) {
            System.out.println(connection.getStation1());
        }
    }
}
