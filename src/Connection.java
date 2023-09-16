import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Connection {

    private String station1;
    private String station2;

    public Connection(String station1, String station2) {
        this.station1 = station1;
        this.station2 = station2;
    }

    public static ArrayList<Connection> readConnectionsFromFile(String fileName) {
        Scanner scanner = null;
        ArrayList<Connection> result = new ArrayList<>();
        try {
            scanner = new Scanner(new File(fileName));
            scanner.nextLine();
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] lineParse = line.split(",");

                String station1 = lineParse[0];
                String station2 = lineParse[1];

                result.add(new Connection(station1, station2));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public String getStation1() {
        return station1;
    }

    public void setStation1(String station1) {
        this.station1 = station1;
    }

    public String getStation2() {
        return station2;
    }

    public void setStation2(String station2) {
        this.station2 = station2;
    }
}
