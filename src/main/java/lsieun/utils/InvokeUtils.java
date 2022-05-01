package lsieun.utils;

import net.bytebuddy.jar.asm.Opcodes;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class InvokeUtils {
    public static void invoke(String className, String methodName, Object... args) throws Exception {
        invoke(Class.forName(className), methodName, args);
    }

    public static void invoke(Class<?> clazz, String methodName, Object... args) throws Exception {
        Method[] declaredMethods = clazz.getDeclaredMethods();
        Method targetMethod = null;
        for (Method m : declaredMethods) {
            if (m.getName().endsWith(methodName)) {
                targetMethod = m;
                break;
            }
        }

        if (targetMethod == null) {
            String message = String.format("can not find target method: %s from %s", methodName, clazz.getName());
            System.out.println(message);
            return;
        }

        int modifiers = targetMethod.getModifiers();
        Class<?> returnType = targetMethod.getReturnType();

        Object result;
        if ((modifiers & Opcodes.ACC_STATIC) != 0) {
            result = targetMethod.invoke(args);
        }
        else {
            Object instance = clazz.newInstance();
            result = targetMethod.invoke(instance, args);
        }

        if (returnType != void.class) {
            System.out.println(result);
        }
    }

    public static Object createInstance(String className) throws Exception {
        Class<?> clazz = Class.forName(className);
        return createInstance(clazz, new Class<?>[0]);
    }

    public static Object createInstance(String className, Class<?>[] parameterTypes, Object... args) throws Exception {
        Class<?> clazz = Class.forName(className);
        return createInstance(clazz, parameterTypes, args);
    }

    public static Object createInstance(Class<?> clazz) throws Exception {
        return createInstance(clazz, new Class<?>[0]);
    }

    public static Object createInstance(Class<?> clazz, Class<?>[] parameterTypes, Object... args) throws Exception {
        Constructor<?> constructor = clazz.getDeclaredConstructor(parameterTypes);
        return constructor.newInstance(args);
    }
}
