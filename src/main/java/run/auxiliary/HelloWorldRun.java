package run.auxiliary;

import sample.HelloWorld;

public class HelloWorldRun {
    public static void main(String[] args) {
        HelloWorld instance = new HelloWorld();
        String message = instance.test("Tom", 10);
        System.out.println(message);
    }
}
