package lsieun.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Logger {

    private static final Map<Class<?>, Logger> map = new ConcurrentHashMap<>();

    public static Logger of(Class<?> clazz) {
        return map.computeIfAbsent(clazz, Logger::new);
    }

    private final Class<?> clazz;

    private Logger(Class<?> clazz) {
        this.clazz = clazz;
    }

    public void red(String msg) {
        color(LogColor.RED, msg);
    }

    public void blue(String msg) {
        color(LogColor.BLUE, msg);
    }

    public void green(String msg) {
        color(LogColor.GREEN, msg);
    }

    public void color(LogColor color, String msg) {
        String typeName = clazz.getTypeName();
        String formattedMsg = String.format(
                "%s[INFO] [%s] - %s%s",
                color.val,
                typeName,
                msg,
                LogColor.RESET.val
        );
        System.out.println(formattedMsg);
    }


    enum LogColor {
        BLACK("\033[0;30m"),
        RED("\033[0;31m"),
        GREEN("\033[0;32m"),
        YELLOW("\033[0;33m"),
        BLUE("\033[0;34m"),
        MAGENTA("\033[0;35m"),
        CYAN("\033[0;36m"),
        WHITE("\033[0;37m"),
        RESET("\033[0m"),
        ;

        public final String val;

        LogColor(String val) {
            this.val = val;
        }
    }
}
