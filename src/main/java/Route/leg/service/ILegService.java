package route.leg.service;

import route.leg.model.Leg;
import route.waypoint.model.Waypoint;

public interface ILegService {

    Leg create(Waypoint wp1, Waypoint wp2, int id);

    String show(Leg leg);
}
