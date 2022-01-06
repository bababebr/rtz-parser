package Tools;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/*
Test Class.
byte = ~byte
 */

public class Encryptor {

    public static byte[] Encrypt(Path pathToFile){
        byte[] out = null;
        try {
            out = Files.readAllBytes(pathToFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < out.length; i++){
            out[i] = (byte) ~out[i];
        }
        return out;
    }

    public static byte[] Encrypt(byte[] out){
        for(int i = 0; i < out.length; i++){
            out[i] = (byte) ~out[i];
        }
        return out;
    }

    public static byte[] Decrypt(Path pathToFile){
        byte[] out = null;
        try {
            out = Files.readAllBytes(pathToFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < out.length; i++){
            out[i] = (byte) ~out[i];
        }
        return out;
    }

    public static byte[] Decrypt(byte[] out){
        for(int i = 0; i < out.length; i++){
            out[i] = (byte) ~out[i];
        }
        return out;
    }

    public static String DecryptToString(Path pathToFile){
        byte[] out = null;
        StringBuilder sb = new StringBuilder();
        try {
            out = Files.readAllBytes(pathToFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < out.length; i++){
            out[i] = (byte) ~out[i];
            sb.append((char) out[i]);
        }
        return sb.toString();
    }

    public static boolean equal(byte[] obj1, byte[] obj2) {
        if(obj1.length != obj2.length) {return false;}
        for(int i = 0; i< obj1.length; i++){
            if (obj1[i] != obj2[i]){
                return false;
            }
        }
        return true;
    }
}
