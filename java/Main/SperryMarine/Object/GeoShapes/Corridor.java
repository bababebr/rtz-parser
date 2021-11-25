package Main.SperryMarine.Object.GeoShapes;

import Main.Enums.GeoShapeTypes;
import Main.SperryMarine.Object.Objects;

public class Corridor extends Objects {
    protected String name = GeoShapeTypes.CORRIDOR.getName();
    private int width=500;

    public Corridor(String layerId) {
        super();
        setType(name);
        setGeometryAttributes(String.format("&lt;CorridorGeometryAttributes Corridor_x0020_Width=\"%d\" /&gt;",width));
    }

    public Corridor(String layerId, int width) {
        super();
        this.width = width;
        setType(name);
        setGeometryAttributes(String.format("&lt;CorridorGeometryAttributes Corridor_x0020_Width=\"%d\" /&gt;", width));
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
