package ru.rtz.controller;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rtz.dto.RouteDto;
import ru.rtz.service.LegService;
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

    @GetMapping(value = "/get", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public RouteDto get(@RequestBody RouteDto routeDto) {
        System.out.println(routeDto);
        return routeDto;
    }

}
