package ru.rtz.route.waypoint;

import ru.rtz.route.waypoint.model.Waypoint;
import ru.rtz.route.waypoint.model.WaypointDto;
import ru.rtz.units.Coordinates;

public class WaypointMapper {

    public static WaypointDto waypointToDto(Waypoint waypoint) {
        return WaypointDto.create(waypoint.getId().getId(), new Coordinates(waypoint.getLat(), waypoint.getLon()));
    }
}
