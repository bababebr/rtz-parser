package ru.rtz.route.route.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.rtz.route.leg.model.Leg;
import ru.rtz.route.waypoint.model.WaypointDto;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(staticName = "create")
@AllArgsConstructor(staticName = "create")
public class RouteDto {
    Long id;
    String name;
    List<WaypointDto> waypoints;
    List<Leg> legList;

    public RouteDto(String name, List<WaypointDto> waypoints) {
        this.name = name;
        this.waypoints = waypoints;
    }

    public String generateRTZ() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("<?xml version=\"1.0\" encoding=\"UTF-8\"?>%n"));
        sb.append(String.format("<ru.rtz.route xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.cirm.org/RTZ/1/0\" xsi:schemaLocation=\"http://www.cirm.org/rtz/RTZ Schema version 1_0.xsd\" version=\"1.0\">%n"));
        sb.append(String.format("<routeInfo routeName=\"%s\" />%n", name));
        sb.append(String.format("<waypoints>%n"));
        for (WaypointDto wp : waypoints) {
            //TODO Fix getTag
            sb.append(wp.getId());
        }
        sb.append(String.format("</waypoints>%n"));
        sb.append("</ru.rtz.route>");
        return sb.toString();
    }
}
