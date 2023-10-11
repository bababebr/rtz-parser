package ru.rtz.route.route.service;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rtz.route.leg.service.LegService;
import ru.rtz.route.route.RouteMapper;
import ru.rtz.route.route.model.Route;
import ru.rtz.route.route.model.RouteDto;
import ru.rtz.route.route.repository.RouteRepository;
import ru.rtz.route.waypoint.model.Waypoint;

import java.util.ArrayList;
import java.util.LinkedList;

@Getter
@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class RouteService implements IRouteService {

    RouteRepository routeRepository;

    @Override
    public Route addRoute(Route route) {
        return routeRepository.save(route);
    }

    @Override
    public RouteDto getRoute() {
        RouteDto routeDto = RouteMapper.routeToDto(routeRepository.findById(1L).get());
        return routeDto;
    }

    @Override
    public Route create(String name, ArrayList<Waypoint> waypoints) {
        Route route = new Route();
        route.setName(name);
        route.setWaypoints(waypoints);
        return route;
    }


/*    private LinkedList<Leg> calculateLegs(LinkedList<Waypoint> waypointTreeMap) {
        LinkedList<Leg> legList = new LinkedList<>();
        for (int i = 1; i <= (waypointTreeMap.size() - 1); i++) {
            legList.add(legService.create(waypointTreeMap.get(i), waypointTreeMap.get(i + 1), i));
        }
        return legList;
    }*/

}
