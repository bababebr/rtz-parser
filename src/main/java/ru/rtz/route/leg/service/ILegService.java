package ru.rtz.route.leg.service;

import ru.rtz.route.leg.model.Leg;
import ru.rtz.route.waypoint.model.Waypoint;

public interface ILegService {

    Leg create(Waypoint wp1, Waypoint wp2, int id);

    String show(Leg leg);
}
