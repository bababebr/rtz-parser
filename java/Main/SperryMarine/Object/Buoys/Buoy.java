package Main.SperryMarine.Object.Buoys;

import Main.Enums.BuoysTypes;
import Main.SperryMarine.Object.Objects;
import Main.Tools.Coordinates;

public class Buoy extends Objects {

    public Buoy(BuoysTypes b) {
        super();
        setType(b.getName());
    }

    public Buoy(BuoysTypes b, Coordinates c) {
        super();
        setType(b.getName());
        addVertices(c);
    }
}
