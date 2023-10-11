package route.route.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import route.leg.repository.model.Leg;
import route.waypoint.model.Waypoint;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(staticName = "create")
public class RouteDto {
    int id;
    String name;
    Map<Integer, Waypoint> waypointsMap;
    List<Leg> legList;

    public RouteDto(String name, TreeMap<Integer, Waypoint> waypointMap) {
        this.name = name;
        this.waypointsMap = waypointMap;
    }

    public String generateRTZ() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("<?xml version=\"1.0\" encoding=\"UTF-8\"?>%n"));
        sb.append(String.format("<route xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.cirm.org/RTZ/1/0\" xsi:schemaLocation=\"http://www.cirm.org/rtz/RTZ Schema version 1_0.xsd\" version=\"1.0\">%n"));
        sb.append(String.format("<routeInfo routeName=\"%s\" />%n", name));
        sb.append(String.format("<waypoints>%n"));
        for (Waypoint wp : waypointsMap.values()) {
            sb.append(wp.getTag());
        }
        sb.append(String.format("</waypoints>%n"));
        sb.append("</route>");
        return sb.toString();
    }
}
