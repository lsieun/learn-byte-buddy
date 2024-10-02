package run.test;

import lsieun.utils.ClassUtils;
import lsieun.utils.MethodInvokeUtils;

public class HelloWorldRun {
    public static void main(String[] args) throws Exception {
        // (1) class
        String className = "sample.HelloWorld";
        Class<?> clazz = Class.forName(className);

        // (2) instance
        Object instance = ClassUtils.createInstance(clazz);
        assert instance != null;

        // (3) invoke methods
        MethodInvokeUtils.invokeAllMethods(instance, false);
    }
}
