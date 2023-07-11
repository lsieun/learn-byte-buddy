package lsieun.buddy.delegation;

import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.util.Formatter;

public class DelegationAtOrigin {

    @RuntimeType
    public static String inspect(
            @Origin Class<?> clazz,
            @Origin Executable executable,
            // @Origin Constructor<?> constructor,
            // @Origin Method method,
            @Origin MethodHandle methodHandle,
            @Origin MethodType methodType,
            @Origin String str,
            @Origin int modifier
    ) {

        StringBuilder sb = new StringBuilder();
        Formatter fm = new Formatter(sb);
        fm.format("Class       : %s%n", clazz);
        fm.format("Executable  : %s%n", executable);
        // fm.format("Constructor : %s%n", constructor);
        // fm.format("Method      : %s%n", method);
        fm.format("MethodHandle: %s%n", methodHandle);
        fm.format("MethodType  : %s%n", methodType);
        fm.format("String      : %s%n", str);
        fm.format("int         : %s%n", modifier);
        System.out.println(sb);

        return "Message from DelegationAtOrigin.inspect()";
    }
}
