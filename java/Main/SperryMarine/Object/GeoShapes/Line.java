package Main.SperryMarine.Object.GeoShapes;

import Main.Enums.GeoShapeTypes;
import Main.SperryMarine.Object.Objects;
import Main.Tools.Coordinates;

import java.util.ArrayList;

public class Line extends Objects {
    protected String name = GeoShapeTypes.LINE.getName();
    private final int ADD_VERT_COUNT = 0;

    public Line(String layerId) {
        super();
        setType(name);
    }

    public Line(String layerId, Coordinates[] c) {
        super();
        setType(name);
        addVertices(c);
    }



    public static Line sinSircleFrom(String layerId, double T, double A, int shift, double coeff,
                                     double startLat, double endLat, double startLong) {
        /*
        T - period; A - Amplitude
         */
        int maxStepCount = 50;
        double step = (endLat - startLat) / maxStepCount;

        Line c = new Line(layerId);
        ArrayList<Coordinates> coordArray = new ArrayList<>();
        ArrayList<Double> sinarray = new ArrayList<Double>();
        for(int j = -180; j <= 180;) {

            //System.out.println(j);
            sinarray.add((Math.sin(Math.toRadians(j)*T)*A)+shift);
            //System.out.println((Math.sin(Math.toRadians(j)*T)*A)+shift);
            j += 360/maxStepCount;
        }

        for(int i = 0; i <= maxStepCount; i ++) {
            coordArray.add(new Coordinates(startLat, startLong + (sinarray.get(i) / 1)));
            startLat += step;
            c.addVertices(coordArray.get(i));
            System.out.println(coordArray.get(i).getX() + " " + coordArray.get(i).getY());
        }
        return c;
    }
}
