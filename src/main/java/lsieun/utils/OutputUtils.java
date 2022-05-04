package lsieun.utils;

import lsieun.buddy.asm.modifier.RemoveSyntheticVisitor;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.LoadedTypeInitializer;
import net.bytebuddy.jar.asm.ClassReader;
import net.bytebuddy.jar.asm.ClassVisitor;
import net.bytebuddy.jar.asm.ClassWriter;
import net.bytebuddy.jar.asm.Opcodes;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

public class OutputUtils {

    public static void save(DynamicType dynamicType) throws IOException {
        saveMainType(dynamicType);

        saveTypeInitializers(dynamicType);
    }

    private static void saveMainType(DynamicType dynamicType) throws IOException {
        Map<TypeDescription, File> map = dynamicType.saveIn(FileUtils.OUTPUT_DIR);
        System.out.println("Main Type: " + map.size());
        for (Map.Entry<TypeDescription, File> entry : map.entrySet()) {
            String type = entry.getKey().getName();
            String path = entry.getValue().getPath().replace("\\", "/");
            printFilePath(type, path);
        }
    }

    private static void saveTypeInitializers(DynamicType dynamicType) throws IOException {
        boolean flag = dynamicType.hasAliveLoadedTypeInitializers();
        if (!flag) return;

        Map<TypeDescription, LoadedTypeInitializer> loadedTypeInitializers = dynamicType.getLoadedTypeInitializers();
        System.out.println("Loaded Type Initializers Size: " + loadedTypeInitializers.size());
        for (Map.Entry<TypeDescription, LoadedTypeInitializer> entry : loadedTypeInitializers.entrySet()) {
            TypeDescription type = entry.getKey();
            LoadedTypeInitializer typeInitializer = entry.getValue();

            String name = type.getName();
            String filepath = String.format("%s/%s.ser", FileUtils.OUTPUT_DIR, name).replace("\\", "/");
            if (typeInitializer instanceof Serializable) {
                SerializationUtils.write(typeInitializer, filepath);
                printFilePath(name, filepath);
            }
            else {
                String message = String.format("%s:%s is not serializable", name, typeInitializer.getClass().getName());
                System.out.println(message);
            }
        }
    }

    public static void removeSynthetic(String filepath) {
        File file = new File(filepath);
        if (!file.exists()) {
            return;
        }

        byte[] bytes = FileUtils.readBytes(filepath);
        if (bytes == null) return;


        ClassReader cr = new ClassReader(bytes);
        ClassWriter cw = new ClassWriter(0);
        ClassVisitor cv = new RemoveSyntheticVisitor(Opcodes.ASM9, cw);
        cr.accept(cv, 0);

        String className = cr.getClassName();
        byte[] newBytes = cw.toByteArray();
        FileUtils.writeBytes(filepath, newBytes);
        printFilePath(className, filepath);
    }

    private static void printFilePath(String name, String filepath) {
        String forwardSlashPath = filepath.replace("\\", "/");
        String message = String.format("    %s: file:///%s", name, forwardSlashPath);
        System.out.println(message);
    }
}
