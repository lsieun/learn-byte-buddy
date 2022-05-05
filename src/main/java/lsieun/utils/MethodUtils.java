package lsieun.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class MethodUtils {
    public static Method findMethod(Class<?> clazz, String methodName) {
        Method[] methodArray = clazz.getDeclaredMethods();

        Method targetMethod = null;
        for (Method m : methodArray) {
            if (m.getName().equals(methodName)) {
                targetMethod = m;
                break;
            }
        }
        return targetMethod;
    }

    public static void invoke(String className, String methodName, Object... args) throws Exception {
        invoke(Class.forName(className), methodName, args);
    }

    public static void invoke(Class<?> clazz, String methodName, Object... args) throws Exception {
        Method[] methodArray = clazz.getDeclaredMethods();
        Method candidate = null;
        for (Method method : methodArray) {
            if (method.getName().endsWith(methodName)) {
                candidate = method;
                break;
            }
        }

        if (candidate == null) {
            String message = String.format("can not find target method: %s from %s", methodName, clazz.getName());
            System.out.println(message);
            return;
        }

        int modifiers = candidate.getModifiers();
        Class<?> returnType = candidate.getReturnType();

        Object result;
        if (Modifier.isStatic(modifiers)) {
            result = candidate.invoke(args);
        }
        else {
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            Object instance = constructor.newInstance();
            result = candidate.invoke(instance, args);
        }

        if (returnType != void.class) {
            System.out.println(result);
        }
    }
}
