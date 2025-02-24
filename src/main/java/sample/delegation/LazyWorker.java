package sample.delegation;

import net.bytebuddy.implementation.bind.annotation.AllArguments;

import java.util.Arrays;

public class LazyWorker {
    public static String test(@AllArguments Object[] args) {
        String message = Arrays.toString(args);
        return "message from LazyWorker: " + message;
    }
}
