package Main;

import Main.Enums.CATZOC;
import Main.Hibernates.HibernatesQueries;
import Main.RTZ.RouteFactory;
import Main.Route.Route;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class main {
    public static void main(String[] args) throws IOException {
        Route r = HibernatesQueries.fetchRoute(1484);
        for(double[] d : r.getWpCoordinates().values()){
            System.out.println(Arrays.toString(d));
        }
    }
}
