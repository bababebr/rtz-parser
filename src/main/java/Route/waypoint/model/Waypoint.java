package route.waypoint.model;

import enums.LEG_TYPE;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import route.waypoint.repository.WaypointId;
import units.Coordinates;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "waypoint")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class Waypoint {
    @EmbeddedId
    WaypointId id;
    String name;
    double radius;
    double lat;
    double lon;
    double stbdXTD = 0d;
    double portXTD = 0d;
    LEG_TYPE legType = LEG_TYPE.Loxodrome;
    double speedMin = 0d;
    double speedMax = 0d;
    String legNote;
    @Transient
    Coordinates position;

    /**
     * Generate Waypoint tag as per RTZ specification requirements
     * Used for the Route export to the RTZ format
     */
    public String getTag() {
        String s = String.format("<waypoint id=\"%d\" revision=\"%d\" name=\"%s\" radius=\"%f\">%n" +
                "<position lat=\"%f\" lon=\"%f\" />%n" +
                "<leg starboardXTD=\"%f\" portsideXTD=\"%f\" geometryType=\"%s\" speedMin=\"%f\" speedMax=\"%f\" " +
                "legNote2=\"%s\" />%n" +
                "</waypoint>%n", id, id, name, radius, lat, lon, stbdXTD, portXTD, LEG_TYPE.Loxodrome, speedMin, speedMax, legNote);
        return s;
    }

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
                ", geometryType='" + legType + '\'' +
                ", speedMin=" + speedMin +
                ", speedMax=" + speedMax +
                ", position=" + position +
                '}';
    }
}
