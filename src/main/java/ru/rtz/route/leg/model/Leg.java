package ru.rtz.route.leg.model;

import lombok.RequiredArgsConstructor;
import ru.rtz.enums.CATZOC;
import ru.rtz.enums.PassageType;
import ru.rtz.route.waypoint.model.Waypoint;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class Leg implements Serializable {
    static final long serialVersionUID = -4228471279843632173L;
    int id;
    String name;
    long wpIdBegin;
    long wpIdEnd;
    double gcCourse;
    double gcDist;
    double rlCourse;
    double rlDist;
    String legNote;
    Waypoint wpStart;
    Waypoint wpEnd;
    CATZOC catzoc;
    PassageType legType;
    /**
     * Calculates Rhumbline and Great Circle line between two Waypoints.
     *
     * Parameters listed below are custom parameters!
     * and they are should be indicated in the Note section of the ru.rtz.RTZ file or Navstation soft for each leg
     * or default values will be used.
     *
     * Depth - minimum depth on the Leg
     * Catzoc - Survey data accuracy (See CATZOC enum for the detailed info)
     * localReq - Local UKC Requirements
     * legType - Type of leg, such as: Resticted waters - R, Open Waters - O, Coastal waters - C. See LegType Enum.
     * waterDensity - Density of the water for the current leg

     */

    @Override
    public String toString() {
        return "Leg{" +
                "id=" + id +
                ", wpIdBegin=" + wpIdBegin +
                ", wpIdEnd=" + wpIdEnd +
                ", GCcoure=" + gcCourse +
                ", GCDist=" + gcDist +
                ", RLCoure=" + rlCourse +
                ", RLDist=" + rlDist +
                ", legNote=" + legNote +
                '}';
    }
}
