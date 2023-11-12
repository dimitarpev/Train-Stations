package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Connection {

    private final Station station1;
    private final Station station2;
    private final int distance;

    public Connection(Station station1, Station station2, int distance) {
        this.station1 = station1;
        this.station2 = station2;
        this.distance = distance;
    }

    public static ArrayList<Connection> readConnectionsFromFile(String fileName, ArrayList<Station> stations) {
        Scanner scanner = null;
        ArrayList<Connection> result = new ArrayList<>();
        try {
            scanner = new Scanner(new File(fileName));
            scanner.nextLine();
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] lineParse = line.split(",");

                String station1Code = lineParse[0];
                if (!station1Code.matches("[a-z]*")) break;
                String station2Code = lineParse[1];
                if (!station2Code.matches("[a-z]*")) break;
                Station station1 = getStationWithCode(stations, station1Code);
                Station station2 = getStationWithCode(stations, station2Code);

                int distance = Integer.parseInt(lineParse[2]);
                if (!String.valueOf(distance).matches("[0-9]*")) break;

                result.add(new Connection(station1, station2, distance));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public static Station getStationWithCode(ArrayList<Station> stations, String code){
        for (Station station: stations) {
            if (station.getCode().equals(code)) {
                return station;
            }
        }
        return null;
    }

    public Station getStation1() {
        return station1;
    }

    public Station getStation2() {
        return station2;
    }

    public int getDistance() {
        return distance;
    }

}
