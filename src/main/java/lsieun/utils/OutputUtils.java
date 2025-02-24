package lsieun.utils;

import lsieun.box.BoxUtils;

import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.LoadedTypeInitializer;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class OutputUtils {

    public static void save(DynamicType dynamicType) {
        save(dynamicType, false);
    }

    public static void save(DynamicType dynamicType, boolean removeSynthetic) {
        try (dynamicType) {
            // save
            Map<TypeDescription, File> mainAndAuxiliaryMap = saveMainAndAuxiliaryType(dynamicType);
            Map<TypeDescription, String> typeInitializerMap = saveTypeInitializers(dynamicType);

            printPath(dynamicType.getTypeDescription(), mainAndAuxiliaryMap, typeInitializerMap);

            // remove synthetic
            if (removeSynthetic) {
                for (Map.Entry<TypeDescription, File> entry : mainAndAuxiliaryMap.entrySet()) {
                    Path filepath = entry.getValue().toPath();
                    ASMUtils.removeSynthetic(filepath);
                }
            }
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

    private static void printPath(TypeDescription mainTypeDesc,
                                  Map<TypeDescription, File> mainAndAuxiliaryMap,
                                  Map<TypeDescription, String> typeInitializerMap) {
        int size1 = mainAndAuxiliaryMap.size();
        int size2 = typeInitializerMap.size();
        String[][] matrix = new String[size1 + size2][3];
        oneLine(
                matrix, 0, "Main Type",
                mainTypeDesc,
                mainAndAuxiliaryMap.get(mainTypeDesc).toPath().toUri().toString()
        );

        int rowIndex = 0;
        for (Map.Entry<TypeDescription, File> entry : mainAndAuxiliaryMap.entrySet()) {
            TypeDescription typeDescription = entry.getKey();
            File file = entry.getValue();
            if (typeDescription.equals(mainTypeDesc)) {
                continue;
            }

            rowIndex++;
            oneLine(
                    matrix, rowIndex,
                    "Auxiliary Type",
                    typeDescription,
                    file.toPath().toUri().toString()
            );
        }

        for (Map.Entry<TypeDescription, String> entry : typeInitializerMap.entrySet()) {
            TypeDescription typeDescription = entry.getKey();
            String value = entry.getValue();
            rowIndex++;
            oneLine(
                    matrix, rowIndex,
                    "Loaded Type Initializer",
                    typeDescription,
                    value
            );
        }

        BoxUtils.printMatrix(matrix);
    }

    private static void oneLine(String[][] matrix, int rowIndex, String category, TypeDescription typeDesc, String content) {
        matrix[rowIndex][0] = category;
        matrix[rowIndex][1] = typeDesc.getSimpleName();
        matrix[rowIndex][2] = content;
    }

    private static Map<TypeDescription, File> saveMainAndAuxiliaryType(DynamicType dynamicType) throws IOException {
        return dynamicType.saveIn(FileUtils.OUTPUT_DIR.toFile());
    }

    private static Map<TypeDescription, String> saveTypeInitializers(DynamicType dynamicType) throws IOException {
        boolean flag = dynamicType.hasAliveLoadedTypeInitializers();
        if (!flag) {
            return Collections.emptyMap();
        }

        Map<TypeDescription, String> map = new HashMap<>();
        Map<TypeDescription, LoadedTypeInitializer> loadedTypeInitializers = dynamicType.getLoadedTypeInitializers();
        System.out.println("Loaded Type Initializers: " + loadedTypeInitializers.size());
        for (Map.Entry<TypeDescription, LoadedTypeInitializer> entry : loadedTypeInitializers.entrySet()) {
            TypeDescription type = entry.getKey();
            LoadedTypeInitializer typeInitializer = entry.getValue();

            String name = type.getName();
            Path filepath = Path.of(FileUtils.OUTPUT_DIR.toString(), name);
            if (typeInitializer instanceof Serializable) {
                SerializationUtils.write(typeInitializer, filepath);
                map.put(type, filepath.toUri().toString());
            }
            else {
                String message = String.format("%s:%s is not serializable", name, typeInitializer.getClass().getName());
                map.put(type, message);
            }
        }
        return map;
    }
}
