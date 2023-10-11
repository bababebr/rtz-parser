package utils;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import units.Coordinates;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class NauticalMath {
    double a = 7915.70447d;
    double e = 0.08181919034d;
    double e2 = 0.066943799079d;
    double half_e = 0.040909552d;
    double GeoToNautical_coeff = 1.001795274d;
    double aNm = 3443.91846652268d;

    /**
     * All formulas used for the calculation are taken from Brown's Nautical Almanac
     * WGS-84 Geodesic system
     */

    public double GC_Dist(Coordinates c1, Coordinates c2) {

        double dLong = c2.getY() - c1.getY();

        double sinLatC1 = Math.sin(Math.toRadians(c1.getX()));
        double cosLatC1 = Math.cos(Math.toRadians(c1.getX()));
        double sinLatC2 = Math.sin(Math.toRadians(c2.getX()));
        double cosLatC2 = Math.cos(Math.toRadians(c2.getX()));

        double result = Math.toDegrees(Math.acos(Math.cos(Math.toRadians(dLong))
                * cosLatC1 * cosLatC2 + (sinLatC1 * sinLatC2))) * 60;
        return result;
    }

    public double GC_Course(Coordinates c1, Coordinates c2) {
        //Course 0-180
        if (Math.abs(c1.getY() - c2.getY()) <= 0.00001d) {
            if (c1.getX() > c2.getX()) {
                return 180d;
            } else {
                return 0d;
            }
        }
        //Course 90-270
        if ((Math.abs(c1.getX() - c2.getX())) <= 0.00001d) {
            if (c1.getY() > c2.getY()) {
                return 270d;
            } else {
                return 90d;
            }
        }
        //Other Angles
        double dLong = c2.getY() - c1.getY();
        double sinLatC1 = Math.sin(Math.toRadians(c1.getX()));
        double cosLatC1 = Math.cos(Math.toRadians(c1.getX()));
        double sinLatC2 = Math.sin(Math.toRadians(c2.getX()));
        double cosLatC2 = Math.cos(Math.toRadians(c2.getX()));
        double distAngle = Math.toDegrees(Math.acos(Math.cos(Math.toRadians(dLong))
                * cosLatC1 * cosLatC2 + (sinLatC1 * sinLatC2)));
        double cosA = (sinLatC2 - ((Math.cos(Math.toRadians(distAngle))) * sinLatC1))
                / (Math.sin(Math.toRadians(distAngle)) * cosLatC1);

        if (c2.getY() < c1.getY()) {
            return 360d - Math.toDegrees(Math.acos(cosA));
        }
        return Math.toDegrees(Math.acos(cosA));
    }

    /**
     * Calculation of Rhumbline course using Meridional Parts Difference
     *
     * @param c1 Start Point
     * @param c2 End Point
     * @return
     */
    public double RL_Course(Coordinates c1, Coordinates c2) {
        double diffM = meridinalParts(c2.getX()) - meridinalParts(c1.getX()); //Meridional parts difference
        if (Math.abs(c2.getX() - c1.getX()) <= 0.00001d) { // if course laid on Parallel (90 or 270 Deg)
            return c2.getY() - c1.getY() > 0 ? 90d : 270d;
        }
        double diffLotAbs = Math.abs(c2.getY() - c1.getY()) > 180 ? 360 - (Math.abs(c2.getY() - c1.getY())) : Math.abs(c2.getY() - c1.getY());
        double diffLot = c2.getY() - c1.getY();
        double diffLat = c2.getX() - c1.getX();

        double course = Math.toDegrees(Math.atan((diffLotAbs) * 60 / diffM));
        if (diffLot > 0) {
            return diffLat >= 0 ? course : 180 + course;
        } else {
            return diffLat > 0 ? (360 - course) : (180 - course);
        }
    }

    public double RL_Dist(Coordinates c1, Coordinates c2) {
        double diffLat = c2.getX() - c1.getX();
        double diffLon = c2.getY() - c1.getY();
        if (Math.abs(diffLat) <= 0.00001d) {
            return Math.abs(Math.cos(Math.toRadians(c1.getX())) * Math.abs(diffLon * 60) * 1.001795274d);
        }
        double s = aNm * (1d / Math.cos(Math.toRadians(RL_Course(c1, c2)))) *
                Math.abs((1d - 1d / 4d * e * e) * Math.toRadians(diffLat) -
                        (3d / 8d * e * e * (Math.sin(Math.toRadians(2d * c2.getX())) - Math.sin(Math.toRadians(2d * c1.getX())))));
        return Math.abs(s);
    }

    public double meridinalParts(double lat) {
        double part_1 = (Math.tan(Math.toRadians(45 + (lat / 2))));
        double part_2 = (1 - (e * Math.sin((Math.toRadians(lat)))));
        double part_3 = (1 + (e * Math.sin((Math.toRadians(lat)))));
        return a * Math.log10(part_1 * Math.pow(part_2 / part_3, half_e));
    }
}
