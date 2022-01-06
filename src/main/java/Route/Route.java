package Route;

import Tools.ETA_Calculator;
import com.sun.istack.NotNull;

import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.TreeMap;

public class Route implements Serializable {
    private static final long serialVersionUID = -3982444376385756354L;
    private int id;
    private String routeName;
    private TreeMap<Integer, Waypoint> waypointsTreeMap = new TreeMap<>();
    private ArrayList<Leg> legList;
    private byte[] sourceRTZ;
    private double[] distanceSailed_GC;
    private double[] distanceSailed_RL;

    private double GC_totalDist;
    private double RL_totalDist;
    private ETA_Calculator calculator = new ETA_Calculator();

    private ZonedDateTime departureTime;

    public Route(String routeName, TreeMap<Integer, Waypoint> waypointsTreeMap, ArrayList<Leg> legList) {
        this.routeName = routeName;
        this.waypointsTreeMap = waypointsTreeMap;
        this.legList = legList;
        calcDist();
    }

    public void recalculateRLDist(){
        for(Leg l : legList){
            l.recalculateRl_Dist();
        }
    }
    private void calcDist(){
        int legID = 0;
        distanceSailed_GC = new double[legList.size()];
        distanceSailed_RL = new double[legList.size()];

        for(Leg l : legList){
            GC_totalDist += l.getGCDist();
            RL_totalDist += l.getRLDist();
            distanceSailed_RL[legID] = RL_totalDist;
            distanceSailed_GC[legID] = GC_totalDist;
            legID++;
        }
    }

    public String generateRTZ(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("<?xml version=\"1.0\" encoding=\"UTF-8\"?>%n"));
        sb.append(String.format("<route xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.cirm.org/RTZ/1/0\" xsi:schemaLocation=\"http://www.cirm.org/rtz/RTZ Schema version 1_0.xsd\" version=\"1.0\">%n"));
        sb.append(String.format("<routeInfo routeName=\"%s\" />%n",routeName));
        sb.append(String.format("<waypoints>%n"));
        for(Waypoint wp : waypointsTreeMap.values()){
            sb.append(wp.generateWaypointTag());
        }
        sb.append(String.format("</waypoints>%n"));
        sb.append("</route>");
        return sb.toString();
    }
    public String print_Legs(boolean printOnly){
        StringBuilder sb = new StringBuilder();

        for (Leg l : legList){
            sb.append(String.format("%d.%s - GR :: Dist: %f  Course %f  " +
                    "RL :: Dist: %f Course %f" +
                    " | LegNote: %s%n ",l.getId(),l.getName(),l.getGCDist(), l.getGCcoure(), l.getRLDist(), l.getRLCoure(),
                    l.getLegNote()));
        }
        if (printOnly){
            System.out.println(getRouteName() + "; Total Dist: " + getGC_totalDist() + System.lineSeparator());
            System.out.println(sb.toString());
            return null;
        }
        return sb.toString();
    }


    public String info(){
        String info = String.format("Route name: %s; Total Dist: %f; Legs Count: %d", routeName, GC_totalDist, legList.size());
        return info;
    }
    public String print_Waypoints(boolean printOnly){
        StringBuilder sb = new StringBuilder();
        for (Waypoint w : waypointsTreeMap.values()){
            sb.append(w.toString()+System.lineSeparator());
        }
        if (printOnly){
            System.out.println(sb.toString());
            return null;
        }
        return sb.toString();
    }
    public String print_Leg(int legId, boolean printOnly){
        if (printOnly){
            System.out.println(legList.get(legId-1).show());
            return null;
        }
        return legList.get(legId-1).show();
    }
    public String print_Wp(int wpId, boolean printOnly){
        if (printOnly){
            System.out.println(waypointsTreeMap.get(wpId).toString());
            return null;
        }
        return waypointsTreeMap.get(wpId).toString();
    }
    public String print_wpIndex(boolean printOnly){
        StringBuilder sb = new StringBuilder();
        for(Waypoint wp : waypointsTreeMap.values()){
            sb.append(wp.getId()).append(" ").append(wp.getName()).append(System.lineSeparator());
        }
        if (printOnly){
            System.out.println(sb.toString());
            return null;
        }
        return sb.toString();
    }

    public double distBetweenWaypoints(@NotNull int idStart, @NotNull int idEnd){
        if(idStart > idEnd){
            System.err.println("Wrong Wps order");
            return -1d;
        }
        if(idStart == idEnd){
            return 0d;
        }
        try {
            return distanceSailed_GC[idEnd-1] - distanceSailed_GC[idStart-1];
        }
        catch (NullPointerException e){
            System.err.println("Waypoint with ID " + idEnd + " isn't exist");
        }
        return 1d;
    }

    public ZonedDateTime arrivalDate(ZoneId arrivalTimeZone,
                                     int departureWP, int arrivalWP, double speed){
        System.out.println(distBetweenWaypoints(departureWP, arrivalWP));
        return calculator.calc(departureTime, arrivalTimeZone, speed, distBetweenWaypoints(departureWP, arrivalWP));
    }
    public ZonedDateTime arrivalDate(double speed, ZoneId arrivalTimeZone){
        return calculator.calc(departureTime, arrivalTimeZone, speed, GC_totalDist);
    }


    /**
     * Default Getters and Setters
     * @return
     */

    public ZonedDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(ZonedDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = ZonedDateTime.parse(departureTime, DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public TreeMap<Integer, Waypoint> getWaypointsTreeMap() {
        return waypointsTreeMap;
    }

    public void setWaypointsTreeMap(TreeMap<Integer, Waypoint> waypointsTreeMap) {
        this.waypointsTreeMap = waypointsTreeMap;
    }

    public ArrayList<Leg> getLegList() {
        return legList;
    }

    public void setLegList(ArrayList<Leg> legList) {
        this.legList = legList;
    }

    public double getGC_totalDist() {
        return GC_totalDist;
    }

    private void setGC_totalDist(double GC_totalDist) {
        this.GC_totalDist = GC_totalDist;
    }

    public double getRL_totalDist() {
        return RL_totalDist;
    }

    private void setRL_totalDist(double RL_totalDist) {
        this.RL_totalDist = RL_totalDist;
    }
}
