package lsieun.utils;

import java.lang.reflect.Constructor;

public class ClassUtils {
    public static Object createInstance(String className, Object... args) throws Exception {
        Class<?> clazz = Class.forName(className);
        int length = args.length;

        Constructor<?> candidate = null;
        Constructor<?>[] constructorArray = clazz.getDeclaredConstructors();
        for (Constructor<?> constructor : constructorArray) {
            int parameterCount = constructor.getParameterCount();
            if (parameterCount == length) {
                candidate = constructor;
                break;
            }
        }

        if (candidate != null) {
            Class<?>[] parameterTypes = candidate.getParameterTypes();
            return createInstance(clazz, parameterTypes, args);
        }

        return null;
    }

    public static Object createInstance(Class<?> clazz, Class<?>[] parameterTypes, Object... args) throws Exception {
        Constructor<?> constructor = clazz.getDeclaredConstructor(parameterTypes);
        return constructor.newInstance(args);
    }
}
