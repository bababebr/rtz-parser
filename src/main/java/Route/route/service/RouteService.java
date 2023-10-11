package route.route.service;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import route.leg.repository.model.Leg;
import route.route.model.Route;
import route.route.repository.RouteRepository;
import route.leg.service.LegService;
import route.waypoint.model.Waypoint;

import java.util.LinkedList;

@Getter
@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RouteService implements IRouteService {

    LegService legService;
    RouteRepository routeRepository;

    @Autowired
    public RouteService(LegService legService, RouteRepository routeRepository) {
        this.legService = legService;
        this.routeRepository = routeRepository;
    }

    public Route addRoute(Route route) {
        return routeRepository.save(route);
    }


    @Override
    public Route create(String name, LinkedList<Waypoint> waypoints) {
        Route route = Route.create();
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
