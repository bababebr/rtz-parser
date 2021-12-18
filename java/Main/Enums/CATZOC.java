package Main.Enums;

/**
 * Associate CATZOC type with basic UKC requirements (% of max. static draught)
 */
public enum CATZOC {
    A1(.1d),
    A2(.1d),
    B(.15d),
    C(.25d),
    D(.25d),
    U(1);
    private double basicUKC;
    CATZOC(double basicUKC) {this.basicUKC = basicUKC;}
    public double getBasicUKC(){return basicUKC;}
}
