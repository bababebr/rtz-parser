package Main.Enums;

public enum ObjectColor {
    BlueGray ("RadarMapChannel"),
    Purple("RadarMapMagentaFaint"),
    PalePurple("RadarMapProhibited"),
    Red("RadarMapRed"),
    Yellow("RadarMapYellow"),
    Green("RadarMapGreen"),
    White("RadarMapWhite"),
    Orange("RadarMapNInfo"),
    Brown("RadarMapBrown");

    private final String name;

    ObjectColor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
