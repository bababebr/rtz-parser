package enums;

public enum LEG_TYPE {

    Loxodrome("Loxodrome"),
    Orthodrome("Orthodrome");

    public final String name;

    LEG_TYPE(String name) {
        this.name = name;
    }
}
