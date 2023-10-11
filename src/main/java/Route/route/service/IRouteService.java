package route.route.service;

import route.route.model.Route;
import route.waypoint.model.Waypoint;

import java.util.LinkedList;

public interface IRouteService {

    Route create(String name, LinkedList<Waypoint> waypoints);

}
