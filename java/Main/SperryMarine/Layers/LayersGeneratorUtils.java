package Main.SperryMarine.Layers;

import Main.Route.Leg;
import Main.Route.Route;
import Main.Route.Waypoint;
import Main.SperryMarine.Object.GeoShapes.Circle;
import Main.Tools.Coordinates;

import java.io.IOException;

/**
 * Util class which is used for auto layers generation for different purpose.
 * For exm:
 *     Generation of the Line Object above each route's leg with information required by my company SMS and VIQ 7.0
 *     such as: min UKC, Safety Depth, Position Fix Frequency and etc.
 *     This information provided in Route objects for each leg
 */
public class LayersGeneratorUtils {
    private LayersGeneratorUtils() {

    }

    public static Layer legInformationGeneration(Route r, double scalar, String text){
        try {
            Layer layer = new Layer(r.getRouteName(),r.getRouteName());
            for(Leg l : r.getLegList()){
                Waypoint wpStart = l.getWpStart();
                Waypoint wpEnd = l.getWpEnd();
                double len = wpStart.getPosition().vec2DLength(wpEnd.getPosition());

                Coordinates pertVecDir = wpStart.getPosition().perpendicularDirVec2D(wpEnd.getPosition());
                Coordinates perpObjectPosition = wpStart.getPosition().coordinatesByDir(pertVecDir,scalar);
                Coordinates objectPos = perpObjectPosition.shiftByVec(wpStart.getPosition().dirVec2D(wpEnd.getPosition()),len/2d);
                Circle c = new Circle(objectPos, 500);
                c.setLabel(text);
                layer.addObject(c);
            }
            return layer;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
