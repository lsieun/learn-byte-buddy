package lsieun.utils;

import java.io.*;

public class SerializationUtils {
    public static void write(Object obj, String filepath) throws IOException {
        try (
                ByteArrayOutputStream bao = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bao)
        ) {
            oos.writeObject(obj);
            byte[] bytes = bao.toByteArray();
            FileUtils.writeBytes(filepath, bytes);
        }
    }

    public static Object read(String filepath) throws IOException {
        byte[] bytes = FileUtils.readBytes(filepath);
        assert bytes != null;
        try (ByteArrayInputStream bai = new ByteArrayInputStream(bytes);
             ObjectInputStream oin = new ObjectInputStream(bai)
        ) {
            return oin.readObject();
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}