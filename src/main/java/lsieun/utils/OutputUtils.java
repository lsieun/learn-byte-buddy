package lsieun.utils;

import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.LoadedTypeInitializer;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;
import java.util.Map;

public class OutputUtils {

    public static void save(DynamicType dynamicType) throws IOException {
        save(dynamicType, false);
    }

    public static void save(DynamicType dynamicType, boolean removeSynthetic) throws IOException {
        saveMainAndAuxiliaryType(dynamicType, removeSynthetic);

        saveTypeInitializers(dynamicType);

        dynamicType.close();
    }

    private static void saveMainAndAuxiliaryType(DynamicType dynamicType, boolean removeSynthetic) throws IOException {
        Map<TypeDescription, File> map = dynamicType.saveIn(FileUtils.OUTPUT_DIR.toFile());

        // Main Type
        System.out.println("Main Type: ");
        TypeDescription mainTypeDescription = dynamicType.getTypeDescription();
        printTypeAndPath(mainTypeDescription, map.get(mainTypeDescription).toPath(), removeSynthetic);

        // Auxiliary Types
        Map<TypeDescription, byte[]> auxiliaryTypesMap = dynamicType.getAuxiliaryTypes();
        if (auxiliaryTypesMap.isEmpty()) {
            return;
        }
        System.out.println("Auxiliary Types: " + auxiliaryTypesMap.size());
        for (Map.Entry<TypeDescription, File> entry : map.entrySet()) {
            TypeDescription typeDescription = entry.getKey();
            File file = entry.getValue();
            if (auxiliaryTypesMap.containsKey(typeDescription)) {
                printTypeAndPath(typeDescription, file.toPath(), removeSynthetic);
            }
        }
    }


    private static void printTypeAndPath(TypeDescription typeDescription, Path filepath, boolean removeSynthetic) throws IOException {
        String type = typeDescription.getName();
        if (removeSynthetic) {
            ASMUtils.removeSynthetic(filepath);
        }
        printFilePath(type, filepath);
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
            Path filepath = Path.of(FileUtils.OUTPUT_DIR.toString(), name);
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


    static void printFilePath(String name, Path filepath) {
        String message = String.format("    %s: %s", name, filepath.toUri());
        System.out.println(message);
    }
}
