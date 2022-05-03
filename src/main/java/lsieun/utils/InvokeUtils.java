package lsieun.utils;

import net.bytebuddy.utility.RandomString;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class InvokeUtils {
    public static void invokeAllMethods(String className) throws Exception {
        Class<?> clazz = Class.forName(className);
        invokeAllMethods(clazz);
    }

    public static void invokeAllMethods(Class<?> clazz) throws Exception {
        int classModifiers = clazz.getModifiers();
        boolean isAbstractClass = Modifier.isAbstract(classModifiers);

        Method[] methodArray = clazz.getDeclaredMethods();
        for (Method method : methodArray) {
            int modifiers = method.getModifiers();
            if (Modifier.isAbstract(modifiers)) {
                continue;
            }

            Class<?>[] parameterTypes = method.getParameterTypes();
            Object[] args = createRandomMethodArgs(parameterTypes);
            if (Modifier.isStatic(modifiers)) {
                printMethodAndArgs(method, args);
                method.invoke(args);
            }
            else {
                if (isAbstractClass) continue;
                Object instance = createInstance(clazz);
                printMethodAndArgs(method, args);
                method.invoke(instance, args);
            }
        }
    }

    private static void printMethodAndArgs(Method method, Object[] args) {
        String methodInfo = getMethodInfo(method);
        String argsInfo = Arrays.toString(args);
        String line = String.format("%s --> %s", methodInfo, argsInfo);
        System.out.println(line);
    }

    private static String getMethodInfo(Method method) {
        int modifiers = method.getModifiers();
        String methodName = method.getName();
        String modifierStr = Modifier.toString(modifiers);
        int parameterCount = method.getParameterCount();
        Class<?> returnType = method.getReturnType();
        Class<?>[] parameterTypes = method.getParameterTypes();

        String returnString = returnType.getSimpleName();
        List<String> parameterList = new ArrayList<>();
        for (int i = 0; i < parameterCount; i++) {
            String str = parameterTypes[i].getSimpleName();
            parameterList.add(str);
        }
        String parameterString = String.join(", ", parameterList);

        return String.format("%nNow, let's invoke: %s %s %s(%s)", modifierStr, returnString, methodName, parameterString);
    }

    private static Object createInstance(Class<?> clazz) throws Exception {
        int modifiers = clazz.getModifiers();
        boolean isAbstract = Modifier.isAbstract(modifiers);
        if (isAbstract) {
            System.out.println(clazz.getName() + "is abstract class");
            return null;
        }

        Constructor<?> candidate = null;

        Constructor<?>[] constructorArray = clazz.getDeclaredConstructors();
        for (Constructor<?> constructor : constructorArray) {
            candidate = constructor;
            break;
        }

        if (candidate == null) {
            System.out.println("no constructor is accessible");
            return null;
        }


        Class<?>[] parameterTypes = candidate.getParameterTypes();
        Object[] args = createRandomMethodArgs(parameterTypes);

        return candidate.newInstance(args);
    }

    private static Object[] createRandomMethodArgs(Class<?>[] parameterTypes) {
        int length = parameterTypes.length;
        Object[] args = new Object[length];

        for (int i = 0; i < length; i++) {
            Class<?> type = parameterTypes[i];
            if (type.isPrimitive()) {
                Object val;
                if (type == boolean.class) {
                    val = Boolean.TRUE;
                }
                else if (type == byte.class) {
                    val = (byte) (Math.random() * 10);
                }
                else if (type == short.class) {
                    val = (short) (Math.random() * 10);
                }
                else if (type == char.class) {
                    val = (char) (Math.random() * 10);
                }
                else if (type == int.class) {
                    val = (int) (Math.random() * 10);
                }
                else if (type == float.class) {
                    val = (float) (Math.random() * 10);
                }
                else if (type == long.class) {
                    val = (long) (Math.random() * 10);
                }
                else if (type == double.class) {
                    val = Math.random() * 10;
                }
                else {
                    val = null;
                }
                args[i] = val;
            }
            else if (type == String.class) {
                args[i] = new RandomString(8).nextString();
            }
            else if (type == Date.class) {
                args[i] = new Date();
            }
            else {
                args[i] = null;
            }
        }

        return args;
    }
}
