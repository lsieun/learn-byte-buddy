package lsieun.utils.log;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LoggerFactory {
    private static final Map<Class<?>, Logger> map = new ConcurrentHashMap<>();

    public static Logger getLogger(Class<?> clazz) {
        return map.computeIfAbsent(clazz, Logger::new);
    }
}
