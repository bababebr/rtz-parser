package Main.Enums;

public enum GeoShapeTypes {
    ARC ("ARC_AREA"),
    ARC_GUIDE ("ARCGUIDE"),
    CIRCLE ("CIRCLE"),
    CORRIDOR ("CORRIDOR"),
    ELLIPSE("ELLIPSE"),
    LINE ("CUSTOM_LINE"),
    POLYGON ("CUSTOM_AREA"),
    RECTANGLE ("RECTANGLE"),
    SLICE ("SLICE"),
    SQUARE ("SQUARE"),
    TRIANGLE ("TRIANGLE");

    private String name;

    GeoShapeTypes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
