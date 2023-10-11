package enums;

public enum PassageType {
    O("Open"),
    C("Coastal"),
    R("Restricted"),
    P("Pilotage"),
    B("Alongside"),
    A("Anchorage"),
    S("SBM");

    private final String name;

    PassageType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
