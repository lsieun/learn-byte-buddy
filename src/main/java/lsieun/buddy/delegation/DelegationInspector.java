package lsieun.buddy.delegation;

import net.bytebuddy.implementation.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.Formatter;
import java.util.concurrent.Callable;

public class DelegationInspector {
    @RuntimeType
    public static Object inspect(
            @Super Object zuper,
            @SuperMethod Method superMethod,
            @SuperCall Callable<Object> instrumentedMethod,


            @Origin Class<?> clazz,
            @Origin Method method,
            @This Object instance
    ) throws Throwable {


        StringBuilder sb = new StringBuilder();
        Formatter fm = new Formatter(sb);
        fm.format("@Super        : %s - %s%n", zuper.getClass().getName(), zuper);
        fm.format("@SuperMethod  : %s%n", superMethod.getName());
        fm.format("@SuperCall    : %s%n%n", instrumentedMethod.getClass().getName());

        fm.format("@Origin Class : %s%n", clazz.getName());
        fm.format("@Origin Method: %s%n", method.getName());
        fm.format("@This         : %s - %s%n", instance.getClass().getName(), instance);

        System.out.println(sb);


        long startTime = System.currentTimeMillis();
        Object result = instrumentedMethod.call();
        long endTime = System.currentTimeMillis();

        long diff = endTime - startTime;
        System.out.println("Execution Time: " + diff);

        return result;
    }
}
