package utils;

import java.util.Objects;

public class Coordinates {

    private final String code;
    private final double lat;
    private final double lng;
    private int gCost;
    private int hCost;
    private Coordinates parent;

    public Coordinates(String code, double lat, double lng) {
        this.code = code;
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Coordinates that = (Coordinates) obj;
        return Double.compare(that.lat, lat) == 0 &&
                Double.compare(that.lng, lng) == 0 &&
                Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lat, lng, code);
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getCode() {
        return code;
    }

    public int getFCost() {
        return gCost + hCost;
    }

    public int getGCost() {
        return gCost;
    }

    public void setGCost(int gCost) {
        this.gCost = gCost;
    }

    public void setHCost(int hCost) {
        this.hCost = hCost;
    }

    public Coordinates getParent() {
        return parent;
    }

    public void setParent(Coordinates parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "code='" + code + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", gCost=" + gCost +
                '}';
    }
}

