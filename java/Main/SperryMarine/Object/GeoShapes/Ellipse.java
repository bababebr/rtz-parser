package Main.SperryMarine.Object.GeoShapes;

import Main.Enums.GeoShapeTypes;
import Main.SperryMarine.Object.Objects;
import Main.Tools.Coordinates;

public class Ellipse extends Objects {

    protected String name = GeoShapeTypes.ELLIPSE.getName();
    private final int ADD_VERT_COUNT = 4; //4 additional vertices required for ellipse
    int minorAxis = 300;
    int majorAxis = 600;
    int Eccentricity;
    Coordinates pos;

    public Ellipse(String layerId) {
        super();
        setType(name);
        setGeometryAttributes(String.format(
                "&lt;EllipseGeometryAttributes Minor_x0020_Axis=\"%d\" Major_x0020_Axis=\"%2d\" " +
                "Eccentricity=\"0.8660254037844386\" /&gt;",majorAxis, minorAxis)); //Eccentricity will be changed automatically
        addVertices(pos);
    }

    public Ellipse(String layerId, int majorAxis, int minorAxis, Coordinates pos) {
        super();
        this.majorAxis = majorAxis;
        this.minorAxis = minorAxis;
        this.pos = pos;

        setType("ELLIPSE");
        setGeometryAttributes(String.format(
                "&lt;EllipseGeometryAttributes Minor_x0020_Axis=\"%d\" Major_x0020_Axis=\"%2d\" " +
                "Eccentricity=\"0.8660254037844386\" /&gt;",majorAxis, minorAxis)); //Eccentricity will be changed automatically
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
