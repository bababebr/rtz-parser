package Main.Hibernates.Entities;

import Main.SperryMarine.Object.Objects;

import javax.persistence.*;
import java.io.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Objects")
public class ObjectEntity {
    @Id
    @GeneratedValue
    private  int id;
    @Column(name = "objectName")
    private String objectName;
    @Column(name = "createDate")
    private LocalDateTime createDate;
    @Column(name = "objectData")
    private byte[] data;

    public ObjectEntity() {
    }

    public ObjectEntity(Objects o) {
        objectName = o.getName();
        createDate = LocalDateTime.now();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(o);
            data = baos.toByteArray();
            System.out.println(data.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Objects toObject(){
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        try {
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (Objects) ois.readObject();
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

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
