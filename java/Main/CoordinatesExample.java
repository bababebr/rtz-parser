package Main;

import Main.Tools.Coordinates;

public class CoordinatesExample {
    public static void main(String[] args) {
        /**
         * Coordinates initiation using String format
         */
        Coordinates c1 = new Coordinates("10-10.5N", "100-50.5E");
        Coordinates c2 = new Coordinates("10-10.5N 100-50.5E");

        /**
         * Coordinates initiation using double format
         */
        Coordinates c3 = new Coordinates(10.175, 100.84167);

        /**
         * Convert String to Double
         *
         *  */
        System.out.println("c1 to double: " + c1.getX() + " " + c1.getY());
        System.out.println("c3 to string: " + c3.toString());

        /**
         * Check if two Coordinates objects locates in the same geographical position
         */
        System.out.println(c1.equals(c3));
    }
}
