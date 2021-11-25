package Main.Enums;

public enum LegTypes {
    O ("Open"),
    C ("Coastal"),
    R ( "Restricted"),
    P ("Pilotage"),
    B ("Alongside"),
    A ("Anchorage"),
    S ("SBM");

    private final String name;

    LegTypes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
