package lsieun.utils;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        try {
            URL url = FileUtils.class.getProtectionDomain().getCodeSource().getLocation();
            URI uri = url.toURI();
            return Paths.get(uri);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
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
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            byte[] buf = new byte[BUFFER_SIZE];
            int len;
            while ((len = in.read(buf)) != -1) {
                bao.write(buf, 0, len);
            }
            return bao.toByteArray();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static InputStream getInputStream(String className) {
        return ClassLoader.getSystemResourceAsStream(className.replace('.', '/') + ".class");
    }
}