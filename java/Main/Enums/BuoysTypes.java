package Main.Enums;

public enum BuoysTypes {
    BOYSPP11("BOYSPP11"),
    OBSTRN01("OBSTRN01"),
    BOYISD12("BOYISD12"),
    BOYCAR04("BOYCAR04"),
    UWTROC04("UWTROC04"),
    UWTROC03("UWTROC03"),
    BOYSAW12("BOYSAW12"),
    ISODGR01("ISODGR01"),
    BOYSUP02("BOYSUP02"),
    BOYGEN03("BOYGEN03"),
    BOYCAR02("BOYCAR02"),
    DANGER("DANGER"),
    REGION_B_PORT_BUOY("REGION_B_PORT_BUOY"),
    REGION_A_PORT_BUOY("REGION_A_PORT_BUOY"),
    REGION_A_STARBOARD_BUOY("REGION_A_STARBOARD_BUOY"),
    BOYCAR01("BOYCAR01"),
    BOYCAR03("BOYCAR03"),
    WATTUR02("WATTUR02"),
    WRECKS05("WRECKS05"),
    BOYMOR11("BOYMOR11"),
    REGION_B_STARBOARD_BUOY("REGION_B_STARBOARD_BUOY");

    private final String name;

    BuoysTypes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
