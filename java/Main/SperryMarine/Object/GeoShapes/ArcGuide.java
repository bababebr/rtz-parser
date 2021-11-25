package Main.SperryMarine.Object.GeoShapes;

import Main.Enums.GeoShapeTypes;
import Main.SperryMarine.Object.Objects;
import Main.Tools.Coordinates;


public class ArcGuide extends Objects {
    protected String name = GeoShapeTypes.ARC_GUIDE.getName();
    private int ADD_VERT_COUNT = 4;
    private double startAngle = 0;
    private double endAngle = 0;
    private int radius = 500;
    private Coordinates pos = new Coordinates(0,0);
    public ArcGuide(String layerId) {
        super();
        setType(name);
        setGeometryAttributes(String.format("&lt;ArcGeometryAttributes Radius=\"%d\" StartAngle=\"%f\" " +
                "EndAngle=\"%f\" /&gt;", radius, startAngle, endAngle));
        addVertices(pos);
    }

    public ArcGuide(String layerId, Coordinates pos) {
        super();
        this.pos = pos;
        setType(name);
        setGeometryAttributes(String.format("&lt;ArcGeometryAttributes Radius=\"%d\" StartAngle=\"%f\" " +
                "EndAngle=\"%f\" /&gt;", radius, startAngle, endAngle));
        addVertices(pos);
    }

    public ArcGuide(String layerId, Coordinates pos, double startAngle, double endAngle, int radius) {
        super();
        this.pos = pos;
        this.startAngle = startAngle;
        this.endAngle = endAngle;
        this.radius = radius;
        setType(name);
        setGeometryAttributes(String.format("&lt;ArcGeometryAttributes Radius=\"%d\" StartAngle=\"%f\" " +
                "EndAngle=\"%f\" /&gt;", radius, startAngle, endAngle));
        addVertices(pos);

    }

    @Override
    public void fillZeros() {
        System.out.println(ADD_VERT_COUNT);
        for(int i=0; i < ADD_VERT_COUNT; i++){
            addVertices(0,0);
        }
    }



}
