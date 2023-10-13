package ru.rtz.route.route.service;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rtz.route.route.RouteMapper;
import ru.rtz.route.route.model.Route;
import ru.rtz.route.route.model.RouteDto;
import ru.rtz.route.route.repository.RouteRepository;
import ru.rtz.route.waypoint.WaypointMapper;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class RouteService implements IRouteService {

    RouteRepository routeRepository;

    @Autowired
    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @Override
    public RouteDto addRoute(Route route) {
        Route savedRoute = routeRepository.save(route);
        return RouteMapper.routeToDto(savedRoute);
    }

    @Override
    public RouteDto getRoute() {
        Route route = routeRepository.findById(1L).get();
        RouteDto routeDto = RouteMapper.routeToDto(route);
        routeDto.setWaypoints(route.getWaypoints().stream().map(WaypointMapper::waypointToDto).collect(Collectors.toList()));
        return routeDto;
    }

    @Override
    public List<RouteDto> getALl() {
        return routeRepository.findAll().stream().map(RouteMapper::routeToDto).collect(Collectors.toList());
    }
    /*    private LinkedList<Leg> calculateLegs(LinkedList<Waypoint> waypointTreeMap) {
        LinkedList<Leg> legList = new LinkedList<>();
        for (int i = 1; i <= (waypointTreeMap.size() - 1); i++) {
            legList.add(legService.create(waypointTreeMap.get(i), waypointTreeMap.get(i + 1), i));
        }
        return legList;
    }*/

}
