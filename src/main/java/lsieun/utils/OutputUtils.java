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
        saveMainType(dynamicType);

        saveTypeInitializers(dynamicType);
    }

    private static void saveMainType(DynamicType dynamicType) throws IOException {
        Map<TypeDescription, File> map = dynamicType.saveIn(FileUtils.OUTPUT_DIR);
        System.out.println("Main Type:");
        for (Map.Entry<TypeDescription, File> entry : map.entrySet()) {
            String type = entry.getKey().getName();
            String path = entry.getValue().getPath().replace("\\", "/");
            String message = String.format("    %s: file:///%s", type, path);
            System.out.println(message);
        }
    }

    private static void saveTypeInitializers(DynamicType dynamicType) throws IOException {
        boolean flag = dynamicType.hasAliveLoadedTypeInitializers();
        System.out.println("hasAliveLoadedTypeInitializers: " + flag);
        if (!flag) return;

        Map<TypeDescription, LoadedTypeInitializer> loadedTypeInitializers = dynamicType.getLoadedTypeInitializers();
        System.out.println("Loaded Type Initializers Size: " + loadedTypeInitializers.size());
        for (Map.Entry<TypeDescription, LoadedTypeInitializer> entry : loadedTypeInitializers.entrySet()) {
            TypeDescription type = entry.getKey();
            LoadedTypeInitializer typeInitializer = entry.getValue();

            String name = type.getName();
            String filepath = String.format("%s/%s.ser", FileUtils.OUTPUT_DIR, name).replace("\\", "/");
            System.out.println(name);
            System.out.println(filepath);
            System.out.println(typeInitializer.getClass().getName());
            if (typeInitializer instanceof Serializable) {
                SerializationUtils.write(typeInitializer, filepath);
                String message = String.format("    %s: file:///%s", name, filepath);
                System.out.println(message);
            }
            else {
                String message = String.format("%s:%s is not serializable", name, typeInitializer.getClass().getName());
                System.out.println(message);
            }
        }
    }
}
