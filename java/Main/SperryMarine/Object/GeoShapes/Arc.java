package Main.SperryMarine.Object.GeoShapes;

import Main.Enums.GeoShapeTypes;
import Main.SperryMarine.Object.Objects;
import Main.Tools.Coordinates;

/*
    Arc requires 5 Altitudes.
    One for Center of Arc; Another 4 will be generated automatically;
 */
public class Arc extends Objects {
    protected String name = GeoShapeTypes.ARC.getName();
    private int ADD_VERT_COUNT = 4;
    private int outerRadius = 600;
    private int innerRadius = 300;
    private double startAngle = 0d;
    private double endAngle = 0d;

    public Arc( ) {
        super();
        setType(name);
    }

    public Arc(Coordinates c) {
        super();
        setType(name);
        addVertices(c);


    }

    public Arc(Coordinates c, int outerRadius, int innerRadius, double startAngle, double endAngle) {
        super();
        setType(name);
        this.outerRadius = outerRadius;
        this.innerRadius = innerRadius;
        this.startAngle = startAngle;
        this.endAngle = endAngle;
        addVertices(c);


    }

    @Override
    public void fillZeros() {
        System.out.println(ADD_VERT_COUNT);
        for(int i=0; i < ADD_VERT_COUNT; i++){
            addVertices(0,0);
        }
    }

    public int getOuterRadius() {
        return outerRadius;
    }

    public void setOuterRadius(int outerRadius) {
        this.outerRadius = outerRadius;
    }

    public int getInnerRadius() {
        return innerRadius;
    }

    public void setInnerRadius(int innerRadius) {
        this.innerRadius = innerRadius;
    }

    public double getStartAngle() {
        return startAngle;
    }

    public void setStartAngle(double startAngle) {
        this.startAngle = startAngle;
    }

    public double getEndAngle() {
        return endAngle;
    }

    public void setEndAngle(double endAngle) {
        this.endAngle = endAngle;
    }
}
