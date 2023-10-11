package ru.rtz.route.route.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rtz.route.route.model.RouteDto;
import ru.rtz.route.waypoint.model.Waypoint;
import ru.rtz.route.leg.service.LegService;
import ru.rtz.route.route.service.RouteService;
import ru.rtz.route.waypoint.service.WaypointService;

@RestController("/route")
public class RouteController {

    private final RouteService routeService;
    private final LegService legService;
    private final WaypointService waypointService;

    @Autowired
    public RouteController(RouteService routeService, LegService legService, WaypointService waypointService) {
        this.routeService = routeService;
        this.legService = legService;
        this.waypointService = waypointService;
    }

    @GetMapping("/get")
    public RouteDto get() {
        return routeService.getRoute();
    }
}
