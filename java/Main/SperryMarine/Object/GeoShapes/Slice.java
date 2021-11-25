package Main.SperryMarine.Object.GeoShapes;

import Main.Enums.GeoShapeTypes;
import Main.SperryMarine.Object.Objects;
import Main.Tools.Coordinates;

public class Slice extends Objects {

    protected String name = GeoShapeTypes.SLICE.getName();

    private int ADD_VERT_COUNT = 2;
    private int radius = 0 ;
    private double startAngle = 0d;
    private double endAngle = 0.5d;

    public Slice(String layerId) {
        super();
        setType(name);
        setLineSegmentTime("RhumbLine");
        setAttribute();
    }

    public Slice(String layerId, int radius, double startAngle, double endAngle) {
        super();
        this.radius = radius;
        this.startAngle = startAngle;
        this.endAngle = endAngle;
        setType(name);
        setLineSegmentTime("RhumbLine");
        setAttribute();
    }

    public Slice(String layerId, int radius, double startAngle, double endAngle, Coordinates pos) {
        super();
        this.radius = radius;
        this.startAngle = startAngle;
        this.endAngle = endAngle;
        setType(name);
        setLineSegmentTime("RhumbLine");
        setAttribute();
        addVertices(pos);
    }

    public void setAttribute() {
        setGeometryAttributes(String.format(
                "&lt;SliceGeometryAttributes Radius=\"%d\" StartAngle=\"%2f\" EndAngle=\"%3f\" /&gt;",
                radius, startAngle, endAngle));
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
        setAttribute();
    }

    public double getStartAngle() {
        return startAngle;

    }

    public void setStartAngle(double startAngle) {
        this.startAngle = startAngle;
        setAttribute();
    }

    public double getEndAngle() {
        return endAngle;
    }

    public void setEndAngle(double endAngle) {
        this.endAngle = endAngle;
        setAttribute();
    }

    @Override
    public void fillZeros() {
        System.out.println(ADD_VERT_COUNT);
        for(int i=0; i < ADD_VERT_COUNT; i++){
            addVertices(0,0);
        }
    }
}
