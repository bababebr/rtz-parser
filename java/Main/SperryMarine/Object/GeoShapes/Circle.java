package Main.SperryMarine.Object.GeoShapes;

import Main.Enums.GeoShapeTypes;
import Main.SperryMarine.Object.Objects;
import Main.Tools.Coordinates;

public class Circle extends Objects {
    protected String name = GeoShapeTypes.CIRCLE.getName();
    private int ADD_VERT_COUNT = 1;
    private int radius;
    private Coordinates centerPos;
    public Circle() {
        setType(name);
    }

    public Circle(Coordinates centerPos, int radius) {
        setType(name);
        this.centerPos = centerPos;
        this.radius = radius;
        setGeometryAttributes(String.format("&lt;CircleGeometryAttributes Radius=\"%d\" /&gt;", radius));
        addVertices(centerPos); //Center coord

    }

    public void changePosition(Coordinates centerPos) {
        this.centerPos = centerPos;
        clearVert();
        addVertices(centerPos);

    }

    public void setRadius(int radius) {
        this.radius = radius;
        setGeometryAttributes(String.format("&lt;CircleGeometryAttributes Radius=\"%d\" /&gt;", radius));
    }

    @Override
    public void fillZeros() {
        for(int i=0; i < ADD_VERT_COUNT; i++){
            addVertices(0,0);
        }
    }




}
