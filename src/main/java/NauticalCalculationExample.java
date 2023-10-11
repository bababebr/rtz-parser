import units.Coordinates;
import utils.NauticalMath;

public class NauticalCalculationExample {
    public static void main(String[] args) {
        Coordinates target = new Coordinates("11-06.921N", "070-55.325E");
        Coordinates ownShip = new Coordinates("10-56.836N", "071-04.882E");

        NauticalMath n = new NauticalMath();
        /**
         * Calculation of Rhumbline distance and course
         */
        System.out.println("RL Dist: " + n.RL_Dist(ownShip, target) + " " + "RL Course: " + n.RL_Course(ownShip, target));

        /**
         * Calculation of Greatcircle distance and course
         */
        System.out.println("GC Dist: " + n.GC_Dist(ownShip, target) + " " + "GC Course: " + n.GC_Course(ownShip, target));
    }
}
