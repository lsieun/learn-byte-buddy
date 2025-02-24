package sample.hierarchy;

public class Father extends GrandFather {
    public void sayFromFather() {
        System.out.println("Father");
    }

    @Override
    public String test(String name, int age) {
        return String.format("Father: %s - %d", name, age);
    }
}