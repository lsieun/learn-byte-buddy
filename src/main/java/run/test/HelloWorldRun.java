package run.test;

import lsieun.utils.ClassUtils;
import lsieun.utils.InvokeUtils;

public class HelloWorldRun {
    public static void main(String[] args) throws Exception {
        String className = "sample.HelloWorld";

        Object instance = ClassUtils.createInstance(className);
        System.out.println(instance);

        InvokeUtils.invokeAllMethods(className);
    }
}
