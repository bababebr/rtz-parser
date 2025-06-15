package ru.rtz.service;

import ru.rtz.route.route.model.Route;
import ru.rtz.route.route.model.RouteDto;

import java.util.List;

public interface IRouteService {

    RouteDto getRoute();

    RouteDto addRoute(Route route);

    List<RouteDto> getALl();
}
