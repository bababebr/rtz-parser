package Route;

import Enums.CATZOC;
import Enums.LegTypes;
import Tools.NauticalMath;

import java.io.Serializable;

public class Leg implements Serializable {
    private static final long serialVersionUID = -4228471279843632173L;
    private int id;
    private String name;
    private int wpIdBegin;
    private int wpIdEnd;
    private double GCcoure;
    private double GCDist;
    private double RLCoure;
    private double RLDist;
    private String legNote;
    private Waypoint wpStart;
    private Waypoint wpEnd;
    private CATZOC catzoc;
    private LegTypes legType;
    /**
     * Calculates Rhumbline and Great Circle line between two Waypoints.
     *
     * Parameters listed below are custom parameters!
     * and they are should be indicated in the Note section of the RTZ file or Navstation soft for each leg
     * or default values will be used.
     *
     * Depth - minimum depth on the Leg
     * Catzoc - Survey data accuracy (See CATZOC enum for the detailed info)
     * localReq - Local UKC Requirements
     * legType - Type of leg, such as: Resticted waters - R, Open Waters - O, Coastal waters - C. See LegType Enum.
     * waterDensity - Density of the water for the current leg
     * @param wp1 Start Leg
     * @param wp2 End Leg
     * @param id Leg id
     */


    public Leg(Waypoint wp1, Waypoint wp2, int id){
        wpStart = wp1;
        wpEnd = wp2;
        NauticalMath n = new NauticalMath();
        this.id = id;
        name = String.format("%s - %s",wpStart.getName(), wpEnd.getName());
        wpIdBegin = wpStart.getId();
        wpIdEnd = wpEnd.getId();
        GCcoure = n.GC_Course(wpStart.getPosition(),wpEnd.getPosition());
        GCDist = n.GC_Dist(wpStart.getPosition(),wpEnd.getPosition());
        RLCoure = n.RL_Course(wpStart.getPosition(),wpEnd.getPosition());
        RLDist = n.RL_Dist(wpStart.getPosition(), wpEnd.getPosition());
        legNote = wpStart.getLegNote();
    }

    public void recalculateRl_Dist(){
        NauticalMath n = new NauticalMath();
        RLDist = n.RL_Dist(wpStart.getPosition(), wpEnd.getPosition());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWpIdBegin() {
        return wpIdBegin;
    }

    public void setWpIdBegin(int wpIdBegin) {
        this.wpIdBegin = wpIdBegin;
    }

    public int getWpIdEnd() {
        return wpIdEnd;
    }

    public void setWpIdEnd(int wpIdEnd) {
        this.wpIdEnd = wpIdEnd;
    }

    public double getGCcoure() {
        return GCcoure;
    }

    public void setGCcoure(double GCcoure) {
        this.GCcoure = GCcoure;
    }

    public double getGCDist() {
        return GCDist;
    }

    public void setGCDist(double GCDist) {
        this.GCDist = GCDist;
    }

    public double getRLCoure() {
        return RLCoure;
    }

    public void setRLCoure(double RLCoure) {
        this.RLCoure = RLCoure;
    }

    public double getRLDist() {
        return RLDist;
    }

    public void setRLDist(double RLDist) {
        this.RLDist = RLDist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLegNote() {
        return legNote;
    }

    public void setLegNote(String legNote) {
        this.legNote = legNote;
    }

    public Waypoint getWpStart() {
        return wpStart;
    }

    public void setWpStart(Waypoint wpStart) {
        this.wpStart = wpStart;
    }

    public Waypoint getWpEnd() {
        return wpEnd;
    }

    public void setWpEnd(Waypoint wpEnd) {
        this.wpEnd = wpEnd;
    }

    public CATZOC getCatzoc() {
        return catzoc;
    }

    public void setCatzoc(CATZOC catzoc) {
        this.catzoc = catzoc;
    }

    public LegTypes getLegType() {
        return legType;
    }

    public void setLegType(LegTypes legType) {
        this.legType = legType;
    }

    @Override
    public String toString() {
        return "Leg{" +
                "id=" + id +
                ", wpIdBegin=" + wpIdBegin +
                ", wpIdEnd=" + wpIdEnd +
                ", GCcoure=" + GCcoure +
                ", GCDist=" + GCDist +
                ", RLCoure=" + RLCoure +
                ", RLDist=" + RLDist +
                ", legNote=" + legNote +
                '}';
    }
    public String show(){
        return String.format("%d. %s - Dist: %f | Course %f%n",id,name, GCDist, GCcoure);
    }
}
