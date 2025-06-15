package ru.rtz.route.route.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rtz.service.LegService;
import ru.rtz.route.route.model.Route;
import ru.rtz.route.route.model.RouteDto;
import ru.rtz.service.RouteService;
import ru.rtz.service.WaypointService;

import java.util.List;

@RestController
@RequestMapping("/route")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;
    private final LegService legService;
    private final WaypointService waypointService;

    @GetMapping("/get")
    public RouteDto get() {
        return routeService.getRoute();
    }

    @PostMapping("/save")
    public RouteDto save() {
        Route route = new Route();
        route.setName("111");
        return routeService.addRoute(route);
    }

    @GetMapping("/get/all")
    public List<RouteDto> getALl() {
        return routeService.getALl();
    }
}
