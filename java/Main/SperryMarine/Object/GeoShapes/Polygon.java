package Main.SperryMarine.Object.GeoShapes;

import Main.Enums.GeoShapeTypes;
import Main.SperryMarine.Object.Objects;
import Main.Tools.Coordinates;

public class Polygon extends Objects {

    protected String name = GeoShapeTypes.POLYGON.getName();

    public Polygon(String layerId) {
        super();
        setType(name);
    }

    public Polygon(String layerId, Coordinates[] c) {
        super();
        setType(name);
        addVertices(c);

    }


}
