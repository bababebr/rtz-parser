package ru.rtz.route.route.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import ru.rtz.route.waypoint.model.Waypoint;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ROUTES")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @ManyToMany
    @JoinTable(
            name = "waypoints",
            joinColumns = @JoinColumn(name = "route_id")
    )
    List<Waypoint> waypoints;

    public Route(String name, List<Waypoint> waypointMap) {
        this.name = name;
        this.waypoints = waypointMap;
    }

    public String generateRTZ() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("<?xml version=\"1.0\" encoding=\"UTF-8\"?>%n"));
        sb.append(String.format("<ru.rtz.route xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.cirm.org/RTZ/1/0\" xsi:schemaLocation=\"http://www.cirm.org/rtz/RTZ Schema version 1_0.xsd\" version=\"1.0\">%n"));
        sb.append(String.format("<routeInfo routeName=\"%s\" />%n", name));
        sb.append(String.format("<waypoints>%n"));
        for (Waypoint wp : waypoints) {
            sb.append(wp.getTag());
        }
        sb.append(String.format("</waypoints>%n"));
        sb.append("</ru.rtz.route>");
        return sb.toString();
    }

}
