package utils;

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

    public int getHCost() {
        return hCost;
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
}

