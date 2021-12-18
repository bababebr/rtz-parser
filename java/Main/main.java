package Main;

import Main.Enums.CATZOC;
import Main.Hibernates.HibernatesQueries;
import Main.RTZ.RouteFactory;
import Main.Route.Route;

import java.io.File;

public class main {
    public static void main(String[] args) {
        CATZOC c = CATZOC.C;
        System.out.println(c.getBasicUKC());
        //HibernatesQueries.wipeTable();
        //HibernatesQueries.insertRoute(new File("G:\\012. 2 OFFICER\\000.Passage Plan\\Routs"));
        //HibernatesQueries.showTable();

        //Route r = HibernatesQueries.fetchRoute(1209);
        Route r = RouteFactory.factory("G:\\012. 2 OFFICER\\000.Passage Plan\\2021\\NAT073\\01. Fujairah - Singapore\\Fujairah Berth -Singapore _NAT073.rtz");
        r.recalculateRLDist();
        System.out.println(r.info());
        System.out.println(r.getRL_totalDist());
        r.print_Legs(true);

    }
}
