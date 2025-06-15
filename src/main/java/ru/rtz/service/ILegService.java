package ru.rtz.service;

import ru.rtz.model.Leg;
import ru.rtz.model.Waypoint;

public interface ILegService {

    Leg create(Waypoint wp1, Waypoint wp2, int id);

    String show(Leg leg);
}
