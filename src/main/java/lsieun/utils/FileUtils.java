package lsieun.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    public static final File OUTPUT_DIR = getOutputDirectory();
    public static final int BUFFER_SIZE = 16 * 1024;

    public static File getTempDirectory() {
        return new File(System.getProperty("java.io.tmpdir"));
    }

    public static File getOutputDirectory() {
        String filepath = getBaseDirectory();
        return new File(filepath);
    }

    public static String getFilePath(String relativePath) {
        String dir = getBaseDirectory();
        String filepath = dir + relativePath;
        if (filepath.startsWith("/") && filepath.contains(":")) {
            return filepath.substring(1);
        }
        return filepath;
    }

    private static String getBaseDirectory() {
        String filepath = System.getProperty("user.dir") + File.separator + "target" + File.separator + "classes" + File.separator;
        return filepath.replace("\\", "/");
    }

    public static byte[] readBytes(String filepath) {
        File file = new File(filepath);
        if (!file.exists()) {
            throw new IllegalArgumentException("File Not Exist: " + filepath);
        }

        try (
                FileInputStream fis = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(fis)
        ) {
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            byte[] buf = new byte[BUFFER_SIZE];
            int len;
            while ((len = bis.read(buf)) != -1) {
                bao.write(buf, 0, len);
            }
            return bao.toByteArray();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void writeBytes(String filepath, byte[] bytes) {
        File file = new File(filepath);
        File dirFile = file.getParentFile();
        mkdirs(dirFile);

        try (OutputStream out = Files.newOutputStream(Paths.get(filepath));
             BufferedOutputStream buff = new BufferedOutputStream(out)) {
            buff.write(bytes);
            buff.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> readLines(String filepath) {
        return readLines(filepath, "UTF8");
    }

    public static List<String> readLines(String filepath, String charsetName) {
        File file = new File(filepath);
        if (!file.exists()) {
            throw new IllegalArgumentException("File Not Exist: " + filepath);
        }

        try (
                InputStream in = Files.newInputStream(file.toPath());
                Reader reader = new InputStreamReader(in, charsetName);
                BufferedReader bufferReader = new BufferedReader(reader)
        ) {
            List<String> list = new ArrayList<>();
            String line;
            while ((line = bufferReader.readLine()) != null) {
                list.add(line);
            }
            return list;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeLines(String filepath, List<String> lines) {
        if (lines == null || lines.size() < 1) return;

        File file = new File(filepath);
        File dirFile = file.getParentFile();
        mkdirs(dirFile);

        try (
                OutputStream out = Files.newOutputStream(file.toPath());
                Writer writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                BufferedWriter bufferedWriter = new BufferedWriter(writer)
        ) {
            for (String line : lines) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void mkdirs(File dirFile) {
        boolean file_exists = dirFile.exists();

        if (file_exists && dirFile.isDirectory()) {
            return;
        }

        if (file_exists && dirFile.isFile()) {
            throw new RuntimeException("Not A Directory: " + dirFile);
        }

        if (!file_exists) {
            boolean flag = dirFile.mkdirs();
            if (!flag) {
                throw new RuntimeException("Create Directory Failed: " + dirFile.getAbsolutePath());
            }
        }
    }

    public static void copy(String srcPath, String destPath) {
        File srcFile = new File(srcPath);
        if (!srcFile.exists()) {
            throw new IllegalArgumentException("srcPath is not valid: " + srcPath);
        }

        File destFile = new File(destPath);
        File dirFile = destFile.getParentFile();
        mkdirs(dirFile);

        try (
                FileInputStream fis = new FileInputStream(srcFile);
                BufferedInputStream bis = new BufferedInputStream(fis);
                FileOutputStream fos = new FileOutputStream(destFile);
                BufferedOutputStream bos = new BufferedOutputStream(fos)
        ) {
            byte[] buf = new byte[BUFFER_SIZE];
            int len;
            while ((len = bis.read(buf)) != -1) {
                bos.write(buf, 0, len);
            }
            bos.flush();
        }
        catch (IOException ex) {
            ex.printStackTrace();
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
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[BUFFER_SIZE];
            int len;
            while ((len = in.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
            return out.toByteArray();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        //
        return null;
    }

    public static InputStream getInputStream(String className) {
        return ClassLoader.getSystemResourceAsStream(className.replace('.', '/') + ".class");
    }
}