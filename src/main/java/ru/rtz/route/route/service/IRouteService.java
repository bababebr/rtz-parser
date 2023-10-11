package ru.rtz.route.route.service;

import ru.rtz.route.route.model.Route;
import ru.rtz.route.route.model.RouteDto;
import ru.rtz.route.waypoint.model.Waypoint;

import java.util.ArrayList;

public interface IRouteService {

    Route create(String name, ArrayList<Waypoint> waypoints);

    RouteDto getRoute();

    Route addRoute(Route route);
}
