package Main.SperryMarine.Object.Beacons;

import Main.Enums.BeaconsTypes;
import Main.SperryMarine.Object.Objects;
import Main.Tools.Coordinates;

public class Beacon extends Objects {
    public Beacon() {
        super();
    }

    public Beacon(String layerId, BeaconsTypes b) {
        super();
        setType(b.getName());
    }

    public Beacon(String layerId, BeaconsTypes b, Coordinates c) {
        super();
        setType(b.getName());
        addVertices(c);
    }
}
