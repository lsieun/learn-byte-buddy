package sample;

import sample.hierarchy.Father;
import sample.hierarchy.ICat;
import sample.hierarchy.IDog;

public class HelloWorld extends Father implements ICat, IDog {
    public void sayFromHelloWorld() {
        System.out.println("HelloWorld");
    }

    public String test(String name, int age) {
        return String.format("HelloWorld: %s - %d", name, age);
    }
}