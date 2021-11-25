package Main.Enums;

public enum  ObjectFillPattern {
    WavesSmallHorizontal("Wave"),
    Cross("OutlinedDiamond"),
    Shingle("Shingle"),
    HorizontalLine("Horizontal"),
    VerticalLines("LightVertical"),
    Dots5("Percent05"),
    Dots10("Percent10"),
    Dots20("Percent20"),
    SmallDasheshHorizontal("DashedHorizontal"),
    LineDiagonal("ForwardDiagonal"),
    LineDiagonalInverted("BackwardDiagonal"),
    WavesVertical("Divot"),
    DashesVertical("DashedVertical"),
    Shell("LargeConfetti"),
    DottedDiamond("DottedDiamond"),
    DashedDownwardDiagonal("DashedDownwardDiagonal"),
    DottedGrid("DottedGrid");

    private final String name;

    ObjectFillPattern(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }






}
