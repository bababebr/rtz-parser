package ru.rtz.service;

import ru.rtz.route.waypoint.model.Waypoint;

public interface IWaypointService {

    Waypoint parse(String wp);

}
