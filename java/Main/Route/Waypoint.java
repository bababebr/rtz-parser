package Main.Route;

import Main.Tools.Coordinates;
import com.sun.istack.NotNull;

import java.io.Serializable;

public class Waypoint implements Serializable {
    private static final long serialVersionUID = -8087725925682445562L;
    private int id;
    private String name;
    private double radius;
    private double lat;
    private double lon;
    private double stbdXTD = 0d;
    private double portXTD = 0d;
    private String geometryType = "Loxodrome";
    private double speedMin = 0d;
    private double speedMax = 0d;

    private String legNote;

    private Coordinates position;


    /**
     * Empty Constructor
     */
    public Waypoint() {

    }
    /**
     * Parsing waypoint tag to the waypoint java object
     * @param wp_tag Waypoint tag from the RTZ route file
     */
    public Waypoint(@NotNull String wp_tag) {
        if(!wp_tag.trim().startsWith("<waypoint")) {
            System.err.print("Wrong waypoint object.");
        }
        try {

            int pos = 0;
            if (wp_tag.contains("id=")) {
                pos = wp_tag.indexOf("id=") + 4;
                id = Integer.parseInt(wp_tag.substring(pos, wp_tag.indexOf("\"", pos)));
            }
            if (wp_tag.contains("name=")) {
                pos = wp_tag.indexOf("name=") + 6;
                name = wp_tag.substring(pos, wp_tag.indexOf("\"", pos));
            }

            if (wp_tag.contains("radius=")) {
                pos = wp_tag.indexOf("radius=") + 8;
                radius = Double.valueOf(wp_tag.substring(pos, wp_tag.indexOf("\"", pos)));
            }
            if (wp_tag.contains("lat=")) {
                pos = wp_tag.indexOf("lat=") + 5;
                lat = Double.valueOf(wp_tag.substring(pos, wp_tag.indexOf("\"", pos)));
            }

            if (wp_tag.contains("lon=")) {
                pos = wp_tag.indexOf("lon=") + 5;
                lon = Double.valueOf(wp_tag.substring(pos, wp_tag.indexOf("\"", pos)));
            }

            if (wp_tag.contains("starboardXTD=")) {
                pos = wp_tag.indexOf("starboardXTD=") + 14;
                stbdXTD = Double.valueOf(wp_tag.substring(pos, wp_tag.indexOf("\"", pos)));
            }
            if (wp_tag.contains("portsideXTD=")) {
                pos = wp_tag.indexOf("portsideXTD=") + 13;
                portXTD = Double.valueOf(wp_tag.substring(pos, wp_tag.indexOf("\"", pos)));
            }
            if (wp_tag.contains("geometryType=")) {
                pos = wp_tag.indexOf("geometryType=") + 14;
                geometryType = wp_tag.substring(pos, wp_tag.indexOf("\"", pos));
            }
            if (wp_tag.contains("speedMin=")) {
                pos = wp_tag.indexOf("speedMin=") + 10;
                speedMin = Double.valueOf(wp_tag.substring(pos, wp_tag.indexOf("\"", pos)));
            }
            if (wp_tag.contains("speedMax=")) {
                pos = wp_tag.indexOf("speedMax=") + 10;
                speedMax = Double.valueOf(wp_tag.substring(pos, wp_tag.indexOf("\"", pos)));
            }
            position = new Coordinates(lat, lon);
            pos = wp_tag.indexOf("legNote2=") + 10;
            legNote = wp_tag.substring(pos, wp_tag.indexOf("\"", pos));
        }
        catch (Exception e){

        }
    }
    /**
     * Generate Waypoint tag as per RTZ specification requirements
     * Used for the Route export to the RTZ format
     */
    public String generateWaypointTag(){
        String s = String.format("<waypoint id=\"%d\" revision=\"%d\" name=\"%s\" radius=\"%f\">%n" +
                "<position lat=\"%f\" lon=\"%f\" />%n"+
                "<leg starboardXTD=\"%f\" portsideXTD=\"%f\" geometryType=\"%s\" speedMin=\"%f\" speedMax=\"%f\" " +
                "legNote2=\"%s\" />%n" +
                "</waypoint>%n",id,id,name,radius,lat,lon,stbdXTD,portXTD,geometryType,speedMin,speedMax,legNote);
        return s;
    }
    /**
     *Default function (Getters, Setters, toString...etc)
     */
    @Override
    public String toString() {
        return "Waypoint{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", radius=" + radius +
                ", lat=" + lat +
                ", lon=" + lon +
                ", stbdXTD=" + stbdXTD +
                ", portXTD=" + portXTD +
                ", geometryType='" + geometryType + '\'' +
                ", speedMin=" + speedMin +
                ", speedMax=" + speedMax +
                ", position=" + position +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getStbdXTD() {
        return stbdXTD;
    }

    public void setStbdXTD(double stbdXTD) {
        this.stbdXTD = stbdXTD;
    }

    public double getPortXTD() {
        return portXTD;
    }

    public void setPortXTD(double portXTD) {
        this.portXTD = portXTD;
    }

    public String getGeometryType() {
        return geometryType;
    }

    public void setGeometryType(String geometryType) {
        this.geometryType = geometryType;
    }

    public double getSpeedMin() {
        return speedMin;
    }

    public void setSpeedMin(double speedMin) {
        this.speedMin = speedMin;
    }

    public double getSpeedMax() {
        return speedMax;
    }

    public void setSpeedMax(double speedMax) {
        this.speedMax = speedMax;
    }

    public Coordinates getPosition() {
        return position;
    }

    public void setPosition(Coordinates position) {
        this.position = position;
    }

    public String getLegNote() {
        return legNote;
    }

    public void setLegNote(String legNote) {
        this.legNote = legNote;
    }
}
