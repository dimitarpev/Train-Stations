package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Station {

    private final int id;
    private final String code;
    private final String uic;
    private final String nameShort;
    private final String nameLong;
    private final String country;
    private final String type;
    private final double geoLat;
    private final double geoLng;

    public Station(int id, String code, String uic, String nameShort, String nameLong, String country, String type, double geoLat, double geoLng) {
        assert id >= 0 : "Please provide a non-negative id";
        this.id = id;

        assert code != null : "Code cannot be null";
        assert !code.isBlank() : "Please provide a valid and a non-blank code";
        this.code = code;

        assert uic != null : "UIC cannot be null";
        assert !uic.isBlank() : "Please provide a valid UIC";
        this.uic = uic;

        assert nameShort != null : "Short name cannot be null";
        assert  !nameShort.isBlank() : "Please provide a valid short name";
        this.nameShort = nameShort;

        assert nameLong != null : "Long name cannot be null";
        assert  !nameLong.isBlank() : "Please provide a valid and a non-blank long name";
        this.nameLong = nameLong;

        assert country != null : "Country cannot be null";
        assert  !country.isBlank() : "Please provide a valid and a non-blank country";
        this.country = country;

        assert type != null : "Type cannot be null";
        assert  !type.isBlank() : "Please provide a valid and a non-blank type";
        this.type = type;

        assert geoLat >= -180 && geoLat <= 180 : geoLat + " is an out of range value for latitude";
        this.geoLat = geoLat;

        assert geoLng >= -90 && geoLng <= 90 : geoLng + " is an out of range value for longitude";
        this.geoLng = geoLng;
    }

    public static ArrayList<Station> readStationsFromFile(String fileName) {
        Scanner scanner;
        ArrayList<Station> result = new ArrayList<>();
        try {
            scanner = new Scanner(new File(fileName));
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] lineParse = line.split(",");
                for (int i = 0; i < lineParse.length; i++) {
                    lineParse[i] = lineParse[i].replace("\"", "").trim();
                }

                int id = Integer.parseInt(lineParse[0]);

                String code = lineParse[1];
                if (!code.matches("[A-Z]*")) break;

                String uic = lineParse[2];
                if (uic.matches("\"[78][0-9]*\"")) break;

                String nameShort = lineParse[3];
                String nameLong = lineParse[5];

                String country = lineParse[7];
                if (!country.matches("[A-Z]*")) break;

                String type = lineParse[8];

                double geoLat = Double.parseDouble(lineParse[9]);
                if(!String.valueOf(geoLat).matches("-?[0-9]*[.]?[0-9]*")) break;

                double geoLng = Double.parseDouble(lineParse[10]);
                if (!String.valueOf(geoLng).matches("-?[0-9]*[.]?[0-9]*")) break;

                result.add(new Station(id, code, uic, nameShort, nameLong, country, type, geoLat, geoLng));
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getUic() {
        return uic;
    }

    public String getNameShort() {
        return nameShort;
    }

    public String getNameLong() {
        return nameLong;
    }

    public String getCountry() {
        return country;
    }

    public String getType() {
        return type;
    }

    public double getGeoLat() {
        return geoLat;
    }

    public double getGeoLng() {
        return geoLng;
    }

    @Override
    public String toString() {
        return "Station{" +
                "code='" + code + '\'' +
                ", nameLong='" + nameLong + '\'' +
                ", country='" + country + '\'' +
                ", type='" + type + '\'' +
                ", geoLat=" + geoLat +
                ", geoLng=" + geoLng +
                '}';
    }
}