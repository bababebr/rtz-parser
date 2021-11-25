package Main.SperryMarine.Object.GeoShapes;

import Main.Enums.GeoShapeTypes;
import Main.SperryMarine.Object.Objects;
import Main.Tools.Coordinates;

public class Rectangle extends Objects {
    protected String name = GeoShapeTypes.RECTANGLE.getName();
    private Coordinates pos;
    private int width = 300;
    private int height = 600;

    private int ADD_VERT_COUNT = 4;
    public Rectangle(String layerId) {
        super();
        setType(name);
        setGeometryAttributes(String.format("&lt;RectangleGeometryAttributes Width=\"%1d\" Height=\"%2d\" /&gt;",width,height));
    }

    public Rectangle(String layerId, int width, int height) {
        super();
        setType(name);
        this.width = width;
        this.height = height;
        setGeometryAttributes(String.format("&lt;RectangleGeometryAttributes Width=\"%1d\" Height=\"%2d\" /&gt;",width,height));
    }

    public Rectangle(String layerId, int width, int height, Coordinates pos) {
        super();
        setType(name);
        this.width = width;
        this.height = height;
        this.pos = pos;
        addVertices(pos);
        setGeometryAttributes(String.format("&lt;RectangleGeometryAttributes Width=\"%1d\" Height=\"%2d\" /&gt;",width,height));
    }
    @Override
    public void fillZeros() {
        System.out.println(ADD_VERT_COUNT);
        for(int i=0; i < ADD_VERT_COUNT; i++){
            addVertices(0,0);
        }
    }
}
