package Main.SperryMarine.Object.GeoShapes;

import Main.Enums.GeoShapeTypes;
import Main.SperryMarine.Object.Objects;
import Main.Tools.Coordinates;

public class Square extends Objects {

    protected String name = GeoShapeTypes.SQUARE.getName();
    private int ADD_VERT_COUNT = 4;
    private int edge;
    private Coordinates pos;

    public Square(String layerId) {
        super();
        setType(name);
    }

    public Square(String layerId, int edge) {
        super();
        setType(name);
        this.edge = edge;
        setGeometryAttributes(String.format("&lt;RectangleGeometryAttributes Width=\"%d\" Height=\"%d\" /&gt;", edge));
    }
    public Square(String layerId, int edge, Coordinates pos) {
        super();
        setType(name);
        this.edge = edge;
        this.pos = pos;
        addVertices(pos);
        setGeometryAttributes(String.format("&lt;RectangleGeometryAttributes Width=\"%d\" Height=\"%d\" /&gt;", edge, edge));
    }
    @Override
    public void fillZeros() {
        System.out.println(ADD_VERT_COUNT);
        for(int i=0; i < ADD_VERT_COUNT; i++){
            addVertices(0,0);
        }
    }
}
