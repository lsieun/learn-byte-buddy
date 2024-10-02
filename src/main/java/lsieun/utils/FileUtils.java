package lsieun.utils;

import java.io.*;
import java.nio.file.Path;

public class FileUtils {
    public static final Path OUTPUT_DIR = getOutputDirectory();
    public static final int BUFFER_SIZE = 16 * 1024;

    public static Path getTempDirectory() {
        String tmpDir = System.getProperty("java.io.tmpdir");
        return Path.of(tmpDir);
    }

    public static Path getOutputDirectory() {
        return getBaseDirectory();
    }

    public static Path getFilePath(String relativePath) {
        Path dir = getBaseDirectory();
        return Path.of(dir.toString(), relativePath);
    }

    private static Path getBaseDirectory() {
        Path dirPath = Path.of(".", "target", "classes");
        return dirPath.toAbsolutePath().normalize();
    }

    public static byte[] readClassBytes(String className) {
        InputStream in = getInputStream(className);
        return readStream(in);
    }

    public static byte[] readStream(final InputStream in) {
        if (in == null) {
            throw new IllegalArgumentException("inputStream is null!!!");
        }

        try (in) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[BUFFER_SIZE];
            int len;
            while ((len = in.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
            return out.toByteArray();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //
        return null;
    }

    public static InputStream getInputStream(String className) {
        return ClassLoader.getSystemResourceAsStream(className.replace('.', '/') + ".class");
    }
}