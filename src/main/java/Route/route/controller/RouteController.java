package route.route.controller;

import org.springframework.web.bind.annotation.RestController;
import route.waypoint.model.Waypoint;
import route.leg.service.LegService;
import route.route.service.RouteService;

@RestController("/route")
public class RouteController {

    private final RouteService routeService;
    private final LegService legService;
    private final Waypoint waypoint;

    public RouteController(RouteService routeService, LegService legService, Waypoint waypoint) {
        this.routeService = routeService;
        this.legService = legService;
        this.waypoint = waypoint;
    }
}
