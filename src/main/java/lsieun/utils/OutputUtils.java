package lsieun.utils;

import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.LoadedTypeInitializer;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

public class OutputUtils {

    public static void save(DynamicType dynamicType) throws IOException {
        save(dynamicType, false);
    }

    public static void save(DynamicType dynamicType, boolean removeSynthetic) throws IOException {
        saveMainType(dynamicType, removeSynthetic);

        saveTypeInitializers(dynamicType);

        dynamicType.close();
    }

    private static void saveMainType(DynamicType dynamicType, boolean removeSynthetic) throws IOException {
        Map<TypeDescription, File> map = dynamicType.saveIn(FileUtils.OUTPUT_DIR);
        System.out.println("Main Type: " + map.size());
        for (Map.Entry<TypeDescription, File> entry : map.entrySet()) {
            String type = entry.getKey().getName();
            String path = entry.getValue().getPath().replace("\\", "/");
            if (removeSynthetic) {
                ASMUtils.removeSynthetic(path);
            }
            printFilePath(type, path);
        }
    }

    private static void saveTypeInitializers(DynamicType dynamicType) throws IOException {
        boolean flag = dynamicType.hasAliveLoadedTypeInitializers();
        if (!flag) return;

        Map<TypeDescription, LoadedTypeInitializer> loadedTypeInitializers = dynamicType.getLoadedTypeInitializers();
        System.out.println("Loaded Type Initializers: " + loadedTypeInitializers.size());
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


    static void printFilePath(String name, String filepath) {
        String forwardSlashPath = filepath.replace("\\", "/");
        String message = String.format("    %s: file:///%s", name, forwardSlashPath);
        System.out.println(message);
    }
}
