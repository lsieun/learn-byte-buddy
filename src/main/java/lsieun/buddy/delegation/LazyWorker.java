package lsieun.buddy.delegation;

import net.bytebuddy.implementation.bind.annotation.Argument;
import net.bytebuddy.implementation.bind.annotation.Default;
import sample.IDog;

import java.util.Date;

public class LazyWorker {
    public static String test(@Default IDog dog,
                              @Argument(0) String name,
                              @Argument(1) int age,
                              @Argument(2) Date date) {
        String message = dog.test(name, age, date);
        return "message from LazyWorker - " + message;
    }
}
