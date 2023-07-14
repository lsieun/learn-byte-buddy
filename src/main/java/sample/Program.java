package sample;

import lsieun.annotation.Version;

import java.lang.reflect.Method;

public class Program {
    public static void main(String[] args) {
        Class<?> clazz = Version.class;
        boolean isAnnotation = clazz.isAnnotation();
        System.out.println("isAnnotation = " + isAnnotation);

        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods) {
            String name = m.getName();
            Object defaultValue = m.getDefaultValue();
            String message = String.format("    %s: %s", name, defaultValue);
            System.out.println(message);
        }
    }
}
