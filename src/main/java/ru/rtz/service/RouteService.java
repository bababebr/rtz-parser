package ru.rtz.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rtz.dto.RouteDto;
import ru.rtz.mapper.RouteMapper;
import ru.rtz.mapper.WaypointMapper;
import ru.rtz.model.Route;
import ru.rtz.repository.RouteRepository;

@Service
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
