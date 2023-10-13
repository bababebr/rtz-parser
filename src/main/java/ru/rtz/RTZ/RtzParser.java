package ru.rtz.RTZ;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import ru.rtz.enums.LegType;
import ru.rtz.exception.RtzException;
import ru.rtz.route.route.model.Route;
import ru.rtz.route.waypoint.model.Waypoint;
import ru.rtz.route.leg.service.LegService;
import ru.rtz.route.route.service.RouteService;
import ru.rtz.route.waypoint.service.WaypointService;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RtzParser implements Serializable {

    //TODO Implement StringTokenizer
    LegService legService;
    WaypointService waypointService;
    RouteService routeService;

    @Autowired
    public RtzParser(LegService legService, WaypointService waypointService, RouteService routeService) {
        this.legService = legService;
        this.waypointService = waypointService;
        this.routeService = routeService;
    }

    public Route parseRtz(File rtzRoute) {
        ArrayList<Waypoint> waypoints = new ArrayList<>();
        String line;
        int wpId = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(rtzRoute))) {
            String header = parseHeaderTag(reader.readLine());
            String rtzVersion = parserRtzVersionTag(reader.readLine());
            String routeName = parseRouteInfoTag(reader.readLine());
            line = reader.readLine();
            while (!line.contains("</waypoints>")) {
                line = reader.readLine();
                if (line.contains("<waypoint ")) {
                    Waypoint waypoint = new Waypoint();
                    //waypoint.setId(++wpId);
                    parseWaypointTag(line, waypoint);
                    while (!line.contains("</waypoint>")) {
                        line = reader.readLine();
                        if (line.contains("<position")) {
                            parsePositionTag(line, waypoint);
                        }
                        if (line.contains("<leg")) {
                            parseLegTag(line, waypoint);
                        }
                        waypoints.add(waypoint);
                    }
                }
            }
            //TODO Route.Create
            return null;
        } catch (IOException e) {
            throw new RtzException("Cannot parse .rtz ru.rtz.route file.");
        }
    }

    private static String parseHeaderTag(String line) {
        if (line.startsWith("<?xml")) return line;
        throw new RtzException("File header not found.");
    }

    private static String parserRtzVersionTag(String line) {
        if (line.startsWith("<ru.rtz.route")) return line;
        throw new RtzException("Route tag not found.");
    }

    private static String parseRouteInfoTag(String line) {
        return line.substring(line.indexOf("routeName=\"") + 11, line.lastIndexOf("\""));
    }

    private static void parseWaypointTag(String line, Waypoint waypoint) {
        int firstIdx = line.indexOf("name=\"") + 6;
        int lastIdx = line.indexOf("\"", firstIdx);
        waypoint.setName(line.substring(firstIdx, lastIdx));
        firstIdx = line.indexOf("radius=\"") + 8;
        lastIdx = line.indexOf("\"", firstIdx);
        waypoint.setRadius(Double.parseDouble(line.substring(firstIdx, lastIdx)));
    }

    private static void parsePositionTag(String line, Waypoint waypoint) {
        int firstIdx = line.indexOf("lat=\"") + 5;
        int lastIdx = line.indexOf("\"", firstIdx);
        waypoint.setLat(Double.parseDouble(line.substring(firstIdx, lastIdx)));
        firstIdx = line.indexOf("lon=\"") + 5;
        lastIdx = line.indexOf("\"", firstIdx);
        waypoint.setLon(Double.parseDouble(line.substring(firstIdx, lastIdx)));
    }

    private static void parseLegTag(String line, Waypoint waypoint) {
        int firstIdx = line.indexOf("starboardXTD=\"") + 14;
        int lastIdx = line.indexOf("\"", firstIdx);
        waypoint.setStbdXTD(Double.parseDouble(line.substring(firstIdx, lastIdx)));
        firstIdx = line.indexOf("portsideXTD=\"") + 13;
        lastIdx = line.indexOf("\"", firstIdx);
        waypoint.setPortXTD(Double.parseDouble(line.substring(firstIdx, lastIdx)));
        firstIdx = line.indexOf("geometryType=\"") + 14;
        lastIdx = line.indexOf("\"", firstIdx);
        //TODO fix enum
        waypoint.setLegType(LegType.valueOf(line.substring(firstIdx, lastIdx)));
        firstIdx = line.indexOf("speedMax=\"") + 10;
        lastIdx = line.indexOf("\"", firstIdx);
        waypoint.setSpeedMax(Double.parseDouble(line.substring(firstIdx, lastIdx)));
        firstIdx = line.indexOf("speedMin=\"") + 10;
        lastIdx = line.indexOf("\"", firstIdx);
        waypoint.setSpeedMin(Double.parseDouble(line.substring(firstIdx, lastIdx)));
        firstIdx = line.indexOf("legNote2=\"") + 10;
        lastIdx = line.indexOf("\"", firstIdx);
        waypoint.setLegNote(line.substring(firstIdx, lastIdx));
    }

    //TODO parse default waypoint
    private void parseDefaultWaypoint(String line) {

    }
}
