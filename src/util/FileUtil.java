package main.util;

import java.io.*;
import java.util.List;

public class FileUtil {
    public static <T> void writeFile(String path, List<T> data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> readFile(String path) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            return (List<T>) ois.readObject();
        } catch (Exception e) {
            return null;
        }
    }
}
