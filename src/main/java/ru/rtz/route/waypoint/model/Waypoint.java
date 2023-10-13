package ru.rtz.route.waypoint.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.rtz.enums.LegType;
import ru.rtz.route.waypoint.repository.WaypointId;
import ru.rtz.units.Coordinates;

import javax.persistence.*;

@Entity
@Table(name = "waypoints")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@EqualsAndHashCode
public class Waypoint {
    @EmbeddedId
    WaypointId id;
    String name;
    double radius;
    double lat;
    double lon;
    double stbdXTD = 0d;
    double portXTD = 0d;
    @Enumerated(EnumType.STRING)
    LegType legType = LegType.Loxodrome;
    double speedMin = 0d;
    double speedMax = 0d;
    String legNote;
    @Transient
    Coordinates position;

    /**
     * Generate Waypoint tag as per ru.rtz.RTZ specification requirements
     * Used for the Route export to the ru.rtz.RTZ format
     */
    public String getTag() {
        String s = String.format("<waypoint id=\"%d\" revision=\"%d\" name=\"%s\" radius=\"%f\">%n" +
                "<position lat=\"%f\" lon=\"%f\" />%n" +
                "<leg starboardXTD=\"%f\" portsideXTD=\"%f\" geometryType=\"%s\" speedMin=\"%f\" speedMax=\"%f\" " +
                "legNote2=\"%s\" />%n" +
                "</waypoint>%n", id, id, name, radius, lat, lon, stbdXTD, portXTD, LegType.Loxodrome, speedMin, speedMax, legNote);
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
