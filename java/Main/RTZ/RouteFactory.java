package Main.RTZ;

import Main.Route.Leg;
import Main.Route.Route;
import Main.Route.Waypoint;
import Main.Tools.Encryptor;
import com.sun.istack.NotNull;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Objects;
import java.util.TreeMap;

public class RouteFactory implements Serializable {
    private String routeName;
    private TreeMap<Integer, Waypoint> waypointsTreeMap = new TreeMap<>();
    private ArrayList<Leg> legsList = new ArrayList<>();
    private File routeFile;
    private FileReader routeFileReader;




    private RouteFactory(String RTZFilePath){
        routeFile = new File(RTZFilePath);
        try {
            routeFileReader = new FileReader(routeFile);
            loadWP();
            calculateLegs();
        } catch (FileNotFoundException e) {
            System.err.println("Header File Not Found, check \'ref\' folder for RTZheader.rtz file.");
            System.out.println("or route file is not found.");
        } catch (IOException e) {
        }
    }
    /**
     * Create route object from route.rtz file
     * @param RTZFilePath Path to the RTZ file
     * @return if validation of rtz file passed ok return route object
     */
    public static Route factory(@NotNull String RTZFilePath ){
        RouteFactory r = new RouteFactory(RTZFilePath);
        if(r.validate()){
            System.out.println(String.format("Route File %1s has been loaded.", r.getRouteName()));
            return new Route(r.getRouteName(), r.getWaypointsTreeMap(),r.getLegsList());
        }
        System.out.println("Please load correct route file");
        return null;
    }

    private void loadWP() throws IOException {
        int id = 1;
        StringBuilder s = new StringBuilder();
        StringBuilder wpString = new StringBuilder();
        boolean found = false;
        int c = routeFileReader.read();
        while (c >= 1) {
            if ((char) c == "\n".charAt(0)) {
                if(s.toString().contains("<routeInfo"))
                    routeName = s.substring(s.indexOf("routeName=\"") + 11, s.lastIndexOf("\"")).replace("\'", "");
                if(s.toString().contains("<waypoint") || found){
                    if(s.toString().contains("</waypoint>")){
                        found = false;
                        wpString.append(s);
                        //System.out.println();
                        Waypoint wp = new Waypoint(wpString.toString());
                        wp.setId(id);
                        id++;
                        waypointsTreeMap.put(wp.getId(), wp);
                        wpString = new StringBuilder();
                    }
                    else {
                        found = true;
                        wpString.append(s);
                    }
                }
                s = new StringBuilder();
            }
            s.append((char) c);
            c = routeFileReader.read();
        }
        routeFileReader.close();
    }
    private void calculateLegs( ){
        for(int i = 1; i <= (waypointsTreeMap.size() - 1); i++){
            legsList.add(new Leg(waypointsTreeMap.get(i),waypointsTreeMap.get(i+1),i));
        }
    }
    private byte[] getHeader() throws IOException { //Parsing root file fot the header
        StringBuilder sb = new StringBuilder();
        int headerRowCount = 3;
        routeFileReader = new FileReader(routeFile);
        int chr = routeFileReader.read();
        for(int i = 0; i < headerRowCount;){
            while (i<headerRowCount){
                if(i==1) {
                    sb.append((char) chr);
                }
                if(chr ==  "\n".charAt(0)){
                    i+=1;
                }
                chr = routeFileReader.read();
            }
        }
        return sb.toString().trim().getBytes();
    }

    private boolean validate(){ //Comparation of route header to the RTZ required pattern
        try {
            byte[] patternV1 = Files.readAllBytes(Path.of("src\\java\\Main\\Data\\RTZ Schema.header")); //encrypted pattern located in project dir
            byte[] patterntV2 = Files.readAllBytes(Path.of("src\\java\\Main\\Data\\RTZ Schema.header"));
            byte[] patterntV3 = Files.readAllBytes(Path.of("src\\java\\Main\\Data\\RTZ Schema.header"));
            byte[] header = Encryptor.Encrypt(getHeader());

            if(Encryptor.equal(patternV1, header)){
                return true;
            }
            else if(Encryptor.equal(patterntV2, header)) {
                return true;
            }
            else return Encryptor.equal(patterntV3, header);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public TreeMap<Integer, Waypoint> getWaypointsTreeMap() {
        return waypointsTreeMap;
    }

    public void setWaypointsTreeMap(TreeMap<Integer, Waypoint> waypointsHashMap) {
        this.waypointsTreeMap = waypointsHashMap;
    }

    public ArrayList<Leg> getLegsList() {
        return legsList;
    }

    public void setLegsList(ArrayList<Leg> legsList) {
        this.legsList = legsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RouteFactory)) return false;
        RouteFactory route = (RouteFactory) o;
        return routeName.equals(route.routeName) &&
                waypointsTreeMap.equals(route.waypointsTreeMap) &&
                routeFile.equals(route.routeFile) &&
                routeFileReader.equals(route.routeFileReader);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routeName, waypointsTreeMap, routeFile, routeFileReader);
    }
}
