package Main.SperryMarine.Object.GeoShapes;

import Main.Enums.GeoShapeTypes;
import Main.SperryMarine.Object.Objects;
import Main.Tools.Coordinates;

public class Triangle extends Objects {

    protected String name = GeoShapeTypes.TRIANGLE.getName();
    private int ADD_VERT_COUNT = 3;
    private int sideLength;
    private Coordinates pos;

    public Triangle() {
        setType(name);
    }
    public Triangle(int sideLength) {
        setType(name);
        this.sideLength = sideLength;
        setGeometryAttributes(String.format("&lt;TriangleGeometryAttributes SideLength=\"%d\" /&gt;", sideLength));
    }
    public Triangle(int sideLength, Coordinates pos) {
        setType(name);
        this.pos = pos;
        this.sideLength = sideLength;
        setGeometryAttributes(String.format("&lt;TriangleGeometryAttributes SideLength=\"%d\" /&gt;", sideLength));
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
