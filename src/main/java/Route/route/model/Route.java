package route.route.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import route.waypoint.model.Waypoint;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.LinkedList;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(staticName = "create")
public class Route {
    int id;
    String name;
    @ManyToMany
    @JoinTable(
            name = "waypoints",
            joinColumns = @JoinColumn(name = "route_id")
    )
    LinkedList<Waypoint> waypoints;

    public Route(String name, LinkedList<Waypoint> waypointMap) {
        this.name = name;
        this.waypoints = waypointMap;
    }

    public String generateRTZ() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("<?xml version=\"1.0\" encoding=\"UTF-8\"?>%n"));
        sb.append(String.format("<route xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.cirm.org/RTZ/1/0\" xsi:schemaLocation=\"http://www.cirm.org/rtz/RTZ Schema version 1_0.xsd\" version=\"1.0\">%n"));
        sb.append(String.format("<routeInfo routeName=\"%s\" />%n", name));
        sb.append(String.format("<waypoints>%n"));
        for (Waypoint wp : waypoints) {
            sb.append(wp.getTag());
        }
        sb.append(String.format("</waypoints>%n"));
        sb.append("</route>");
        return sb.toString();
    }

}
