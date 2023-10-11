package ru.rtz.route.route;

import ru.rtz.route.route.model.Route;
import ru.rtz.route.route.model.RouteDto;

public class RouteMapper {

    public static RouteDto routeToDto(Route route) {
        return RouteDto.create(route.getId(), route.getName(), route.getWaypoints(), null);
    }

}
