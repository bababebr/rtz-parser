package ru.rtz.enums;

public enum LegType {

    Loxodrome("Loxodrome"),
    Orthodrome("Orthodrome");

    public final String name;

    LegType(String name) {
        this.name = name;
    }
}
