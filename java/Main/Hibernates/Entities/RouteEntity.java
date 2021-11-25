package Main.Hibernates.Entities;
import Main.Route.Route;

import javax.persistence.*;
import java.io.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "routes")
public class RouteEntity {

    @Id
    @GeneratedValue
    private int id;
    @Column(name = "routename")
    private String name;

    @Column(name = "data")
    private byte[] data;
    @Column(name = "createDate")
    private LocalDateTime createDate;
    @Column(name = "GCDistance")
    private double gcDist;
    @Column(name = "RLDistance")
    private double rlDist;


    public RouteEntity(){

    }


    public RouteEntity(Route r) {
        name = r.getRouteName();
        gcDist = r.getGC_totalDist();
        rlDist = r.getRL_totalDist();
        createDate = LocalDateTime.now();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(r);
            data = baos.toByteArray();
            System.out.println(data.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Route toRoute(){
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        try {
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (Route) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
}
