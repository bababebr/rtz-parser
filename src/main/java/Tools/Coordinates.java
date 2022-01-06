package Tools;

import java.io.Serializable;

public class Coordinates implements Serializable {
    static final long serialVersionUID = -5856838176003846381l;
    private double x;
    private double y;
    private String lat;
    private String lon;

    public Coordinates() {

    }

    public Coordinates(double x, double y) {
        this.x = x;
        this.y = y;
        doubleToStringFormat();
    }

    public Coordinates(String lat, String lon) {
        String latPattern = "[0-9]{1,2}[-][0-9]{2}[.][0-9]*[N|S]";
        String logPattern = "[0-9]{1,3}[-][0-9]{2}[.][0-9]*[E|S]";
        this.lat = lat.matches(latPattern) == true ? lat : "";
        this.lon = lon.matches(logPattern) == true ? lon : "";

        if(lat.isBlank() || lon.isBlank()){
            System.err.println("Wrong coordinates format");
            return;
        }

        if(lon.startsWith("0")){
            lon = lon.substring(1);
        }
        int latDeg = Integer.parseInt(lat.substring(0,lat.indexOf("-")));
        double latMin = Double.parseDouble(lat.substring(lat.indexOf("-")+1,lat.length()-1));
        int lonDeg = Integer.parseInt(lon.substring(0,lon.indexOf("-")));
        double lonMin = Double.parseDouble(lon.substring(lon.indexOf("-")+1,lon.length()-1));
        if(lat.endsWith("N")){
            this.x = latDeg + (latMin / 60);
        }
        else if(lat.endsWith("S")) {
            this.x = -(latDeg + (latMin / 60));
        }

        if(lon.endsWith("E")){
            this.y = lonDeg + (lonMin / 60);
        }
        else {
            this.y = -(lonDeg + (lonMin / 60));
        }
    }
    public Coordinates(String latLong){
        String pattern = "[0-9]{1,2}[-][0-9]{2}[.][0-9]*[N|S] [0-9]{1,3}[-][0-9]{2}[.][0-9]*[E|S]";
        if(latLong.matches(pattern)){
            this.lat = latLong.split("[ ]")[0];
            this.lon = latLong.split("[ ]")[1];
            int latDeg = Integer.parseInt(lat.substring(0,lat.indexOf("-")));
            double latMin = Double.parseDouble(lat.substring(lat.indexOf("-")+1,lat.length()-1));
            int lonDeg = Integer.parseInt(lon.substring(0,lon.indexOf("-")));
            double lonMin = Double.parseDouble(lon.substring(lon.indexOf("-")+1,lon.length()-1));
            if(lat.endsWith("N")){
                this.x = latDeg + (latMin / 60);
            }
            else if(lat.endsWith("")) {
                this.x = -(latDeg + (latMin / 60));
            }

            if(lon.endsWith("E")){
                this.y = lonDeg + (lonMin / 60);
            }
            else {
                this.y = -(lonDeg + (lonMin / 60));
            }
            this.x = latDeg + (latMin / 60);
            this.y = lonDeg + (lonMin / 60);

            if(lat.endsWith("N")){
                this.x = latDeg + (latMin / 60);
            }
            else {
                this.x = -(latDeg + (latMin / 60));
            }

            if(lon.endsWith("E")){
                this.y = lonDeg + (lonMin / 60);
            }
            else {
                this.y = -(lonDeg + (lonMin / 60));
            }
        }
        else {
            System.out.println("Coordinates format is incorrect");
            return;
        }
    }

    private void doubleToStringFormat() {
        int latDeg = (int) x;
        double latMin = (x - latDeg) * 60;
        int lonDeg = (int) y;
        double lonMin = (y - lonDeg) * 60;
        if(x < 0d) {
            lat = String.format("%d-%.2f",latDeg,latMin).concat("S");
        }
        else {
            lat = String.format("%d-%.2f",latDeg,latMin).concat("N");
        }
        if(y < 0d){
            lon = String.format("%d-%.2f",lonDeg,lonMin).concat("W");
        }
        else {
            lon = String.format("%d-%.2f",lonDeg, lonMin).concat("E");
        }
    }

    /**
     * Calculation of Direction vector
     * @param c1 - Vector Start
     * @param c2 - Vector end
     * @return Direction vectror
     */
    public Coordinates dirVec2D(Coordinates c1, Coordinates c2){
        double length = vec2DLength(c1,c2);
        return new Coordinates((c2.x-c1.x) / length, (c2.y-c1.y)/length);
    }

    /**
     * Calculation of Direction vector from current Coordinates to given position
     * @param nextPos
     * @return Direction vectror
     */
    public Coordinates dirVec2D(Coordinates nextPos){
        double length = vec2DLength(nextPos);
        return new Coordinates((nextPos.x-x) / length, (nextPos.y-y)/length);
    }


    /**
     * Calculation of Vector Length
     * @param c1 - Vector Start
     * @param c2 - Vector end
     * @return - Vector Length
     */
    private double vec2DLength(Coordinates c1, Coordinates c2){
        //NauticalMath n = new NauticalMath();
        //return n.GC_Dist(c1,c2)/60d;
        return Math.sqrt(Math.pow((c2.x-c1.x),2) + Math.pow((c2.y-c1.y),2));
    }
    /**
     * Calculation of Vector Length
     * @param nextPos - Next Position
     * @return - Vector Length
     */
    public double vec2DLength(Coordinates nextPos){
        //NauticalMath n = new NauticalMath();
        //return n.GC_Dist(this, nextPos)/60d;
        return Math.sqrt(Math.pow((nextPos.x-x),2) + Math.pow((nextPos.y-y),2));
    }

    /**
     * Calculation of Direction of the perpendicular vector
     * starting from c1
     * @param c1 - Vector Start
     * @param c2 - Vector end
     * @return Direction of perpendicular vector
     */
    public Coordinates perpendicularDirVec2D(Coordinates c1, Coordinates c2){
        double scalar;
        double length;
        double yP = 1d;
        double xP;
        Coordinates dirVec = dirVec2D(c1,c2);
        yP = dirVec.y >=0 ? -dirVec.x : dirVec.x;
        if((c2.x-c1.x <= 0.0001d)){
            xP = 1d;
        }
        else {
            xP = -(dirVec.y * yP) / dirVec.x;
        }
        return new Coordinates(xP , yP);
    }

    /**
     * Calculation of Direction of the perpendicular vector to the current vector
     * starting from c1
     * @param nextPos - End Position
     * @return Direction of perpendicular vector
     */
    public Coordinates perpendicularDirVec2D(Coordinates nextPos){
        double length;
        double yP;
        double xP;
        Coordinates dirVec = dirVec2D(nextPos);
        yP = dirVec.y >=0 ? -dirVec.x : dirVec.x;
        if((nextPos.x - x <= 0.0001d)){
            xP = 1d;
        }
        else {
            xP = -(dirVec.y * yP) / dirVec.x;
        }

        return new Coordinates(xP , yP);
    }

    /**
     * Function received starting coordinates, direction vector multiplied by scale factor,
     * and returning new position
     * @param c Start position
     * @param dirVec Direction vector
     * @param scale scale factor, using for control length of direction vector
     * @return Position
     */
    public Coordinates coordinatesByDir(Coordinates c, Coordinates dirVec, double scale) {
        return new Coordinates(c.x +(dirVec.x*scale),c.y+(dirVec.y*scale));
    }

    /**
     * Function received starting coordinates, direction vector multiplied by scale factor,
     * and returning new position
     * @param dirVec Direction vector
     * @param scale scale factor, using for control length of direction vector
     * @return Position
     */
    public Coordinates coordinatesByDir(Coordinates dirVec, double scale) {
        return new Coordinates(x +(dirVec.x*scale) ,y+(dirVec.y*scale));
    }
    /**
     * Shifting one vector using shifting direction vector and scale factor
     * @param startPos
     * @param shiftDirVer Shifting direction vector
     * @param scalar Scale factor
     * @return Shifted Vector
     */
    public Coordinates shiftByVec(Coordinates startPos, Coordinates shiftDirVer, double scalar){
        return new Coordinates(startPos.x+(shiftDirVer.x*scalar), startPos.y+(shiftDirVer.y*scalar));
    }

    /**
     * Shifting one vector using shifting direction vector and scale factor
     * @param shiftDirVer Shifting direction vector
     * @param scalar Scale factor
     * @return Shifted Vector
     */
    public Coordinates shiftByVec(Coordinates shiftDirVer, double scalar){
        return new Coordinates(x+(shiftDirVer.x*scalar), y+(shiftDirVer.y*scalar));
    }

    public Double[] toDeg(Double coord) {
        Double deg = Math.toDegrees(coord);
        Double min = deg - deg.intValue();
        Double deg_int = (double) deg.intValue();
        return new Double[]{deg_int, min * 60};
    }

    public double toRad(int deg, double min) {
        return (Math.toRadians(deg + min / 60));
    }

    public double[] getCoords() {
        return new double[]{x,y};
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getYRad() {
        return Math.toRadians(y);
    }

    public double getXRad() {
        return Math.toRadians(x);
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return lat + " " + lon;
    }

    @Override
    public boolean equals(Object obj) {
        Coordinates o = (Coordinates) obj;
        double threshold = 0.0001d;
        return (Math.abs(x-o.x) <= threshold || Math.abs(y-o.y) <= threshold);
    }
}
