package lsieun.utils;

import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class OutputUtils {
    public static void save(DynamicType dynamicType) throws IOException {
        Map<TypeDescription, File> map = dynamicType.saveIn(FileUtils.OUTPUT_DIR);
        for (Map.Entry<TypeDescription, File> entry : map.entrySet()) {
            String type = entry.getKey().getName();
            String path = entry.getValue().getPath().replace("\\", "/");
            String message = String.format("%s: file:///%s", type, path);
            System.out.println(message);
        }
    }
}
