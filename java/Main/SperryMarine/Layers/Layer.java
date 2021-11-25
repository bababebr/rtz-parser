package Main.SperryMarine.Layers;

import Main.SperryMarine.Object.Objects;
import Main.Tools.ObjectGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Layer {
    private String layerId;
    private String name;
    private String description;
    private List<Objects> objectsList = new ArrayList<>();
    private ObjectGenerator parser = new ObjectGenerator();

    private BufferedReader schema;

    public Layer(String name, String description) throws IOException {
        this.name = name;
        this.description = description;
        StringBuffer s = new StringBuffer(md5(String.valueOf(new Date().getTime()).getBytes()));
        s.insert(8,"-").insert(13,"-").insert(18,"-").insert(23,"-");
        layerId = s.toString();
        schema = Files.newBufferedReader(Paths.get("src\\Main.Data\\scheme.xsd"));
    }

    public String export() throws IOException {
        StringBuffer s = generateSchema();
        for(Objects o : objectsList) {
            o.setLayerID(layerId);
            o.fillZeros();

            s.append(parser.generateObject(o));
        }
        s.append(generateEnd());
        return s.toString();
    }

    private StringBuffer generateEnd() {
        StringBuffer s = new StringBuffer();
        s.append("<Layers>").append("\n");
        s.append("<Id>").append(layerId).append("</Id>").append("\n");
        s.append("<Name>").append(name).append("</Name>").append("\n");
        s.append("<Description>").append(description).append("</Description>").append("\n");
        s.append("</Layers>").append("\n");
        s.append("</NewDataSet>");
        return s;
    }

    @Override
    public String toString() {
        return name;
    }

    private StringBuffer generateSchema() throws IOException {
        String line = new String();
        StringBuffer schem = new StringBuffer();
        schema = Files.newBufferedReader(Paths.get("src\\Main.Data\\scheme.xsd"));
        while (true) {
            try {
                if (!((line = (schema.readLine())) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            schem.append(line).append("\n");
        }
        return schem;
    }

    private String md5(byte[] b) {
        MessageDigest d = null;
        try {
            d = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] hash = d.digest(b);
        BigInteger no = new BigInteger(1, hash);
        String w = no.toString(16);
        while (w.length() < 32) {
            w = "0" + w;
        }
        return w;
    }

    public void addObject(Objects o) {
        objectsList.add(o);
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLayerId() {
        return layerId;
    }

    public void printObjects() {
        for (Objects o : objectsList) {
            System.out.println(objectsList.indexOf(o) + ": Object " + o.getName() + ", Type: " + o.getType());
        }
    }

    public List<Objects> getObjects() {
        return  objectsList;
    }
    public void removeObj(int index) {
        try {
            System.out.println("Object " + objectsList.remove(index).getName() + " removed.");
        }
        catch (IndexOutOfBoundsException e) {
            System.err.println("Object.Objects id is incorrect");
        }
    }
    public boolean isEmpty() {
        return objectsList.isEmpty();
    }
    public int objCount() {
        return objectsList.size();
    }
}
