package Main.SperryMarine.Object;

import Main.Enums.ObjectColor;
import Main.Enums.ObjectFillPattern;
import Main.Tools.Coordinates;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class represents an Sperry Marine Object
 */
public class Objects implements Serializable {
    private String objectID;
    private String layerID;
    private String type;
    private double latMin = 4d;
    private double lonMin = 4d;
    private double latMax = 0d;
    private double lonMax = 0d;
    private ArrayList<Double> vertices = new ArrayList<>();
    private double rotation;
    private String createTime;
    private String modifyTime;
    private String createShip;
    private String modifyUser = "User";
    private String name;
    private String label;
    private String note;
    private int chartEdition;
    private boolean archived = false;
    private String timeLabel;
    private int dateDependentType;
    private String dateDependentStart;
    private String dateDependentEnd;
    private boolean hasLight;
    private String lineSegmentType = "GreatCircleLine";
    private String geometryAttributes = "";
    private String customLineColor = ObjectColor.Purple.getName();
    private String lineStyle = "Solid";
    private String lineWeight = "Regular";
    private String lineArrowType = "None";
    private boolean showArrowAtStart;
    private boolean showArrowAtEnd;
    private String fillColor = ObjectColor.Purple.getName();
    private String fillPattern = ObjectFillPattern.LineDiagonal.getName();
    private String opacity = "Low";
    private int[] altitudes = new int[0];
    private Coordinates coordinates = new Coordinates();

    private int ADD_VERT_COUNT = 0;

    /**
     * Default Constructor
     */
    public Objects() {
        LocalDateTime l = LocalDateTime.now();
        createTime = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'+00:00'").format(l);
        modifyTime = dateDependentStart = dateDependentEnd = createTime;
        createShip = "";
        objectID = md5(String.valueOf(createTime + String.valueOf(hashCode())).getBytes());
        lineArrowType = "None";
    }

    /**
     * Setting object line style
     * @param style - See Main.Enums.LineStyle
     * @param weight - See Main.Enums.LineWeight
     * @param color - See Main.Enums.ObjectColor
     * @param type See Main.Enums.GeoShapesType;
     */
    public void setLine(String style, String weight, String color, String type) {
        this.lineWeight = weight;
        this.lineStyle = style;
        this.customLineColor = color;
        this.lineSegmentType = type;
    }

    /**
     * Setting object shape style
     * @param fillColor - See Main.Enums.ObjectColor
     * @param fillPattern - See Main.Enums.ObjectFillPattern
     * @param opacity - sell Main.Enums.ObjectOpacity
     */
    public void setShape(String fillColor, String fillPattern, String opacity) {
        this.fillColor = fillColor;
        this.fillPattern = fillPattern;
        this.opacity = opacity;
    }



    @Override
    public String toString() {
        return name + " " + type;
    }

    @Deprecated
    /**
     * Use "Coordinates" object method
     */
    public void addVertices(String c ) {
        String regex = "[0-8][0-9]-[0-6][0-9].[0-9]*[NS] [0-1][0-8][0-9]-[0-6][0-9].[0-9]*[EW]";
        String[] q = c.split("[A-Za-z//-]");
        addVertices(Integer.parseInt(q[0]), Double.parseDouble(q[1]),
                Integer.parseInt(q[2].trim()),Double.parseDouble(q[3]));
    }
    @Deprecated
    /**
     * Use "Coordinates" object method
     */
    public void addVertices(int latDeg, double ltMin, int longDeg, double longMin) {
        double lat = coordinates.toRad(latDeg,ltMin) ;
        double lng = coordinates.toRad(longDeg,longMin);
        if(lat <= latMin) {
            latMin = lat;
        }
        if(lat > latMax) {
            latMax = lat;
        }
        if(lng <= lonMin) {
            lonMin = lng;
        }
        if(lng > lonMax) {
            lonMax = lng;
        }
        vertices.add(lat);
        vertices.add(lng);
        setAltitudes();
    }
    @Deprecated
    /**
     * Use "Coordinates" object method
     */
    public void addVertices(double lat, double lng) {
        if(lat <= latMin) {
            latMin = lat;
        }
        if(lat > latMax) {
            latMax = lat;
        }
        if(lng <= lonMin) {
            lonMin = lng;
        }
        if(lng > lonMax) {
            lonMax = lng;
        }
        vertices.add(lat);
        vertices.add(lng);
        setAltitudes();
    }

    /**
     *Adding Objects Vertices
     * @param c - Vertex coordinates
     */
    public void addVertices(Coordinates c) {
        double lat = c.getXRad();
        double lng = c.getYRad();
        if(lat <= latMin) {
            latMin = lat;
        }
        if(lat > latMax) {
            latMax = lat;
        }
        if(lng <= lonMin) {
            lonMin = lng;
        }
        if(lng > lonMax) {
            lonMax = lng;
        }
        vertices.add(lat);
        vertices.add(lng);
        setAltitudes();
    }
    /**
     *Adding Objects Vertices
     * @param c - Vertex coordinates array
     */
    public void addVertices(Coordinates[] c){
        for(Coordinates coord : c){
            addVertices(coord);
        }
    }

    /**
     * Some objects requires additional amount of vertices which are will be generated automatically on ECDIS
     * Each type of object has cons var ADD_VERT_COUNT
     * See Main.SperryMarine.Layers.Object.GeoShapes
     */
    public void fillZeros() {
        for(int i=0; i < ADD_VERT_COUNT; i++){
            addVertices(0,0);
        }
    }

    /**
     * Delete all Vertices
     */
    public void clearVert() {
        vertices.clear();
    }

    public String getModifyUser() {
        return modifyUser;
    }

    /**
     * Altitudes tag requires filling with array of Zeros having the same length as a Vertices array
     * Private method, using inside of addVertices method.
     */
    private void setAltitudes() {
        altitudes = new int[vertices.size()];
        for (int i=0; i < vertices.size(); i++) {
            altitudes[i] = 0;
        }
    }

    /**
     * Converting altitudes array to String
     * Method use inside ObjectGenerator class
     * @return
     */
    public String generateAltidutes() {
        if (altitudes.length == 0) {
            return "null";
        }
        StringBuffer s = new StringBuffer();
        for(int i : altitudes) {
            s.append(i).append(",");
        }
        s.deleteCharAt(s.length()-1);
        return s.toString();
    }

    /**
     * md5 hash function for the LayerID and objectID generation
     * @param b - bytes array
     * @return
     */
    private String md5(byte[] b) {
        MessageDigest d = null;
        try {
            d = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] hash = d.digest(b);
        BigInteger no = new BigInteger(1, hash);
        String w = no.toString(16);
        while (w.length() < 32) {
            w = "0" + w;
        }
        return w;
    }

    /**
     * Defults Getters,Setters, HashCode, Equals
     * @param layerID
     */
    public void setLayerID(String layerID) {
        this.layerID = layerID;
    }
    public double getLatMin() {
        return latMin;
    }
    public double getLonMin() {
        return lonMin;

    }
    public double getLatMax() {
        return latMax;
    }
    public double getLonMax() {
        return lonMax;
    }
    public ArrayList<Double> getVertices() {
        return vertices;
    }
    public int[] getAltitudes() {
        return altitudes;
    }
    public String getCreateTime() {
        return createTime;
    }
    public String getCreateShip() {
        return createShip;
    }
    public String getModifyTime() {
        return modifyTime;
    }
    public String getName() {
        return name;
    }
    public String getLabel() {
        return label;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getLayerID() {
        return layerID;
    }
    public String getObjectID() {
        return objectID;
    }
    public double getRotation() {
        return rotation;
    }
    public void setRotation(double rotation) {
        this.rotation = rotation;
    }
    public boolean isArchived() {
        return archived;
    }
    public void setTimeLabel(String timeLabel) {
        this.timeLabel = timeLabel;
    }
    public void setDateDependentType(int dateDependentType) {
        this.dateDependentType = dateDependentType;
    }
    public int getChartEdition() {
        return chartEdition;
    }
    public String getTimeLabel() {
        return timeLabel;
    }
    public int getDateDependentType() {
        return dateDependentType;
    }
    public String getDateDependentStart() {
        return dateDependentStart;
    }
    public String getGetDateDependentEnd() {
        return dateDependentEnd;
    }
    public boolean isHasLight() {
        return hasLight;
    }
    public String getLineSegmentType() {
        return lineSegmentType;
    }
    public String getGeometryAttributes() {
        return geometryAttributes;
    }
    public String getCustomLineColor() {
        return customLineColor;
    }
    public String getLineStyle() {
        return lineStyle;
    }
    public String getLineWeight() {
        return lineWeight;
    }
    public String getLineArrowType() {
        return lineArrowType;
    }
    public boolean isShowArrowAtStart() {
        return showArrowAtStart;
    }
    public boolean isShowArrowAtEnd() {
        return showArrowAtEnd;
    }
    public String getFillColor() {
        return fillColor;
    }
    public String getFillPattern() {
        return fillPattern;
    }
    public String getOpacity() {
        return opacity;
    }
    public Coordinates getCoordinates() {
        return coordinates;
    }
    public void setDateDependentStart(String dateDependentStart) {
        this.dateDependentStart = dateDependentStart;
    }
    public void setGetDateDependentEnd(String getDateDependentEnd) {
        this.dateDependentEnd = getDateDependentEnd;
    }
    public void setHasLight(boolean hasLight) {
        this.hasLight = hasLight;
    }
    public void setLineSegmentTime(String lineSegmentTime) {
        this.lineSegmentType = lineSegmentTime;
    }
    public void setGeometryAttributes(String geometryAttributes) {
        this.geometryAttributes = geometryAttributes;
    }
    public void setCustomLineColor(String customLineColor) {
        this.customLineColor = customLineColor;
    }
    public void setLineStyle(String lineStyle) {
        this.lineStyle = lineStyle;
    }
    public void setLineWeight(String lineWeight) {
        this.lineWeight = lineWeight;
    }
    public void setLineArrowType(String lineArrowType) {
        this.lineArrowType = lineArrowType;
    }
    public void setShowArrowAtEnd(boolean showArrowAtEnd) {
        this.showArrowAtEnd = showArrowAtEnd;
    }
    public void setShowArrowAtStart(boolean showArrowAtStart) {
        this.showArrowAtStart = showArrowAtStart;
    }
    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }
    public void setFillPattern(String fillPattern) {
        this.fillPattern = fillPattern;
    }
    public void setOpacity(String opacity) {
        this.opacity = opacity;
    }
    public void setChartEdition(int chartEdition) {
        this.chartEdition = chartEdition;
    }
    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
    public int getADD_VERT_COUNT() {
        return ADD_VERT_COUNT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Objects)) return false;
        Objects objects = (Objects) o;
        return Double.compare(objects.latMin, latMin) == 0 &&
                Double.compare(objects.lonMin, lonMin) == 0 &&
                Double.compare(objects.latMax, latMax) == 0 &&
                Double.compare(objects.lonMax, lonMax) == 0 &&
                Double.compare(objects.rotation, rotation) == 0 &&
                chartEdition == objects.chartEdition &&
                archived == objects.archived &&
                dateDependentType == objects.dateDependentType &&
                hasLight == objects.hasLight &&
                showArrowAtStart == objects.showArrowAtStart &&
                showArrowAtEnd == objects.showArrowAtEnd &&
                java.util.Objects.equals(objectID, objects.objectID) &&
                java.util.Objects.equals(layerID, objects.layerID) &&
                java.util.Objects.equals(type, objects.type) &&
                java.util.Objects.equals(vertices, objects.vertices) &&
                java.util.Objects.equals(createTime, objects.createTime) &&
                java.util.Objects.equals(modifyTime, objects.modifyTime) &&
                java.util.Objects.equals(createShip, objects.createShip) &&
                java.util.Objects.equals(modifyUser, objects.modifyUser) &&
                java.util.Objects.equals(name, objects.name) &&
                java.util.Objects.equals(label, objects.label) &&
                java.util.Objects.equals(note, objects.note) &&
                java.util.Objects.equals(timeLabel, objects.timeLabel) &&
                java.util.Objects.equals(dateDependentStart, objects.dateDependentStart) &&
                java.util.Objects.equals(dateDependentEnd, objects.dateDependentEnd) &&
                java.util.Objects.equals(lineSegmentType, objects.lineSegmentType) &&
                java.util.Objects.equals(geometryAttributes, objects.geometryAttributes) &&
                java.util.Objects.equals(customLineColor, objects.customLineColor) &&
                java.util.Objects.equals(lineStyle, objects.lineStyle) &&
                java.util.Objects.equals(lineWeight, objects.lineWeight) &&
                java.util.Objects.equals(lineArrowType, objects.lineArrowType) &&
                java.util.Objects.equals(fillColor, objects.fillColor) &&
                java.util.Objects.equals(fillPattern, objects.fillPattern) &&
                java.util.Objects.equals(opacity, objects.opacity) &&
                Arrays.equals(altitudes, objects.altitudes) &&
                java.util.Objects.equals(coordinates, objects.coordinates);
    }

    @Override
    public int hashCode() {
        int result = java.util.Objects.hash(objectID, layerID, type, latMin, lonMin, latMax, lonMax, vertices, rotation, createTime, modifyTime, createShip, modifyUser, name, label, note, chartEdition, archived, timeLabel, dateDependentType, dateDependentStart, dateDependentEnd, hasLight, lineSegmentType, geometryAttributes, customLineColor, lineStyle, lineWeight, lineArrowType, showArrowAtStart, showArrowAtEnd, fillColor, fillPattern, opacity, coordinates);
        result = 31 * result + Arrays.hashCode(altitudes);
        return result;
    }
}
