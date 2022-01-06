package Main;
import Main.Tools.Coordinates;
import Main.Tools.NauticalMath;

public class NauticalCalculationExample {
    public static void main(String[] args) {
        Coordinates c1 = new Coordinates("02-00.0N","013-00.0E");
        Coordinates c2 = new Coordinates("00-59.0N", "013-00.0E");

        NauticalMath n = new NauticalMath();
        /**
         * Calculation of Rhumbline distance and course
         */
        System.out.println("RL Dist: " + n.RL_Dist(c1,c2) + " " + "RL Course: " + n.RL_Course(c1,c2));

        /**
         * Calculation of Greatcircle distance and course
         */
        System.out.println("GC Dist: " + n.GC_Dist(c1,c2)  + " " + "GC Course: " + n.GC_Course(c1,c2));
    }
}
