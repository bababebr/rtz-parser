package Main.SperryMarine.Route;

import Main.Enums.CATZOC;
import Main.Enums.LegTypes;
import Main.Route.Leg;
import Main.Route.Waypoint;
import Main.Tools.Coordinates;

public class CustomLeg extends Leg {
    double depth;
    CATZOC e;
    LegTypes l;
    double localReq;
    double waterDensity;
    Coordinates minDepthPos;
    /**
     * Custom leg created for the usage personal usage on board.
     *
     * I creates the routes using the NavStation, for each leg i indicate in WP notes next information:
     *  Depth, UKC, LegType, Local UKC Requirements, Water Density, Position of minimum depth;
     *  For example: For the leg between WP1 & WP 2, WP1 notes will looks like:
     *              "Depth 15; UKC B; LegType R, WD 1.025; 25-10.0N 130-10.0E"
     *
     *  For UKC please see CATZOC Enum;
     *  For LegType see LegTypes Enum;
     *
     *
     *
     * Calculates Rhumbline and Great Circle line between two Waypoints.
     * <p>
     * Parameters listed below are custom parameters!
     * and they are should be indicated in the Note section of the RTZ file or Navstation soft for each leg
     * or default values will be used.
     * <p>
     * Depth - minimum depth on the Leg
     * Catzoc - Survey data accuracy (See CATZOC enum for the detailed info)
     * localReq - Local UKC Requirements
     * legType - Type of leg, such as: Resticted waters - R, Open Waters - O, Coastal waters - C. See LegType Enum.
     * waterDensity - Density of the water for the current leg
     *
     * @param wp1 Start Leg
     * @param wp2 End Leg
     * @param id  Leg id
     */
    public CustomLeg(Waypoint wp1, Waypoint wp2, int id) {
        super(wp1, wp2, id);
        noteParser(wp1.getLegNote());
    }

    private void noteParser(String legNote){
        try {
            int pos = 0;

            if(legNote.contains("Depth")){
                pos = legNote.indexOf("Depth")+6;
                depth = Double.parseDouble(legNote.substring(pos,legNote.indexOf(";",pos)));
            }
            if(legNote.contains("UKC")){
                pos = legNote.indexOf("UKC")+4;
                e = CATZOC.valueOf(legNote.substring(pos,legNote.indexOf(";",pos)));
            }
            if(legNote.contains("LocalDepth")){
                pos = legNote.indexOf("LocalDepth")+11;
                localReq = Double.parseDouble((legNote.substring(pos,legNote.indexOf(";",pos))));
            }
            if(legNote.contains("WD")){
                pos = legNote.indexOf("WD")+3;
                waterDensity = Double.parseDouble((legNote.substring(pos,legNote.indexOf(";",pos))));
            }
            if(legNote.contains("MinDepthPos")){
                pos = legNote.indexOf("MinDepthPos")+12;
                minDepthPos = new Coordinates(legNote.substring(pos,legNote.indexOf(";",pos)));
            }
            if(legNote.contains("LegType")){
                pos = legNote.indexOf("LegType")+8;
                l = LegTypes.valueOf(legNote.substring(pos,legNote.indexOf(";",pos)));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }


    public double getWaterDensity() {
        return waterDensity;
    }

    public void setWaterDensity(double waterDensity) {
        this.waterDensity = waterDensity;
    }

    public Coordinates getMinDepthPos() {
        return minDepthPos;
    }

    public void setMinDepthPos(Coordinates minDepthPos) {
        this.minDepthPos = minDepthPos;
    }

    public CATZOC getE() {
        return e;
    }

    public void setE(CATZOC e) {
        this.e = e;
    }

    public LegTypes getL() {
        return l;
    }

    public void setL(LegTypes l) {
        this.l = l;
    }
}
