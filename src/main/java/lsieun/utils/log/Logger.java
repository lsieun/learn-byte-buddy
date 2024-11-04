package lsieun.utils.log;

import java.text.MessageFormat;

@SuppressWarnings("UnnecessaryLocalVariable")
public class Logger {

    private final Class<?> clazz;

    Logger(Class<?> clazz) {
        this.clazz = clazz;
    }

    public void trace(String pattern, Object... arguments) {
        String msg = format(pattern, arguments);
        log(LogColor.CYAN, LogLevel.TRACE, msg);
    }

    public void debug(String pattern, Object... arguments) {
        String msg = format(pattern, arguments);
        log(LogColor.GREEN, LogLevel.DEBUG, msg);
    }

    public void info(String pattern, Object... arguments) {
        String msg = format(pattern, arguments);
        log(LogColor.BLUE, LogLevel.INFO, msg);
    }

    public void warn(String pattern, Object... arguments) {
        String msg = format(pattern, arguments);
        log(LogColor.MAGENTA, LogLevel.WARN, msg);
    }

    public void error(String pattern, Object... arguments) {
        String msg = format(pattern, arguments);
        log(LogColor.RED, LogLevel.ERROR, msg);
    }

    private String format(String pattern, Object... arguments) {
        String msg = MessageFormat.format(pattern, arguments);
        return msg;
    }

    public void log(LogColor color, LogLevel level, String msg) {
        String typeName = clazz.getTypeName();
        String formattedMsg = String.format(
                "%s[%5s] [%s] - %s%s",
                color.val,
                level,
                typeName,
                msg,
                LogColor.RESET.val
        );
        System.out.println(formattedMsg);
    }
}
