package route.waypoint.service;

import enums.LEG_TYPE;
import exception.WaypointException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import route.waypoint.model.Waypoint;
import route.waypoint.repository.WaypointId;
import route.waypoint.repository.WaypointRepository;
import units.Coordinates;

@Service
public class WaypointService implements IWaypointService {

    private final WaypointRepository waypointRepository;

    @Autowired
    public WaypointService(WaypointRepository waypointRepository) {
        this.waypointRepository = waypointRepository;
    }

    @Override
    public Waypoint parse(String wp) {
        Waypoint waypoint = new Waypoint();
        if (!wp.trim().startsWith("<waypoint")) {
            throw new WaypointException("Wrong waypoint object.");
        }
        try {
            int pos;
            pos = wp.indexOf("id=") + 4;
            waypoint.setId(WaypointId.create(0, Integer.parseInt(wp.substring(pos, wp.indexOf("\"", pos)))));
            pos = wp.indexOf("name=") + 6;
            waypoint.setName(wp.substring(pos, wp.indexOf("\"", pos)));
            pos = wp.indexOf("radius=") + 8;
            waypoint.setRadius(Double.parseDouble(wp.substring(pos, wp.indexOf("\"", pos))));
            pos = wp.indexOf("lat=") + 5;
            waypoint.setLat(Double.parseDouble(wp.substring(pos, wp.indexOf("\"", pos))));
            pos = wp.indexOf("lon=") + 5;
            waypoint.setLon(Double.parseDouble(wp.substring(pos, wp.indexOf("\"", pos))));
            pos = wp.indexOf("starboardXTD=") + 14;
            waypoint.setStbdXTD(Double.parseDouble(wp.substring(pos, wp.indexOf("\"", pos))));
            pos = wp.indexOf("portsideXTD=") + 13;
            waypoint.setPortXTD(Double.parseDouble(wp.substring(pos, wp.indexOf("\"", pos))));
            pos = wp.indexOf("geometryType=") + 14;
            waypoint.setLegType(LEG_TYPE.valueOf(wp.substring(pos, wp.indexOf("\"", pos))));
            pos = wp.indexOf("speedMin=") + 10;
            waypoint.setSpeedMin(Double.parseDouble(wp.substring(pos, wp.indexOf("\"", pos))));
            pos = wp.indexOf("speedMax=") + 10;
            waypoint.setSpeedMax(Double.parseDouble(wp.substring(pos, wp.indexOf("\"", pos))));
            waypoint.setPosition(new Coordinates(waypoint.getLat(), waypoint.getLon()));
            pos = wp.indexOf("legNote2=") + 10;
            waypoint.setLegNote(wp.substring(pos, wp.indexOf("\"", pos)));
        } catch (Exception e) {
            throw new WaypointException("Cannot parse Waypoint");
        }
        return waypoint;
    }
}
