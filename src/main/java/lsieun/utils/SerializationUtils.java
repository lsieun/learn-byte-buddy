package lsieun.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class SerializationUtils {
    public static void write(Object obj, Path filepath) throws IOException {
        try (
                ByteArrayOutputStream bao = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bao)
        ) {
            oos.writeObject(obj);
            byte[] bytes = bao.toByteArray();
            Files.write(filepath, bytes);
        }
    }

    public static Object read(Path filepath) throws IOException {
        byte[] bytes = Files.readAllBytes(filepath);
        try (ByteArrayInputStream bai = new ByteArrayInputStream(bytes);
             ObjectInputStream oin = new ObjectInputStream(bai)
        ) {
            return oin.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}