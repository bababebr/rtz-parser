package ru.rtz.route.leg.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import ru.rtz.route.leg.model.Leg;
import ru.rtz.route.waypoint.model.Waypoint;
import ru.rtz.utils.NauticalMath;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
public class LegService implements ILegService {
    NauticalMath n = new NauticalMath();

    @Override
    public Leg create(Waypoint wp1, Waypoint wp2, int id) {
        Leg leg = new Leg();
        leg.setWpStart(wp1);
        leg.setWpEnd(wp2);
        leg.setId(id);
        leg.setName(String.format("%s - %s", wp1.getName(), wp2.getName()));
        leg.setWpIdBegin(wp1.getId().getId());
        leg.setWpIdEnd(wp2.getId().getId());
        leg.setGcCourse(n.GC_Course(wp1.getPosition(), wp2.getPosition()));
        leg.setGcDist(n.GC_Dist(wp1.getPosition(), wp1.getPosition()));
        leg.setRlCourse(n.RL_Course(wp1.getPosition(), wp2.getPosition()));
        leg.setRlDist(n.RL_Dist(wp1.getPosition(), wp2.getPosition()));
        leg.setLegNote(wp1.getLegNote());
        return leg;
    }

    @Override
    public String show(Leg leg) {
        return String.format("%d. %s - Dist: %f | Course %f%n", leg.getId(), leg.getName(), leg.getGcDist(), leg.getGcCourse());
    }
}
