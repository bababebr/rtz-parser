package ru.rtz.service;

import ru.rtz.dto.RouteDto;
import ru.rtz.model.Route;

import java.util.List;

public interface IRouteService {

    RouteDto getRoute();

    RouteDto addRoute(Route route);

    List<RouteDto> getALl();
}
